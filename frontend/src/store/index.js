import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist'

https://medium.com/@leecorylee/understanding-vuex-utilize-vuex-with-vuexpersist-sessionstorage-and-localstorage-959b48b91ab6

// Vue.use(Vuex);
// Vue.createApp(App).use(Vuex)
// const app = Vue.createApp({
//     Vuex
// })

var vuexStore = null;

const cookieEnabled = navigator.cookieEnabled;
// console.log("Cookies: " + cookieEnabled);

if (cookieEnabled) {
    const vuexLocalStorage = new VuexPersistence({
        key: "vuex", // display key in browser session
        storage: window.sessionStorage, // or window.localStorage, js-cookie  or localForage
        reducer: state => ({
            session: {
                login: state.session.login,
                user: state.session.user,
                cookie: state.session.cookie,
            }
        })
    });

    vuexStore = new Vuex.Store({
        plugins: [vuexLocalStorage.plugin],
        state: {
            session: {
                login: false,
                user: null,
                cookie: null,
            }
        },
        mutations: {
            updateLogin(state, val){
                state.session.login = val;
            },
            updateUser(state, val){
                state.session.user = val;
            },
            updateCookie(state, val){
                state.session.cookie = val;
            },
        },
        getters: {
            login: state => state.session.login,
            user: state => state.session.user,
            cookie: state => state.session.cookie,
        },
        actions:{}

    })
    // console.log(vuexLocalStorage)
}

export default vuexStore;