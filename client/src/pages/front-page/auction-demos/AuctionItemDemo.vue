<template>
  <article
    class="max-w-md mx-auto shadow-md cursor-pointer hover:shadow-lg transition duration-100"
  >
    <div>
      <!-- top overlay -->
      <div class="relative">
        <div
          class="absolute top-0 bg-dark-blue-gradient opacity-90 h-12 w-full"
        ></div>
        <div class="absolute p-3 w-full">
          <div class="flex items-center justify-between">
            <img src="../../../assets/logo.png" class="w-6 h-6 mr-2" alt="" />
            <div class="absolute left-11">
              <div class="text-md font-bold text-white">
                <span class="text-main-color">Dream</span> Auction
              </div>
            </div>
            <div class="absolute right-2 text-red-500 text-lg">
              {{ visibleTime }}
            </div>
          </div>
        </div>

        <!-- image -->
        <img :src="imageUrl" class="object-cover h-64 w-full" alt="shoes" />
      </div>
      <!-- text container -->
      <div class="p-4 mt-2 px-6">
        <h3 class="text-gray-800 text-left text-2xl font-extrabold">
          Lorem ipsum dolor sit amet
        </h3>
        <p class="pt-4 text-gray-700 lg:h-36 md:h-24">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit
          exercitationem blanditiis libero in, corporis soluta tempore omnis
          minima impedit fugit enim sunt perspiciatis.
        </p>
        <div class="text-sm pt-10">
          Seller: <span class="font-bold">Edvin Trönnberg</span>
        </div>
        <!-- pricing tag  -->
        <div
          class="flex items-center space-x-4 pt-4 text-sm pb-3 justify-between"
        >
          <div class="flex space-x-4">
            <div
              class="flex items-center space-x-1 font-bold bg-dark-green w-32 rounded-full justify-center py-1 text-white"
            >
              <!-- dollar sign icon -->
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                class="h-6 w-6 text-main-color"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                />
              </svg>
              <!-- auction price  -->
              <span class="text-main-color">{{ price }}</span>
            </div>
            <div class="flex items-center">
              <span
                class="bg-gray-200 py-2 rounded-full w-32 text-center uppercase font-bold"
                >{{ formatBidsString }}</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- text container end -->
  </article>
</template>

<script>
import { ref, computed } from 'vue';
import useTime from '../../../hooks/time';

export default {
  props: {
    endDate: String,
    bids: Number,
    imageUrl: String,
    price: Number,
  },
  setup(props) {
    const auctionTimeLeft = ref(null);

    const formatBidsString = computed(() => {
      return props.bids === 1 ? props.bids + ' bid' : props.bids + ' bids';
    });

    const [visibleTime, calcLocaleDateDifference] = useTime(props.endDate);

    calcLocaleDateDifference();

    return {
      auctionTimeLeft,
      formatBidsString,
      visibleTime,
    };
  },
};
</script>

<style scoped>
.bg-dark-blue-gradient {
  background: linear-gradient(
    90deg,
    rgba(9, 117, 121, 1) 0%,
    rgba(0, 0, 0, 1) 0%,
    rgb(0 78 71) 100%
  );
}
</style>