import AuctionItemService from "../../services/AuctionItemService";
import BidService from "../../services/BidService";


export default {
    namespaced: true,
    state() {
        return {
            auctions: [],
            viewedAuction: null,
            pageInfo: null
        }
    },

    mutations: {
        SET_AUCTIONS(state, payload) {
            state.auctions = payload;
        },

        SET_PAGE_INFO(state, payload) {
            state.pageInfo = payload;
        },

        SET_VIEWED_AUCTION(state, payload) {
            state.viewedAuction = payload;
        },
        SET_CREATE_BID(state, payload) {
            state.viewedAuction.bids = payload;
        },
        SET_VIEWED_AUCTION_PRICE(state, payload) {
            state.viewedAuction.price = payload;
        }
    },

    actions: {
        async fetchAuctions({ commit }, query) {
            try {
                const { data } = await AuctionItemService.getAuctions(query);
                commit("SET_AUCTIONS", data.content);
                delete data.content;
                commit("SET_PAGE_INFO", data);
            } catch (err) {
                console.log(err);
            }
        },

        async findById({ commit }, id) {
            try {

                const { data } = await AuctionItemService.findById(id);
                commit("SET_VIEWED_AUCTION", data);
            } catch (err) {
                console.log(err);
            }
        },

        async deleteById({ commit, state }, id) {
            try {
                const deletedItem = await AuctionItemService.deleteById(id);

                let newAuctions = state.auctions;
                if (deletedItem.status === 200) {
                    newAuctions = newAuctions.filter(auction => auction.id !== id);
                    commit("SET_AUCTIONS", newAuctions);
                }

            } catch (err) {
                console.log(err);
            }
        },
        async postBid({ commit, state }, payload) {
            const newPayload = {
                userId: payload.userId,
                amount: payload.amount
            }
            try {
                const newBids = state.viewedAuction.bids;
                const bid = await BidService.postBid(payload.auctionId, newPayload)

                if (bid.status === 201) {
                    newBids.unshift(bid.data);
                    commit("SET_CREATE_BID", newBids)

                    commit("SET_VIEWED_AUCTION_PRICE", bid.data.amount);

                }
            } catch (err) {
                return err;
            }
        },
        async postAuction(_, payload) {
            try {
                const auction = await AuctionItemService.post(payload);
                if (auction.status === 201) {
                    return auction;
                }
            } catch (err) {
                console.log(err);
            }
        },
        async closeAuction({ commit }, id) {
            try {
                const closedAuction = await AuctionItemService.closeAuction(id);
                if (closedAuction.status === 200) {
                    commit("SET_VIEWED_AUCTION", closedAuction.data);
                }
            } catch (err) {
                console.log(err);
            }
        },
        async updateAuction(_, { auctionId, payload }) {
            try {
                const updatedAuction = await AuctionItemService.update(auctionId, payload);
                return updatedAuction;
            } catch (err) {
                console.log(err);
            }
        }
    },

    getters: {
        getAuctions: state => state.auctions,
        getViewedAuction: state => state.viewedAuction,
        getPageInfo: state => state.pageInfo
    }
}