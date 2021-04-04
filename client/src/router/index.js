import { createRouter, createWebHistory } from "vue-router";
import routes from "./routes";
import store from "../store";

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior(_, _2, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return { x: 0, y: 0 }
        }
    },


});

router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const isUserLoggedIn = store.getters["auth/isUserLoggedIn"];

    if (requiresAuth && !isUserLoggedIn) {
        next({ name: 'AuctionPageContainer' })
    } else {
        next();

    }

})

export default router;