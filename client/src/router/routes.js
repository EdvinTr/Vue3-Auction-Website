const routes = [
    {
        name: "FrontPage",
        path: '/',
        component: () => import("../pages/front-page/FrontPageContainer.vue"),
    },
    {
        name: "Login",
        path: "/login",
        component: () => import("../pages/login-page/Login.vue"),
    },
    {
        name: "CreateAuction",
        path: "/create-auction",
        component: () => import("../pages/create-auction/CreateAuction.vue"),
        meta: {
            requiresAuth: true
        }
    },
    {
        name: "UpdateAuction",
        path: "/auctions/:id/update",
        component: () => import("../pages/update-auction/UpdateAuctionContainer.vue"),
        meta: {
            requiresAuth: true
        }
    },
    {
        name: "AuctionPageContainer",
        path: "/auctions",
        component: () => import("../pages/all-auctions-page/AuctionPageContainer.vue"),
    },
    {
        name: "AuctionItemPageContainer",
        path: "/auctions/items/:id",
        component: () => import("../pages/auction-item-page/AuctionItemPageContainer.vue"),
    },

]

export default routes;