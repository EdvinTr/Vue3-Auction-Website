import Api from "./Api"

export default {
    oAuth2Login(user) {
        return Api().post(`oauth2/login`, user)
    }
}