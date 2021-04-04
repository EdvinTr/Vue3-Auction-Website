<template>
  <footer class="py-20 bg-dark-green">
    <!-- container -->
    <div class="container mx-auto px-20 lg:px-0">
      <div
        class="text-center lg:grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-4 text-white gap-20"
      >
        <div>
          <div class="lg:text-left">
            <!-- company name  -->
            <div>
              <div class="text-4xl md:text-3xl lg:text-2xl font-bold">
                <span class="text-main-color">Dream</span> Auction
              </div>
              <span class="tracking-widest">sell your old goods</span>
            </div>
            <!-- company text -->
            <p
              class="text-gray-300 mt-4 text-center lg:text-left pl-20 pr-20 lg:pl-0 xl:text-sm"
            >
              Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eveniet
              itaque ad sapiente possimus labore quisquam accusamus.
            </p>
            <!-- company text end  -->

            <!-- mail icon and text -->
            <div class="flex justify-center space-x-2 pt-4 lg:justify-start">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                class="w-6 h-6"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1"
                  d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
                />
              </svg>
              <span class="text-main-color">dream.auction@gmail.com</span>
            </div>

            <!-- globe icon and text -->
            <div class="flex justify-center space-x-2 pt-4 lg:justify-start">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                class="w-6 h-6"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1"
                  d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                />
              </svg>
              <span class="text-main-color">www.leetcode.com</span>
            </div>

            <!-- phone icon and text -->
            <div class="flex justify-center space-x-2 pt-4 lg:justify-start">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                class="w-6 h-6"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="1"
                  d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"
                />
              </svg>
              <span>+880193208432</span>
            </div>
          </div>
        </div>

        <!-- Our Community section -->
        <div class="mt-20 lg:mt-0 lg:text-left">
          <h6 class="text-2xl lg:text-xl font-bold">Our Community</h6>
          <ul class="lg:pt-10">
            <li
              v-for="item in communityItems"
              :key="item.id"
              class="flex items-center justify-center lg:justify-start hover:text-main-color cursor-pointer mt-4 lg:mt-0"
            >
              <span class="hidden lg:inline-block"
                ><RightArrow class="-ml-3" /></span
              >{{ item.title }}
            </li>
          </ul>
        </div>

        <!-- Help and FAQs section -->
        <div class="mt-20 lg:mt-0 lg:text-left">
          <h6 class="text-2xl lg:text-xl font-bold">Help and FAQs</h6>
          <ul class="lg:pt-10">
            <li
              v-for="item in helpAndFaqsItems"
              :key="item.id"
              class="flex items-center justify-center lg:justify-start hover:text-main-color cursor-pointer mt-4 lg:mt-0"
            >
              <span class="hidden lg:inline-block"
                ><RightArrow class="-ml-3" /></span
              >{{ item.title }}
            </li>
          </ul>
        </div>

        <!-- Current Posts section -->
        <div class="mt-20 lg:mt-0 lg:text-left">
          <h6 class="text-xl font-bold">Current Posts</h6>
          <div class="flex justify-center mt-6 lg:mt-10 lg:justify-start">
            <div class="space-y-10 lg:space-y-6">
              <!-- post container start -->
              <div
                class="lg:flex space-x-4 justify-center lg:relative"
                v-for="num in 2"
                :key="num"
              >
                <img
                  class="w-20 h-20 mx-auto lg:mx-0"
                  src="https://images.unsplash.com/photo-1611187400820-b3e1beac9b6a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80"
                  alt=""
                />
                <p class="mt-4 lg:mt-0 w-40 lg:w-auto">
                  Section 1.10.32 of "de Finibus Bonorum...
                </p>
                <div
                  class="mt-2 lg:mt-0 lg:absolute bottom-1 left-20 bg-main-color rounded-xl text-sm lg:px-1"
                >
                  11 minutes ago
                </div>
              </div>
              <!-- post container end -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- container end -->
  </footer>

  <!-- copyright footer -->
  <div style="background-color: #202325">
    <div class="container mx-auto">
      <div class="py-5 text-white text-sm text-center lg:text-left">
        ©<span class="text-main-color mx-1">Dream</span>Auction - All Rights
        Reserved 2021
      </div>
    </div>
  </div>
</template>

<script>
export default {
  setup() {
    const communityItems = [
      { id: 1, title: 'Buying and Selling' },
      { id: 2, title: 'Job Opportunities' },
      { id: 3, title: 'Emerald Forum' },
      { id: 4, title: 'How to Join us' },
      { id: 5, title: 'Emerald Blog' },
      { id: 6, title: 'Free Goods' },
    ];
    const helpAndFaqsItems = [
      { id: 7, title: 'Terms and Conditions' },
      { id: 8, title: 'Security Information' },
      { id: 9, title: 'Products Licenses' },
      { id: 10, title: 'Help Center' },
      { id: 11, title: 'FAQs' },
    ];
    return {
      communityItems,
      helpAndFaqsItems,
    };
  },
};
</script>