<template>
  <div class="container mx-auto p-4 pt-12 h-screen bg-white">
    <div
      class="max-w-3xl shadow-lg mx-auto p-4 lg:pr-32 lg:pl-32 md:pr-20 md:pl-20 sm:pr-12 sm:pl-12 pr-4 pl-4 rounded-lg"
    >
      <h3 class="text-center text-3xl font-bold text-gray-700 mt-12">
        Sign In With
      </h3>

      <!-- OAuth providers  -->
      <div class="mt-12 flex space-x-4 font-bold">
        <OAuthProviderBadge
          provider-name="Facebook"
          class="hover:bg-blue-800 hover:opacity-90"
        />
        <OAuthProviderBadge
          provider-name="Google"
          class="hover:bg-gray-100"
          @click="loginWithGoogle"
        />
      </div>

      <!-- Username input -->
      <div class="mt-12 flex-col space-y-2">
        <label for="username" class="font-bold text-gray-800">Username</label>
        <input
          autocomplete="off"
          type="text"
          id="username"
          class="w-full border border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>

      <!-- Password inputs -->
      <div class="mt-3 flex-col space-y-2">
        <label for="password" class="font-bold text-gray-800">Password</label
        ><span
          class="text-sm text-gray-400 ml-2 border-b border-gray-300 cursor-pointer hover:text-gray-500"
          >Forgot?</span
        >
        <input
          type="password"
          id="password"
          class="w-full border border-gray-200 bg-gray-100 p-3 rounded-lg focus:outline-none focus:bg-gray-200"
        />
      </div>
      <!-- Sign In button -->
      <button
        class="mt-4 w-full bg-dark-green text-gray-200 p-4 rounded-lg focus:outline-none active:bg-gray-800"
      >
        Sign In
      </button>
      <div
        class="mt-12 pb-3 sm:flex text-center space-x-1 text-gray-500 sm:justify-center cursor-pointer hover:text-gray-700"
      >
        <!-- Not a member text and signup -->
        <div>Not a member?</div>
        <span class="underline" style="text-underline-offset: 0.16rem"
          >Sign up now</span
        >
      </div>
    </div>
  </div>
</template>

<script>
import OAuthProviderBadge from '../../components/oauth-provider-badge/OAuthProviderBadge.vue';
export default {
  components: {
    OAuthProviderBadge,
  },
  methods: {
    async loginWithGoogle() {
      const googleUser = await this.$gAuth.signIn();
      if (!googleUser) {
        return null;
      }
      const payload = {
        user: {
          firstName: googleUser.getBasicProfile().getGivenName(),
          lastName: googleUser.getBasicProfile().getFamilyName(),
          profileImageUrl: googleUser.getBasicProfile().getImageUrl(),
          email: googleUser.getBasicProfile().getEmail(),
        },
        token: googleUser.getAuthResponse().access_token,
      };
      await this.$store.dispatch('auth/login', payload);
      this.$router.push('/');
    },
  },
};
</script>

