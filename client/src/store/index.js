import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import authModule from "./auth";
import auctionsModule from "./auctions";
import categoriesModule from "./categories"

const store = createStore({
    modules: {
        auth: authModule,
        auctions: auctionsModule,
        categories: categoriesModule
    },
    plugins: [createPersistedState()],
    state() {
        return {

        }
    },
    mutations: {

    },
    actions: {

    },
    getters: {

    }
})

export default store;