<template>
  <div class="transactionView">
    <Transactions></Transactions>
    
  </div>
</template>

<script>
// @ is an alias to /src
import Transactions from '@/components/Transactions.vue'

export default {
  name: 'TransactionsView',
  components: {
    Transactions
  }
}
</script>