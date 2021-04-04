<template>
  <section class="pt-32">
    <!-- arrow icon start -->
    <div
      class="w-16 h-16 flex items-center justify-center mx-auto rounded-full bg-dark-green"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        class="h-16 w-16 text-gray-200"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="1"
          d="M15 13l-3 3m0 0l-3-3m3 3V8m0 13a9 9 0 110-18 9 9 0 010 18z"
        />
      </svg>
    </div>
    <!-- arrow icon end -->

    <!-- section description  -->
    <div class="text-center">
      <h5 class="text-4xl text-gray-800 text-center font-bold pt-4">
        <span class="text-main-color">Latest </span>Auctions
      </h5>
      <p class="w-96 mx-auto pt-4 text-gray-600 px-10 md:px-0">
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Consequatur
        saepe, corporis voluptas.
      </p>
    </div>
    <!-- description end -->

    <!-- auction cards list -->
    <AuctionItemDemoList class="my-12 xl:my-20" />

    <!-- View all auctions button -->
    <router-link
      :to="{ name: 'AuctionPageContainer' }"
      class="flex justify-center pt-2 md:pt-6 xl:pt-0"
    >
      <BaseButton
        title="View All Auctions"
        background="bg-main-color"
        class="text-white"
      />
    </router-link>
  </section>
</template>

<script>
import AuctionItemDemoList from './AuctionItemDemoList.vue';
export default {
  components: {
    AuctionItemDemoList,
  },
};
</script>