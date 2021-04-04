<template>
  <form
    class="container mx-auto p-4 pt-12 h-screen bg-white"
    @submit.prevent="updateAuction"
  >
    <div
      class="max-w-3xl pb-6 shadow-lg mx-auto p-4 lg:pr-16 lg:pl-16 md:pr-20 md:pl-20 sm:pr-12 sm:pl-12 pr-4 pl-4 rounded-lg space-y-8"
    >
      <!-- card title -->
      <h3 class="text-center text-3xl font-bold text-gray-700 mt-12">
        Update auction
      </h3>

      <!-- Title input -->
      <div class="mt-12 flex-col space-y-2">
        <label for="title" class="font-bold text-gray-800">Auction Title</label>
        <input
          autocomplete="off"
          type="text"
          id="title"
          v-model="inputTitle"
          placeholder="Enter a title..."
          class="w-full border border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200 focus:ring-0 focus:border-main-color"
        />
      </div>
      <div class="mt-12 flex-col space-y-2">
        <!-- select dropdown -->
        <label for="title" class="font-bold text-gray-800">Category</label>
        <select
          id="category"
          name="category"
          class="w-full focus:border-main-color focus:ring-0 rounded-md border-gray-300"
          v-model="selectedCategory"
        >
          <option v-for="category in categories" :key="category.categoryId">
            {{ category.categoryName }}
          </option>
        </select>
      </div>

      <!-- price input -->
      <div class="mt-3 flex-col space-y-2">
        <label for="price" class="font-bold text-gray-800">Price (USD)</label>
        <input
          v-model.number="inputPrice"
          placeholder="1"
          type="number"
          :min="inputPrice"
          id="price"
          class="w-full border focus:ring-0 focus:border-main-color border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>
      <!-- date picker -->
      <div class="mt-3 flex-col space-y-2">
        <label for="end-date" class="font-bold text-gray-800">End date</label>
        <input
          placeholder="1"
          type="datetime-local"
          v-model="inputEndDate"
          step="1"
          id="end-date"
          class="w-full border focus:ring-0 focus:border-main-color border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>
      <!-- image url input -->
      <div class="mt-3 flex-col space-y-2">
        <label for="image-url" class="font-bold text-gray-800">Image Url</label>
        <input
          placeholder="Enter a image URL..."
          type="url"
          id="image-url"
          v-model="inputImageUrl"
          class="w-full border focus:ring-0 focus:border-main-color border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>

      <!-- description input -->
      <div class="mt-3 flex-col space-y-2">
        <label for="description" class="font-bold text-gray-800"
          >Description</label
        >
        <textarea
          rows="4"
          v-model="inputDescription"
          placeholder="Enter a description..."
          type="text"
          id="description"
          class="w-full border border-gray-200 focus:ring-0 focus:border-main-color bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>

      <!-- error message -->
      <span class="text-red-500" v-if="isFormValid === 'invalid'"
        >One or more inputs contain errors</span
      >
      <!-- send button -->
      <button
        class="w-full flex justify-center bg-success text-gray-200 p-4 rounded-lg focus:outline-none active:opacity-90"
      >
        <svg
          v-if="isFormSent"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          class="animate-spin h-5 w-5 mr-0"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"
          />
        </svg>
        <span v-if="!isFormSent">Update Auction</span>
      </button>
    </div>
  </form>
</template>

<script>
import { useStore } from 'vuex';
import { onMounted, computed, ref, watch, reactive } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import useLocaleDateValidation from '../../hooks/validateLocaleDate';
import useFormValidation from '../../hooks/validateForm';

export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();

    const isFormSent = ref(false);

    const auctionId = route.params.id;
    onMounted(async () => {
      await store.dispatch('auctions/findById', auctionId);
      await store.dispatch('categories/fetchCategories');
    });
    const auction = computed(() => store.getters['auctions/getViewedAuction']);
    const categories = computed(
      () => store.getters['categories/getCategories']
    );
    const categoryId = ref(null);

    const inputTitle = ref(auction.value.name);
    const inputCategory = ref(auction.value.category.categoryName);
    const inputPrice = ref(auction.value.price);

    const inputEndDate = ref(auction.value.endDate.split(' ').join('T'));
    const inputImageUrl = ref(auction.value.imageUrl);
    const inputDescription = ref(auction.value.description);

    const selectedCategory = ref(auction.value.category.categoryName);

    const activeUser = computed(() => store.getters['auth/getUser']);

    // hooks
    const [isLocaleDateValid, validateLocaleDate] = useLocaleDateValidation(); // makes sure the chosen date is not in the past
    const [isFormValid, validateForm] = useFormValidation();

    const updateAuction = async () => {
      if (!activeUser.value) {
        return;
      }
      const chosenCategory = categories.value.find(
        (c) => c.categoryName === selectedCategory.value
      );
      if (!chosenCategory) {
        isFormValid.value = 'invalid';
        return;
      }
      const auctionData = {
        auctionId: auctionId,
        payload: {
          userId: activeUser.value.userId,
          name: inputTitle.value,
          categoryId: chosenCategory.categoryId,
          description: inputDescription.value,
          price: inputPrice.value,
          endDate: inputEndDate.value.split('T').join(' '),
          imageUrl: inputImageUrl.value,
        },
      };
      validateLocaleDate(auctionData.payload.endDate);
      if (!isLocaleDateValid.value) {
        isFormValid.value = 'invalid';
        return;
      }
      validateForm(auctionData.payload); // form hook
      if (isFormValid.value === 'valid') {
        isFormSent.value = true;
        await store
          .dispatch('auctions/updateAuction', auctionData)
          .then((res) => {
            if (res.status === 200) {
              isFormSent.value = false;
              router.push({
                name: 'AuctionItemPageContainer',
                params: { id: res.data.auctionItemId },
              });
            } else {
              // show error message "something went wrong"
            }
          });
      } else {
        // form is not valid
      }
    };

    return {
      auction,
      categories,
      inputTitle,
      inputCategory,
      inputPrice,
      inputEndDate,
      inputImageUrl,
      inputDescription,
      updateAuction,
      categoryId,
      isFormSent,
      isFormValid,
      selectedCategory,
    };
  },
};
</script>

<style>
</style>