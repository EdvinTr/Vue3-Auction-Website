<template>
  <section>
    <!-- arrow icon and text -->
    <div class="pt-20 lg:pt-32">
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
      <div class="text-center">
        <h5 class="text-4xl text-gray-800 text-center font-bold pt-4">
          <span class="text-main-color">Client </span>Feedbacks
        </h5>
        <p class="w-96 mx-auto pt-4 text-gray-600 px-10 md:px-0">
          Lorem ipsum, dolor sit amet consectetur adipisicing elit. Consequatur
          saepe, corporis voluptas.
        </p>
      </div>
    </div>
    <!-- grid -->
    <div
      class="lg:container lg:mx-auto grid grid-cols-1 pt-8 lg:pt-24 md:grid-cols-2 xl:grid-cols-3"
    >
      <!-- card list start -->
      <ClientFeedBackCardList />
    </div>
    <!-- grid end -->

    <!-- gallery buttons -->
    <div class="flex justify-center space-x-4 pt-20">
      <button
        class="focus:outline-none border-2 border-main-color rounded-full w-5 h-5"
      ></button>
      <button
        class="focus:outline-none bg-main-color rounded-full w-5 h-5"
      ></button>
      <button
        class="focus:outline-none border-2 border-main-color rounded-full w-5 h-5"
      ></button>
    </div>
  </section>
</template>

<script>
import ClientFeedBackCardList from './ClientFeedBackCardList.vue';

export default {
  components: {
    ClientFeedBackCardList,
  },
};
</script>