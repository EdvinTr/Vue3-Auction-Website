<template>
  <div class="h-24 container mx-auto px-4 lg:flex items-center w-full hidden">
    <!-- nav items -->
    <nav
      class="h-full flex items-center space-x-0 font-bold uppercase text-gray-800 w-full"
    >
      <router-link
        to="/"
        class="justify-center flex items-center border-l border-r border-opacity-50 border-gray-300 w-32 h-full bg-main-color text-white"
      >
        Home
      </router-link>
      <router-link
        to="/"
        class="justify-center flex items-center border-r border-opacity-50 border-gray-300 w-48 h-full hover:bg-gray-100"
      >
        How to Shop
      </router-link>
      <router-link
        to="/"
        class="justify-center flex items-center border-r border-opacity-50 border-gray-300 w-40 h-full hover:bg-gray-100"
      >
        Products
      </router-link>
      <router-link
        to="/"
        class="justify-center flex items-center border-r border-opacity-50 border-gray-300 w-40 h-full hover:bg-gray-100"
      >
        Services
      </router-link>
      <router-link
        to="/"
        class="justify-center flex items-center border-r border-opacity-50 border-gray-300 w-48 h-full hover:bg-gray-100"
      >
        Online Goods
      </router-link>
      <router-link
        to="/auctions"
        class="justify-center flex items-center border-r border-opacity-50 border-gray-300 w-40 h-full hover:bg-gray-100"
      >
        Auctions
      </router-link>
    </nav>
    <!-- nav items end -->

    <!-- Hello World -->
    <!-- search input -->
    <div class="hidden xl:flex w-full items-center justify-center relative">
      <input
        class="border border-gray-500 border-opacity-30 2xl:ml-16 xl:ml-4 px-4 py-3 rounded-full w-full focus:outline-none focus:bg-gray-100"
        type="text"
        placeholder="Search product here..."
        name="search"
        id="search"
        autocomplete="off"
      />

      <!-- search icon circle -->
      <div
        class="bg-dark-green rounded-full p-2 absolute right-0.5 cursor-pointer hover:bg-black"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          class="h-7 w-7 text-white"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
          />
        </svg>
      </div>
    </div>
    <!-- search input end -->
  </div>
</template>