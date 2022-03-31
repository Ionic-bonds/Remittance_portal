import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import { createStore } from 'vuex'
import store from "./store"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import './overwrite.css'



createApp(App)
.use(store) 
.use(router)
.use(ElementPlus)
.mount('#app')
