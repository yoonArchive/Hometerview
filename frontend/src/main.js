// 기본 플러그인 Import
import { createApp, h } from "vue";
import store from "./common/lib/store";
// import ElementPlus from "./common/lib/element-plus";
import App from "./App.vue";
import VueAxios from "./common/lib/axios";
import axios from "./common/lib/axios";
import i18n from "./common/lib/i18n";
import router from "./common/lib/vue-router";
// import "@fortawesome/fontawesome-free/js/all.js";
import "@fortawesome/fontawesome-free/css/all.css";
// import MyInterviewView from './views/myinterview/MyInterviewView;
// import firebase from 'firebase'
// import "element-plus/packages/theme-chalk/src/base.scss";
// import 'v-calendar/dist/style.css';

// import VCalendar from 'v-calendar';

// var firebaseConfig = {
//   apiKey: "AIzaSyDbDJvN2_PbQfiuVP18qVQWYeNmxyAkx1s",
//   authDomain: "pjt-b105.firebaseapp.com",
//   projectId: "pjt-b105",
//   storageBucket: "pjt-b105.appspot.com",
//   messagingSenderId: "795234785208",
//   appId: "1:795234785208:web:e50a444fa59ec47b88214d",
//   measurementId: "G-396EMPTV8G"
// };

// Element UI Components [끝]

const app = createApp({
  render: () => h(App)
});
// app.use(ElementPlus);
app.use(VueAxios, axios);
app.use(store);
app.use(i18n);
app.use(router);
// app.use(VCalendar, {})

// components.forEach(component => {
//   app.component(component.name, component);
// });

// plugins.forEach(plugin => {
//   app.use(plugin);
// });

app.mount("#app");
