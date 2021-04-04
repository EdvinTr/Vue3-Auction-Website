<template>
  <!-- clock icon -->
  <div>
    <div class="space-y-1 xl:space-y-3">
      <div class="flex items-center xl:justify-center xl:flex-col space-x-1">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          class="w-8 h-8 xl:w-16 xl:h-16 text-main-color"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="1"
            d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"
          />
        </svg>
        <div class="text-2xl font-bold" v-if="!isAuctionClosed">
          {{ endTimeStamp }}
        </div>
      </div>

      <div class="xl:text-center xl:space-y-2">
        <p class="italic text-gray-700" v-if="!isAuctionClosed">
          Ends {{ endDateString }} at {{ endTimeStamp }}
        </p>
        <p class="text-red-500 font-bold text-lg" v-if="isAuctionClosed">
          Auction Closed
        </p>
      </div>
    </div>
    <!-- blue box with bid count -->
    <div class="border-b border-gray-300 w-full py-3"></div>
    <div class="bg-blue-50 w-full mt-4 text-center py-4 xl:h-80">
      <div class="text-3xl text-gray-700">{{ totalBids }}</div>
      <div class="italic text-gray-700">bids</div>
    </div>
  </div>
</template>

<script>
import { computed, toRefs } from 'vue';

export default {
  props: {
    auction: Object,
    isAuctionClosed: Boolean,
  },
  setup(props) {
    const { isAuctionClosed } = toRefs(props);

    const endTimeStamp = computed(() => {
      const newDate = new Date(props.auction.endDate);
      let hours = newDate.getHours();
      let minutes = newDate.getMinutes();
      let seconds = newDate.getSeconds();

      minutes = minutes < 10 ? '0' + minutes : minutes;
      seconds = seconds < 10 ? '0' + seconds : seconds;

      return `${hours}:${minutes}`;
    });

    const endDateString = computed(() => {
      return new Date(props.auction.endDate).toDateString();
    });

    const totalBids = computed(() => {
      return props.auction.bids.length;
    });
    return {
      endTimeStamp,
      endDateString,
      totalBids,
      isAuctionClosed,
    };
  },
};
</script>

<style>
</style>