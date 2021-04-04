import Api from "./Api";

export default {

    getAuctions(query) {
        return Api().get(`auctions?${query}`)
    },
    findById(id) {
        return Api().get(`auctions/${id}`)
    },
    deleteById(id) {
        return Api().delete(`auctions/${id}`)
    },
    post(data) {
        return Api().post(`auctions`, data)
    },
    closeAuction(id) {
        return Api().post(`auctions/${id}/close`)
    },
    update(id, data) {
        return Api().put(`auctions/${id}`, data)
    }
}