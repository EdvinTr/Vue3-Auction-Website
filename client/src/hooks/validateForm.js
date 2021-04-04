import { ref } from "vue";

export default function useFormValidation() {

    const isFormValid = ref("pending");

    function validateForm(formData) {
        for (const key in formData) {
            if (!formData[key] || formData[key] === '') {
                isFormValid.value = 'invalid';
                break;
            } else {
                isFormValid.value = 'valid';
            }
        }
    };
    return [isFormValid, validateForm];
}