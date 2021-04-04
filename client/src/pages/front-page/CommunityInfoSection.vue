<template>
  <section class="grid grid-cols-1 xl:grid-cols-2 pt-32">
    <!-- left side -->
    <div class="flex justify-center bg-gray-900 h-96 box-height-120">
      <!-- text box -->
      <div class="pt-16 lg:pb-16 md:pb-20 pb-24 my-3 2xl:px-60 px-4">
        <div
          class="w-96 2xl:w-full space-y-5 text-center lg:text-left px-10 md:px-0"
        >
          <h6 class="text-4xl text-white 2xl:text-5xl lg:text-left">
            Services for
            <span class="border-b-2 border-main-color border-opacity-80"
              >everyone</span
            >
          </h6>
          <p class="text-gray-300">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Unde
            aperiam voluptatem id quos repellat saepe sed, ipsum voluptatum nisi
            ea ipsam aspernatur corporis.
          </p>
        </div>
        <!-- button with arrow icon -->
        <div
          class="flex md:justify-left justify-center lg:justify-start items-center pt-8"
        >
          <button class="py-2 px-5 bg-main-color rounded-md text-white flex">
            Discover
            <span
              ><svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                class="w-6 h-6 ml-2"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1"
                  d="M14 5l7 7m0 0l-7 7m7-7H3"
                /></svg
            ></span>
          </button>
        </div>
        <!-- button end -->
      </div>
    </div>
    <!-- right side -->
    <div class="bg-blue-gradient flex justify-center h-96 box-height-120">
      <div class="h-full">
        <img
          src="../../assets/cartoonish_image_social.jpg"
          class="object-cover w-full h-full"
          alt="social"
        />
      </div>
    </div>
  </section>
</template>

<style scoped>
.box-height-120 {
  height: 30rem;
}
</style>

