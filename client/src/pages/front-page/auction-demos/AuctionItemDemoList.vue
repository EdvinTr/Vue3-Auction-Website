<template>
  <div
    class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 container mx-auto gap-12"
  >
    <AuctionItem
      v-for="item in items"
      :key="item.id"
      :end-date="item.endDate"
      :bids="item.bids"
      :image-url="item.imageUrl"
      :price="item.price"
    />
  </div>
</template>

<script>
import AuctionItem from './AuctionItemDemo.vue';
export default {
  components: {
    AuctionItem,
  },
  setup() {
    const items = [
      {
        id: 0,
        endDate: '2021-03-25 12:45:25',
        bids: 2,
        price: 129,
        imageUrl:
          'https://images.unsplash.com/photo-1579965342575-16428a7c8881?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=999&q=80',
      },
      {
        id: 1,
        endDate: '2021-03-23 23:12:33',
        bids: 2,
        price: 74,
        imageUrl:
          'https://images.unsplash.com/photo-1574182245530-967d9b3831af?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=977&q=80',
      },
      {
        id: 2,
        endDate: '2021-03-23 21:55:11',
        bids: 8,
        price: 55,
        imageUrl:
          'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80',
      },
      {
        id: 3,
        endDate: '2021-03-23 15:21:12',
        bids: 4,
        price: 44,
        imageUrl:
          'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1951&q=80',
      },
      {
        id: 4,
        endDate: '2021-03-23 16:25:04',
        bids: 21,
        price: 19,
        imageUrl:
          'https://images.unsplash.com/photo-1585298723682-7115561c51b7?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80',
      },
      {
        id: 5,
        endDate: '2021-03-20 22:33:51',
        bids: 0,
        price: 8,
        imageUrl:
          'https://images.unsplash.com/photo-1556306510-31ca015374b0?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80',
      },
    ];
    return {
      items,
    };
  },
};
</script>