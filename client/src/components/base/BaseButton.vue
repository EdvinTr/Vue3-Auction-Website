<template>
  <button class="py-2 px-6 rounded-md" :class="[background]">
    {{ title }}
  </button>
</template>

<script>
export default {
  props: {
    title: String,
    background: String,
  },
};
</script>