import Api from "./Api";

export default {
    postBid(auctionId, data) {
        return Api().post(`auctions/${auctionId}`, data)
    }
}