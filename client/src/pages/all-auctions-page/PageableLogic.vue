<template>
  <div class="flex justify-start items-center space-x-2">
    <button class="focus:outline-none active:opacity-50"></button>
    <button
      v-for="(num, index) in totalPages"
      @click="handleNewPageNumberClick(index)"
      :key="index"
      class="py-1 px-2 border border-blue-500"
      :class="{ 'bg-blue-500 text-white': index === currentPageNumber }"
    >
      {{ index + 1 }}
    </button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
  setup() {
    const store = useStore();

    const pageInfo = computed(() => store.getters['auctions/getPageInfo']);
    const totalPages = computed(() => pageInfo.value.totalPages);
    const currentPageNumber = computed(() => pageInfo.value.number);

    const handleNewPageNumberClick = async (pageNumber) => {
      await store.dispatch('auctions/fetchAuctions', `&page=${pageNumber}`);
    };

    return {
      totalPages,
      currentPageNumber,
      handleNewPageNumberClick,
    };
  },
};
</script>