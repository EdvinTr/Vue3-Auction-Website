<template>
  <div class="mt-32 bg-blue-gradient">
    <!-- overall container -->
    <div
      class="py-12 lg:flex lg:items-center lg:justify-between container mx-auto"
    >
      <div class="lg:flex lg:items-center lg:space-x-6">
        <!-- mail icon  -->
        <div
          class="bg-dark-green w-24 h-24 flex items-center justify-center rounded-full mx-auto"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            class="w-16 h-16w-16 text-main-color"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="0.8"
              d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
            />
          </svg>
        </div>
        <!-- mail icon end -->

        <!-- text -->
        <div class="text-gray-100 pt-8 lg:pt-0 text-center lg:text-left">
          <div class="text-2xl xl:text-3xl lg:font-bold">
            Subscribe to our Newsletter
          </div>
          <p class="tracking-wide">And receive the latest news and offers</p>
        </div>
      </div>

      <!-- input and button form -->
      <form class="pt-8 lg:pt-0" @submit.prevent>
        <div class="flex justify-center lg:inline-block px-10 md:px-0">
          <input
            class="w-96 p-3 focus:outline-none rounded-l-md focus:bg-gray-100"
            type="text"
            name="email"
            id="email"
            placeholder="Enter your email here..."
            autocomplete="off"
          />
        </div>
        <div
          class="flex justify-center pt-5 lg:pt-0 lg:inline-block px-10 md:px-0"
        >
          <button class="bg-black text-white w-96 lg:w-48 py-3 rounded-r-md">
            Subscribe!
          </button>
        </div>
      </form>
      <!-- form end -->
    </div>
  </div>
</template>

<style scoped>
.bg-blue-gradient {
  background: rgb(9, 117, 121);
  background: linear-gradient(
    90deg,
    rgba(9, 117, 121, 1) 0%,
    rgba(5, 156, 129, 1) 0%,
    rgba(0, 172, 255, 1) 100%
  );
}
</style>