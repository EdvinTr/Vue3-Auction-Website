<template>
  <form
    class="container mx-auto p-4 pt-12 h-screen bg-white"
    @submit.prevent="createAuction"
  >
    <div
      class="max-w-3xl pb-6 shadow-lg mx-auto p-4 lg:pr-16 lg:pl-16 md:pr-20 md:pl-20 sm:pr-12 sm:pl-12 pr-4 pl-4 rounded-lg space-y-8"
    >
      <!-- card title -->
      <h3 class="text-center text-3xl font-bold text-gray-700 mt-12">
        Create an auction
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
          <option disabled value="">Please select one</option>
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
          v-if="isAuctionSent"
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
        <span v-if="!isAuctionSent">Create Auction</span>
      </button>
    </div>
  </form>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

import useLocaleDateValidation from '../../hooks/validateLocaleDate';
import useFormValidation from '../../hooks/validateForm';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    const fetchCategories = async () => {
      store.dispatch('categories/fetchCategories');
    };
    onMounted(fetchCategories);
    const categories = computed(
      () => store.getters['categories/getCategories']
    );

    const inputTitle = ref('');
    const categoryId = ref(null);
    const inputPrice = ref('');
    const inputEndDate = ref('');
    const inputImageUrl = ref('');
    const inputDescription = ref('');
    let isAuctionSent = ref(false);

    const selectedCategory = ref('');

    // hooks
    const [isLocaleDateValid, validateLocaleDate] = useLocaleDateValidation();
    const [isFormValid, validateForm] = useFormValidation();

    const activeUser = computed(() => store.getters['auth/getUser']);

    const createAuction = async () => {
      if (!activeUser.value) {
        //check if any user is online
        return;
      }
      const chosenCategory = categories.value.find(
        (c) => c.categoryName === selectedCategory.value
      );
      if (!chosenCategory) {
        // make sure a category is chosen else return
        isFormValid.value = 'invalid';
        return;
      }
      //create payload
      const auctionData = {
        userId: activeUser.value.userId,
        name: inputTitle.value,
        categoryId: chosenCategory.categoryId,
        description: inputDescription.value,
        price: inputPrice.value,
        endDate: inputEndDate.value.split('T').join(' '), //Replace the LocaleDate "T" character with a space
        imageUrl: inputImageUrl.value,
      };
      validateLocaleDate(auctionData.endDate); // Validate the endDate is not in the past
      if (!isLocaleDateValid.value) {
        isFormValid.value = 'invalid';
        return;
      }
      validateForm(auctionData); // validate entire form, meaning no empty values

      if (isFormValid.value === 'valid') {
        isAuctionSent.value = true; // indicate on Create button that form is sent
        await store
          .dispatch('auctions/postAuction', auctionData) //send data to backend
          .then((res) => {
            if (res.status === 201) {
              // if resource was created successfully, navigate to the new route
              isAuctionSent.value = false;
              router.push({
                name: 'AuctionItemPageContainer',
                params: { id: res.data.auctionItemId },
              });
            }
          });
      }
    };

    return {
      createAuction,
      categories,
      inputTitle,
      inputPrice,
      inputEndDate,
      inputImageUrl,
      inputDescription,
      categoryId,
      isFormValid,
      isAuctionSent,
      selectedCategory,
    };
  },
};
</script>