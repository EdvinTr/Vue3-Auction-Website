import UserService from "../../services/UserService";

export default {
    namespaced: true,
    state() {
        return {
            user: null,
            token: null,
            isUserLoggedIn: false,
        }
    },
    mutations: {
        SET_LOGIN(state, payload) {
            state.user = payload.user;
            state.token = payload.token;
            state.isUserLoggedIn = true;
        },
        SET_LOGOUT(state) {
            state.user = null;
            state.token = null;
            state.isUserLoggedIn = false;
        }
    },

    actions: {
        async login({ commit }, payload) {
            const requestBody = {
                firstName: payload.user.firstName,
                lastName: payload.user.lastName,
                profileImageUrl: payload.user.profileImageUrl,
                email: payload.user.email,
                token: payload.token,
            };
            try {
                const { data } = await UserService.oAuth2Login(requestBody);
                const newPayload = {
                    user: data,
                    token: data.token
                };
                commit("SET_LOGIN", newPayload);

            } catch (err) {
                console.log(err);
            }
        },
        async logout({ commit }) {
            commit("SET_LOGOUT");
        }
    },

    getters: {
        getUser: state => state.user,
        isUserLoggedIn: state => state.isUserLoggedIn,
        getToken: state => state.token,

        getUserRole(state) {
            if (!state.user) {
                return false;
            }
            return state.user.role === "ADMIN" ? true : false;
        }
    }
}