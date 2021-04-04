import { createApp } from 'vue'
import App from './App.vue'
import store from "./store";
import router from "./router";
import BaseButton from "./components/base/BaseButton.vue"
import RightArrow from "./components/RightArrow.vue";
import './index.css'

import gAuthPlugin from 'vue3-google-oauth2';
let gauthClientId = "<YOUR Google OAuth2 Client Key>";

createApp(App)
    .use(store)
    .use(router)
    .component("BaseButton", BaseButton)
    .component("RightArrow", RightArrow)
    .use(gAuthPlugin, { clientId: gauthClientId, scope: 'email', prompt: 'consent', fetch_basic_profile: true })
    .mount('#app')
