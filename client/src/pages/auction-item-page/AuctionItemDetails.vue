<template>
  <!-- title -->
  <div>
    <div class="flex justify-between items-center">
      <h2 class="text-3xl font-bold text-gray-">
        {{ auctionTitle }}
      </h2>
      <div v-if="isUserAdmin">
        <button
          class="bg-red-500 px-2 py-1 text-white rounded-md"
          v-if="!isAuctionClosed"
          @click="closeAuction"
        >
          Close Auction
        </button>
      </div>
    </div>

    <!-- product ids and stuff -->
    <div class="flex-col pt-4 xl:flex xl:flex-row xl:space-x-4 text-gray-600">
      <div>
        Seller: <span class="font-bold"> {{ sellerFullName }}</span>
      </div>

      <div>Listing ID: {{ auctionId }}</div>
    </div>
    <!-- current price -->
    <div class="flex xl:justify-between items-center space-x-2">
      <div class="text-2xl pt-4 font-bold pb-4">
        <span class="text-main-color">Current</span> Price
      </div>
      <PriceTag :price="formattedPrice" />
    </div>
    <!-- bid increment text -->
    <div
      class="flex space-x-1 xl:space-x-1 text-sm italic xl:space-x-1justify-between items-center pt-0 text-gray-600"
    >
      <div>Bid Increment (US)</div>
      <div>$3.00</div>
    </div>
    <p class="pt-4 xl:pt-12 lg:w-3/5 xl:w-full" style="height: 9.5rem">
      {{ description }}
    </p>
    <!-- input and button -->
    <div class="md:flex md:flex-row items-center md:justify-start pt-8 lg:pt-8">
      <div class="flex">
        <div class="flex items-center text-xl text-gray-400 pr-2">(US)$</div>
        <input
          :disabled="isAuctionClosed"
          v-model.number="inputBid"
          type="number"
          :min="price"
          class="border border-gray-300 p-2 md:p-2 md:w-80 w-full focus:outline-none rounded-md focus:bg-gray-100 focus:ring-0 focus:border-gray-300"
          :class="{
            'focus:border-red-500 border-red-500': isBidInvalid === 'invalid',
          }"
        />
      </div>
      <!-- bid button -->
      <button
        @click="createBid"
        :disabled="isAuctionClosed"
        class="bg-main-color flex items-center justify-center md:ml-4 px-6 py-2 rounded-md text-white mt-4 md:mt-0 w-full md:w-32 focus:outline-none"
        :class="{ 'cursor-not-allowed bg-red-500': isAuctionClosed }"
      >
        <!-- loading spinner when sending bid -->
        <svg
          v-if="isBidSent"
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
        <span v-if="!isBidSent">Bid Now!</span>
      </button>
    </div>
    <!-- error message on bid invalid -->
    <div
      class="text-sm text-red-500 mt-4 lg:mt-2 lg:ml-12 lg:pl-3"
      v-if="isBidInvalid === 'invalid'"
    >
      The bid must be greater than the previous
    </div>
  </div>
</template>

<script>
import PriceTag from './AuctionItemPriceTag.vue';
import { computed, watch, ref, toRefs } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
  components: {
    PriceTag,
  },
  props: {
    auction: Object,
    isAuctionClosed: Boolean,
  },
  emits: ['close-auction'],

  setup(props, { emit }) {
    const { isAuctionClosed } = toRefs(props);

    const route = useRoute();
    const store = useStore();

    const user = computed(() => props.auction.user);

    const sellerFullName = computed(() => {
      return `${user.value.firstName} ${user.value.lastName}`;
    });

    const auctionId = computed(() => props.auction.auctionItemId);
    const description = computed(() => props.auction.description);
    const price = computed(() => props.auction.price);
    const auctionTitle = computed(() => props.auction.name);

    const formattedPrice = computed(() => {
      return props.auction.price
        .toString()
        .replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    });

    const closeAuction = () => {
      emit('close-auction', auctionId.value);
    };

    const isBidInvalid = ref('pending');
    const inputBid = ref(null);

    const isUserAdmin = computed(() => store.getters['auth/getUserRole']);

    watch(inputBid, (newVal, _) => {
      if (!newVal) {
        isBidInvalid.value = 'pending';
      } else if (newVal <= price.value) {
        isBidInvalid.value = 'invalid';
      } else {
        isBidInvalid.value = 'valid';
      }
    });

    const isBidSent = ref(false);
    const currentRouteId = route.params.id;

    // create bid
    const createBid = async () => {
      if (isBidInvalid.value !== 'valid') {
        return;
      }
      if (!store.getters['auth/getUser']) {
        return;
      }
      const payload = {
        auctionId: parseInt(currentRouteId),
        userId: store.getters['auth/getUser'].userId,
        amount: inputBid.value,
      };

      isBidSent.value = true;
      await store.dispatch('auctions/postBid', payload);
      inputBid.value = null;
      isBidSent.value = false;
    }; //create bid end

    return {
      isAuctionClosed,
      sellerFullName,
      auctionId,
      price,
      formattedPrice,
      description,
      auctionTitle,
      inputBid,
      createBid,
      isBidInvalid,
      isBidSent,
      closeAuction,
      isUserAdmin,
    };
  },
};
</script>