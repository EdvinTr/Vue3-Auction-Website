<template>
  <div
    class="shadow-xl border-t w-96 border-gray-50 mx-auto h-auto px-10 mb-8 md:mb-0"
  >
    <!-- card profile image -->
    <img
      :src="profileImageUrl"
      class="rounded-full w-24 h-24 object-cover mx-auto mt-16"
      alt=""
    />
    <!-- card text container -->
    <div class="text-center text-gray-800">
      <div class="text-2xl font-bold pt-3">{{ clientName }}</div>
      <div class="uppercase text-main-color font-bold text-lg">Winner</div>
      <div class="mt-6 border-t border-gray-400 w-10 mx-auto"></div>
      <p class="pt-5">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, obcaecati!
        Odio voluptate amet asperiores, cum atque ullam in obcaecati temporibus
        id itaque. Dolores sunt facilis, deserunt temporibus saepe incidunt
        ducimus.
      </p>
      <div class="flex items-center justify-center pt-5 pb-14">
        <svg
          v-for="num in 5"
          :key="num"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 20 20"
          fill="currentColor"
          class="text-main-color w-6 h-6"
        >
          <path
            d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
          />
        </svg>
      </div>
    </div>
    <!-- card text end -->
  </div>
  <!-- card end -->
</template>

<script>
export default {
  props: {
    profileImageUrl: String,
    clientName: String,
  },
};
</script>