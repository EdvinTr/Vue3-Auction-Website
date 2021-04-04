<template>
  <div
    class="rounded-lg shadow-md border border-gray-300 flex items-center space-x-3 w-2/4 p-4 justify-center cursor-pointer"
    :class="[backgroundColor, textColor]"
  >
    <img
      :src="`/src/assets/svg/${authProviderProperties.image}`"
      class="h-7 w-7"
      alt="provider"
    />
    <span>{{ providerName }}</span>
  </div>
</template>

<script>
export default {
  props: {
    providerName: {
      type: String,
      required: true,
    },
  },
  computed: {
    /* Returns the appropriate styles for different oAuth2 providers */
    authProviderProperties() {
      switch (this.providerName) {
        case 'Facebook':
          return {
            name: 'Facebook',
            bgc: 'bg-facebook',
            image: 'facebook.svg',
            textColor: 'text-gray-100',
          };
        case 'Google':
          return {
            name: 'Google',
            bgc: 'bg-white',
            image: 'google-symbol.svg',
            textColor: 'text-gray-600',
          };
      }
    },
    textColor() {
      return this.authProviderProperties.textColor;
    },
    backgroundColor() {
      return this.authProviderProperties.bgc;
    },
  },
};
</script>

<style scoped>
.background-color-facebook {
  background-color: #3b5997;
}
.bg-google {
  background-color: white;
}
</style>