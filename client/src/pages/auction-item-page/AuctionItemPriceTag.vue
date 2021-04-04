<template>
  <!-- dark background container -->
  <div
    class="flex items-center space-x-1 font-bold bg-dark-green w-auto px-4 h-10 rounded-full xl:py-1 text-white"
  >
    <!-- dollar sign icon -->
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      class="xl:h-7 xl:w-7 h-6 w-6 text-main-color"
      stroke="currentColor"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2"
        d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
      />
    </svg>
    <!-- price text -->
    <span class="text-main-color text-md lg:text-lg">{{ price }}</span>
  </div>
</template>

<script>
export default {
  props: {
    price: String,
  },
};
</script>