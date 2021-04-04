<template>
  <section class="grid grid-cols-1 lg:grid-cols-2">
    <!-- left side -->
    <div class="bg-dark-green flex justify-center">
      <div class="pt-16 lg:pb-24 md:pb-20 pb-24 my-3">
        <!-- dollar sign icon -->
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          class="h-24 w-24 text-main-color mx-auto"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="0.5"
            d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
          />
        </svg>
        <!-- dollar sign icon end -->

        <!-- text -->
        <div class="uppercase">
          <p class="text-white pt-3 text-center">Make money Instantly!</p>
          <h2 class="text-4xl md:text-5xl text-white pt-2 font-bold">
            Start <span class="text-main-color">Selling</span>
          </h2>
        </div>
        <div class="flex justify-center items-center pt-8">
          <button class="py-2 px-5 bg-main-color rounded-md text-white">
            Open Your Shop!
          </button>
        </div>
      </div>
    </div>
    <!-- right side -->
    <div class="bg-blue-gradient flex justify-center">
      <div class="pt-16 lg:pb-16 md:pb-20 pb-24 my-3">
        <!-- price tag icon -->
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          class="h-24 w-24 text-white mx-auto"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="0.5"
            d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z"
          />
        </svg>
        <!-- price tag icon end -->

        <!-- text -->
        <div class="uppercase">
          <p class="text-white pt-3 text-center">Find Anything You Want</p>
          <h2 class="text-4xl md:text-5xl text-white pt-2 font-bold">
            Start Buying
          </h2>
        </div>
        <div class="flex justify-center items-center pt-8">
          <BaseButton
            background="bg-gray-800"
            class="text-white"
            title="Register Now"
          />
        </div>
      </div>
    </div>
  </section>
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