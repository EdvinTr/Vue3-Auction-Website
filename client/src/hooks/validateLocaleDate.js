import { ref } from "vue";

/* date is valid if localeCompare returns -1
  date is not valid if localeCompare returns 1
  date is equal if localeCompare returns 0 */
export default function useLocaleDateValidation() {

    const isLocaleDateValid = ref(false);

    function validateLocaleDate(localeDate) {
        const currentDate = new Date().toLocaleString();
        const compare = currentDate.localeCompare(localeDate);
        isLocaleDateValid.value = compare === -1;
    }

    return [isLocaleDateValid, validateLocaleDate];
}