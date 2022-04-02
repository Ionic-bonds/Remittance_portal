import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Remit from '../views/Remit.vue'
import TransactionsView from "../views/TransactionsView.vue"
import store from "../store"

function checkAuth(to, from) {
  !store.getters.login && router.push("/")
}


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/remit',
    name: 'remit',
    component: Remit,
    beforeEnter: [checkAuth]
  },
  {
    path: '/transactions',
    name: 'transactions',
    component: TransactionsView,
    beforeEnter: [checkAuth]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  // history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
