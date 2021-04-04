import { ref } from "vue";

export default function useIsAuctionClosed(date) {

    const isAuctionClosed = ref(false);

    function calculateIsAuctionClosed() {
        const now = new Date().getTime();
        let passedDate = new Date(date).getTime();
        let timeLeft = passedDate - now;
        isAuctionClosed.value = timeLeft < 0 ? true : false;
    }
    return [isAuctionClosed, calculateIsAuctionClosed];
}