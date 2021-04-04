<template>
  <div>
    <!-- HTML code goes here -->
    <h3>{{ message }}</h3>

    <p>{{ calculate }}</p>
    <button @click="handleClick">Click me</button>

    <input type="text" v-model="inputName" name="" id="" />
    <!-- v-model gets the data from the input field -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: 'Hello World', // This will be shown in the HTML view (browser) !!
      inputName: '',
    };
  },
  methods: {
    // Here we define methods
    handleClick() {
      console.log('Hello World'); // We can bind this to the button in HTML
    },
  },
  computed: {
    // In computed we make calculations, we access it the same as the data property
    calculate() {
      return 5 + 5;
    },
  },
  watch: {
    inputName(newVal, oldVal) {
      // This function gets called everytime the user types in the input field
    },
  },
};
</script>

<style scoped>
/* Scoped only affects this component */

/* Here we add styles */
</style>