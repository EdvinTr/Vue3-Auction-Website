<template>
  <div>
    <div class="container mx-auto h-auto">
      <div class="px-4"></div>

      <div class="flex justify-between px-4 py-8">
        <!-- Category menu here -->
        <div class="w-1/5 hidden lg:block pr-4">
          <ul class="space-y-2">
            <li
              v-for="category in categories"
              @click="sortByCategory(category.categoryName)"
              :key="category"
              class="flex items-center hover:text-main-color cursor-pointer text-lg bg-gray-100 px-2 py-2"
            >
              {{ category.categoryName }}
              <div class="w-full flex justify-end">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  class="w-4 h-4"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M19 9l-7 7-7-7"
                  />
                </svg>
              </div>
            </li>
          </ul>
        </div>

        <!-- Auction items here -->
        <div class="w-full lg:w-4/5 mx-auto lg:mx-0 lg:flex-col h-auto">
          <input
            type="text"
            class="border border-gray-300 rounded-md w-full p-2 mb-8 focus:outline-none focus:bg-gray-100 focus:ring-0 focus:border-main-color"
            placeholder="Search"
          />
          <!-- pageable buttons -->
          <PageableLogic />
          <h2 class="font-bold text-2xl" v-if="auctions.length === 0">
            No results
          </h2>
          <!-- auction item cards -->
          <router-link
            v-for="item in auctions"
            :key="item.auctionItemId"
            :to="{
              name: 'AuctionItemPageContainer',
              params: { id: item.auctionItemId },
            }"
          >
            <MiniAuctionItemCard :auction="item" />
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RightArrow from '../../components/RightArrow.vue';
import MiniAuctionItemCard from './MiniAuctionItemCard.vue';
import PageableLogic from './PageableLogic.vue';

import { useStore } from 'vuex';
import { computed, onMounted } from 'vue';

export default {
  components: {
    MiniAuctionItemCard,
    RightArrow,
    PageableLogic,
  },
  setup() {
    const store = useStore();

    const auctions = computed(() => store.getters['auctions/getAuctions']);
    const categories = computed(
      () => store.getters['categories/getCategories']
    );

    onMounted(async () => {
      await store.dispatch('auctions/fetchAuctions');
      await store.dispatch('categories/fetchCategories');
      window.scrollTo(0, 0);
    });
    const sortByCategory = async (category) => {
      await store.dispatch('auctions/fetchAuctions', `&category=${category}`);
    };

    return {
      auctions,
      categories,
      sortByCategory,
    };
  },
};
</script>