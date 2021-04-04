<template>
  <div class="container mx-auto p-4">
    <!-- back button -->
    <router-link :to="{ name: 'AuctionPageContainer' }">
      <div class="px-8 py-2 bg-dark-green text-white rounded-md mt-4 mb-8 w-20">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          class="w-4 h-4 text-white"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M10 19l-7-7m0 0l7-7m-7 7h18"
          />
        </svg>
      </div>
    </router-link>
    <!-- card container -->
    <div
      class="shadow-lg w-full h-auto xl:p-4 xl:flex p-4 overflow-hidden relative"
    >
      <!-- trashcan icon -->
      <button
        @click="deleteAuctionById"
        v-if="isUserAdmin"
        class="absolute right-5 top-5 cursor-pointer"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          class="w-9 h-9 xl:w-7 xl:h-7 text-red-500"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
          />
        </svg>
      </button>
      <div class="xl:w-2/6">
        <!-- image url -->
        <img
          :src="imageUrl"
          alt=""
          class="object-cover h-96 w-full xl:w-full"
        />
        <div class="flex space-x-4 py-4">
          <!-- mini images -->
          <img
            :src="imageUrl"
            alt=""
            class="object-cover h-14 md:h-16 lg:h-20 cursor-pointer opacity-50 hover:opacity-100 w-16 lg:w-24 md:w-20"
            v-for="num in 3"
            :class="{ 'opacity-100': num === 1 }"
            :key="num"
          />
        </div>
        <div v-if="!isAuctionClosed">
          <router-link
            :to="{ name: 'UpdateAuction', params: { id: currentRouteId } }"
            v-if="isUserAdmin || isUsersAuction"
          >
            <button
              class="text-white bg-success px-6 py-2 rounded-md focus:outline-none"
              aria-label="update"
            >
              Update
            </button>
          </router-link>
        </div>
      </div>

      <!-- second column  -->
      <div class="xl:pr-6 2xl:pr-0 xl:w-6/12 2xl:w-5/12 xl:pl-4 xl:max-h-96">
        <AuctionItemDetails
          :auction="auction"
          :is-auction-closed="isAuctionClosed"
          @close-auction="closeAuction"
        />
      </div>

      <!-- third column -->
      <div class="w-full xl:w-1/6 p-0 xl:mx-auto xl:pt-0 pt-10 relative">
        <AuctionItemDateDetails
          :auction="auction"
          :is-auction-closed="isAuctionClosed"
        />
      </div>
    </div>
    <!-- bids list goes here -->
    <UserBid v-for="bid in auction.bids" :key="bid.bidId" :bid-info="bid" />
    <div class="min-h-screen"></div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, onMounted, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import UserBid from './UserBidItem.vue';
import AuctionItemDetails from './AuctionItemDetails.vue';
import AuctionItemDateDetails from './AuctionItemDateDetails.vue';

export default {
  components: {
    UserBid,
    AuctionItemDetails,
    AuctionItemDateDetails,
  },
  setup() {
    const auction = computed(() => store.getters['auctions/getViewedAuction']);

    const isAuctionClosed = ref(false);

    //calculate if Auction is closed, go fix backend instead man.
    const calcIsAuctionClosed = () => {
      const now = new Date().getTime();
      let passedDate = new Date(auction.value.endDate).getTime();
      let timeLeft = passedDate - now;
      isAuctionClosed.value = timeLeft < 0;
    };

    onMounted(async () => {
      await store.dispatch('auctions/findById', currentRouteId);

      calcIsAuctionClosed();

      window.scrollTo(0, 0);
    });

    const store = useStore();
    const router = useRouter();
    const route = useRoute();

    const closeAuction = async (id) => {
      await store.dispatch('auctions/closeAuction', id);
      calcIsAuctionClosed();
    };

    const currentRouteId = route.params.id;
    const imageUrl = computed(() => auction.value.imageUrl);

    const isUserAdmin = computed(() => store.getters['auth/getUserRole']);
    const activeUser = computed(() => store.getters['auth/getUser']);
    const isUsersAuction = computed(() => {
      if (!activeUser.value) {
        return false;
      }

      return activeUser.value.userId === auction.value.user.userId;
    });

    const deleteAuctionById = async () => {
      await store.dispatch('auctions/deleteById', currentRouteId);
      router.push({ name: 'AuctionPageContainer' });
    };

    return {
      auction,
      imageUrl,
      deleteAuctionById,
      currentRouteId,
      isUserAdmin,
      isAuctionClosed,
      closeAuction,
      isUsersAuction,
    };
  },
};
</script>