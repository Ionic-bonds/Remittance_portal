<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <HomeCanva></HomeCanva>    

  </div>
</template>

<script>
// @ is an alias to /src
import HomeCanva from '@/components/HomeCanva.vue'
import Transactions from '@/components/Transactions.vue'

export default {
  name: 'HomeView',
  components: {
    HomeCanva, Transactions
  }
}
</script>
