import { createApp } from "vue";
import vueLoading from "vue-loading-overlay";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "vue-loading-overlay/dist/vue-loading.css";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

const app = createApp(App);

app.use(vueLoading);
app.use(store);
app.use(router);

app.mount("#app");
