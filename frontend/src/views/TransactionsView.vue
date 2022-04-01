<template>
  <div class="transactionView">
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <Transactions></Transactions>
    
  </div>
</template>

<script>
// @ is an alias to /src
import Transactions from '@/components/Transactions.vue'

export default {
  name: 'HomeView',
  components: {
    Transactions
  }
}
</script>
