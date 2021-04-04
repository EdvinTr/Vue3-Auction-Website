import CategoryService from "../../services/CategoryService";

export default {
    namespaced: true,
    state() {
        return {
            categories: []
        }
    },
    mutations: {
        SET_CATEGORIES(state, payload) {
            state.categories = payload;
        }
    },
    actions: {
        async fetchCategories({ commit }) {
            try {
                const { data } = await CategoryService.getCategories();
                commit("SET_CATEGORIES", data);
            } catch (err) {
                console.log(err);
            }
        }
    },
    getters: {
        getCategories: state => state.categories
    },
}