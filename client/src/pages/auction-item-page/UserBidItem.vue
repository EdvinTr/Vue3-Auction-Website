<template>
  <div class="shadow-lg w-full p-4 my-4">
    <div class="flex justify-between">
      <!-- username and amount -->
      <div class="italic">
        {{ usersFullName }} <span class="text-main-color font-bold">bid</span>
        <span class="ml-1 font-bold">${{ bidAmount }}</span>
      </div>
      <!--  date -->
      <div>{{ creationDate }}</div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';

export default {
  props: {
    bidInfo: Object,
  },
  setup(props) {
    const user = computed(() => props.bidInfo.user);
    const usersFullName = computed(
      () => `${user.value.firstName} ${user.value.lastName}`
    );

    const bidAmount = computed(() => {
      return props.bidInfo.amount
        .toString()
        .replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    });

    const creationDate = computed(() => props.bidInfo.creationDate);

    return {
      usersFullName,
      bidAmount,
      creationDate,
    };
  },
};
</script>