<template>
  <article
    class="w-full lg:h-auto flex-col lg:flex shadow-md p-4 md:p-6 lg:p-2 cursor-pointer hover:shadow-lg"
  >
    <div class="lg:flex lg:space-x-4 justify-between">
      <!-- auction image -->
      <img
        :src="imageUrl"
        class="object-cover w-full h-60 lg:h-56 lg:w-3/5"
        :alt="name"
      />
      <!-- auction info -->
      <div class="lg:space-x-4 w-full">
        <div class="w- flex-col">
          <h3 class="text-xl font-bold pt-4 lg:pt-6">{{ name }}</h3>
          <p>{{ auction.description.slice(0, 50) }}...</p>
          <div class="font-bold flex-col">
            Bids: <span class="font-normal">{{ totalBids }}</span>
            <div>Current Bid:${{ currentBid }}</div>
            <div class="font-medium">
              Category: <span>{{ categoryName }}</span>
            </div>
          </div>
        </div>
      </div>
      <!-- end date -->
      <div class="lg:pt-6 pt-8 text-left lg:text-center lg:pr-4">
        <p>Ends in:</p>
        <div class="font-bold text-red-500">{{ visibleTime }}</div>
        <button class="px-10 mt-2 py-2 bg-main-color rounded-md text-white">
          View
        </button>
      </div>
    </div>
  </article>
</template>

<script>
import { computed, onBeforeMount } from 'vue';
import useTime from '../../hooks/time';

export default {
  props: {
    auction: Object,
  },
  setup(props) {
    onBeforeMount(() => {
      calcLocaleDateDifference();
    });
    const name = computed(() => props.auction.name);
    const totalBids = computed(() => props.auction.bids.length);

    const currentBid = computed(() =>
      props.auction.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
    );

    const imageUrl = computed(() => props.auction.imageUrl);
    const categoryName = computed(() => props.auction.category.categoryName);

    const [visibleTime, calcLocaleDateDifference] = useTime(
      props.auction.endDate
    );

    return {
      name,
      totalBids,
      currentBid,
      imageUrl,
      categoryName,
      visibleTime,
    };
  },
};
</script>