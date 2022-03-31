<template>
  <Nav></Nav>

  <router-view/>
  <div class = "empty--div"></div>
  
  <Footer></Footer>
</template>


<script>
import Nav from '@/components/Nav.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: 'App',
  components: {
    Nav, Footer
  }
}
</script>

<style>

body {
  /* margin: 0px !important;
  padding: 0px !important; */
  background-image: linear-gradient(rgba(30, 22, 81, 1), rgba(137, 196, 244));
  height: 100%;
  margin: 0;
  background-repeat: no-repeat;
  background-attachment: fixed;
  min-width: 550px;
  font-family: 'Poppins', sans-serif;
}
.empty--div{
  height: 150px;
}
</style>
