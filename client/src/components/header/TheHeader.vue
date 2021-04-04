<template>
  <header class="h-32 bg-dark-green flex items-center">
    <div class="container xl:max-w-screen lg:mx-auto px-4">
      <div class="p-0 text-white flex items-center justify-between">
        <!-- left side -->
        <!-- logo and company name -->
        <router-link :to="{ name: 'FrontPage' }" class="">
          <div class="flex items-center">
            <img src="../../assets/logo.png" class="w-16 h-16 mr-4" alt="" />
            <div>
              <div class="text-4xl font-bold">
                <span class="text-main-color">Dream</span> Auction
              </div>
              <span class="tracking-widest">sell your old goods</span>
            </div>
          </div>
        </router-link>
        <!-- logo end -->

        <!-- right side -->
        <div class="lg:flex items-center space-x-5 hidden">
          <!-- login & register buttons -->
          <button
            v-if="!isUserLoggedIn"
            class="py-2 px-6 rounded-md bg-main-color hidden lg:inline-block"
          >
            Register
          </button>
          <router-link
            v-if="!isUserLoggedIn"
            :to="{ name: 'Login' }"
            class="py-2 px-6 rounded-md bg-blue-500 hidden xl:inline-block"
          >
            Login
          </router-link>
          <button
            v-if="isUserLoggedIn"
            @click="handleClickLogout"
            class="py-2 px-6 rounded-md bg-blue-500 hidden xl:inline-block"
          >
            <svg
              v-if="isLoggingOut"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              class="animate-spin h-5 w-5 mr-0"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"
              />
            </svg>
            <span v-if="!isLoggingOut">Logout</span>
          </button>
          <router-link
            v-if="isUserLoggedIn"
            :to="{ name: 'CreateAuction' }"
            class="py-2 px-6 rounded-md bg-main-color hidden xl:inline-block"
          >
            Create Auction
          </router-link>
          <HeaderIcons v-if="isUserLoggedIn" />
          <div
            class="flex items-center space-x-4 cursor-pointer"
            v-if="isUserLoggedIn"
          >
            <!-- profile image -->
            <img
              :src="userProfileImage"
              class="hover:opacity-80 rounded-full w-12 h-12 ring-4 ring-main-color ring-offset-2 ring-offset-yellow-800"
              alt="profile"
            />
            <div class="flex-wrap">
              <!-- users real name -->
              <div class="text-gray-100 flex items-center">
                {{ username }}

                <!-- dropdown arrow start -->
                <span
                  ><svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    class="w-4 h-4 text-gray-400 ml-2"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M19 9l-7 7-7-7"
                    /></svg
                ></span>
                <!-- dropdown arrow end -->
              </div>

              <!-- users current cash -->
              <div class="text-main-color font-bold">$745.00</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import HeaderIcons from './HeaderIcons.vue';
import { useStore } from 'vuex';
import { ref, computed } from 'vue';

export default {
  components: {
    HeaderIcons,
  },
  setup() {
    const store = useStore();

    const isLoggingOut = ref(false);
    const isUserLoggedIn = computed(() => store.getters['auth/isUserLoggedIn']);
    const user = computed(() => store.getters['auth/getUser']);

    const username = computed(
      () => `${user.value.firstName} ${user.value.lastName}`
    );
    const userProfileImage = computed(() => user.value.profileImageUrl);

    return {
      isLoggingOut,
      username,
      userProfileImage,
      isUserLoggedIn,
    };
  },
  computed: {
    currentRouteName() {
      return this.$router.currentRoute.value.name;
    },
  },
  methods: {
    async handleClickLogout() {
      try {
        this.isLoggingOut = true;
        await this.$gAuth.signOut();
        await this.$store.dispatch('auth/logout');

        if (this.currentRouteName !== 'FrontPage') {
          this.$router.push('/');
        }
        this.isLoggingOut = false;
      } catch (err) {
        console.log(err);
      }
    },
  },
};
</script>