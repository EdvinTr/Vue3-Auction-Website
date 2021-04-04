import { ref } from "vue";

export default function useTime(date) {
    const visibleTime = ref("")

    function calcLocaleDateDifference() {
        const interval = setInterval(() => {
            let now = new Date().getTime();
            let passedDate = new Date(date).getTime();
            let timeLeft = passedDate - now;
            if (timeLeft < 0) {
                visibleTime.value = "Auction Closed";
                clearInterval(interval);
                return;
            }
            let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
            let hours = Math.floor(
                (timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
            );
            let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

            hours = hours < 10 ? '0' + hours : hours;
            minutes = minutes < 10 ? '0' + minutes : minutes;
            seconds = seconds < 10 ? '0' + seconds : seconds;
            if (days === 1) {
                visibleTime.value = `${days} day`
            } else if (days > 1) {
                visibleTime.value = `${days} days`
            }
            else {
                visibleTime.value = `${hours}:${minutes}:${seconds}`
            }
        }, 1000)
    }
    return [visibleTime, calcLocaleDateDifference]
}