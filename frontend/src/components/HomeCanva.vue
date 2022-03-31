<template>
  <el-row class = "home--container">

    <el-col :sm="24" :md="16" class="home--intro--container">
        <h1 class="home--intro--title">The easiest way to remit money to FinanceNow, EverywhereRemit and PaymentGo</h1>
        <p class="home--intro--subtitle">Either download our template files or just import your data file to our system. NEED HELP WITH THE TEXT</p>
    </el-col>

    <el-col :sm="8"  class="nav--image--container hidden-sm-and-down">
        <img class="nav--image" alt="Vue logo" src="../assets/logo.png">
    </el-col>

  </el-row>

</template>


<script >
export default {
    name: 'HomeCanva',
    data() {
        return { 
        }
    },
}
</script>


<style scoped>

.home--intro--container{
    display: flex;
    /* align-items: center; */
    justify-content: center;
    flex-direction: column;
    padding: 50px 5rem 50px 5rem;
}

.home--intro--title{
    font-family: 'Poppins', sans-serif;
    font-style: 700px;
    font-size: 36px;
    color: white;
    /* margin: 120px 50px 40px 80px; */
}

.home--intro--subtitle{
    font-family: 'Poppins', sans-serif;
    color: white;
    /* margin: 10px 50px 40px 80px; */
}

.nav--image--container{
    display: flex;
    justify-content: center; /* align horizontal */
    align-items: center; /* align vertical */
}
.nav--image{
    margin: 50px 20px;
    width: 30rem;
}
</style>