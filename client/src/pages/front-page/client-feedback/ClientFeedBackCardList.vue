<template>
  <ClientFeedBackCard
    v-for="client in clientInfo"
    :key="client.image"
    :profile-image-url="client.image"
    :client-name="client.clientName"
  />
</template>

<script>
import ClientFeedBackCard from './ClientFeedbackCard.vue';
export default {
  components: {
    ClientFeedBackCard,
  },
  setup() {
    const clientInfo = [
      {
        image:
          'https://images.unsplash.com/photo-1531427186611-ecfd6d936c79?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=934&q=80',
        clientName: 'Adam West',
      },

      {
        image:
          'https://images.unsplash.com/photo-1542103749-8ef59b94f47e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80',
        clientName: 'Linda Williams',
      },
      {
        image:
          'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80',
        clientName: 'Sandy Smith',
      },
    ];

    return {
      clientInfo,
    };
  },
};
</script>