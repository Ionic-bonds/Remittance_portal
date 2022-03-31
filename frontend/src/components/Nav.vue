<template>
  <el-menu
    :default-active="activeLink"
    class="el-menu-demo nav--container"
    mode="horizontal"
    background-color="rgba(30, 22, 81, 1)"
    text-color="#fff"
    active-text-color="#ffd04b"
    @select="handleSelect"
    :router="true"
  >
    <div class="nav--left">
      <img class="nav--logo" alt="Vue logo" src="../assets/Icon.png" />
    </div>

    <el-menu-item class="nav--right" index="/" >
      Home
    </el-menu-item>

    <el-menu-item class="nav--right" index="/remit" :disabled="!loginStatus">
      Remit
    </el-menu-item>

    <el-menu-item class="nav--right" index="/transaction" :disabled="!loginStatus">Transactions</el-menu-item>

    <button
      class="nav--login"
      @click="!loginStatus ? (loginFormVisible = true) : (this.$store.commit('updateLogin', false))"
    >{{ loginStatus ? "Log Out" : "Login" }}</button>

    <button
      v-if = "!loginStatus"
      class="nav--signup"
      @click="signupFormVisible = true">Sign up</button>

  </el-menu>

  <el-dialog v-model="loginFormVisible" width="500px">
    <LoginForm v-model:loginToggle="loginFormVisible"></LoginForm>
  </el-dialog>

  <el-dialog v-model="signupFormVisible" width="500px">
    <SignupForm v-model:signupToggle="signupFormVisible"></SignupForm>
  </el-dialog>
</template>


<script >
import LoginForm from './LoginForm.vue'
import SignupForm from './SignupForm.vue'
import Feedback from './Feedback.vue'
import router from "@/router";
// import { ElMessage } from 'element-plus'

export default {
  name: 'Nav',
  data() {
    return {
      activeIndex: "0",
      loginFormVisible: false,
      signupFormVisible: false,
      // loginStatus: false,
      activeLink: null,
    }
  },
  components: {
    LoginForm, SignupForm
  },
  methods: {
    handleSelect(index){
      // console.log(this.$store.getters.login)
      // console.log(this.loginStatus)
      // console.log(index)
    },
    // loginButton: () => {
    //   if(!this.loginStatus){
    //     this.loginFormVisible = true
    //   }
    // }
    signup(){
      console.log("sign up")
    }
  },
  watch: {
    loginStatus: function (val, oldVal) {
      // console.log("old: ", oldVal, ", New:", val)
      if (oldVal) {
        Feedback.open1("Successfully logged out!", "success")
        console.log(this.loginStatus + ", log out!")

        this.activeLink = "/"
        router.push("/")
      } else {
        this.activeLink = "/remit"
      }
    },
    // activeLink: function (val, oldVal) {
    //   console.log("old: ", oldVal, ", New:", val)
    // },
    $route (to, from) {
      // console.log(to.path)
      this.activeLink = to.path;
    }
  },
  computed: {
    // loginStatus: {get(){return this.$store.state.login}}
    loginStatus: {get(){return this.$store.getters.login}}

  },
  mounted(){
    this.activeLink = this.$route.path;
    // console.log(this.loginStatus)
    // https://stackoverflow.com/questions/69984821/vue-js-state-value-reset-from-the-input-after-hard-refresh-how-to-fix-it
    // Feedback.open1("Successfully logged out!", "success")
    // if (!this.loginStatus){
    //     this.activeLink = "0"
    //     router.push("/")
    // }
    // console.log(router.currentRoute.value.path)
  }
}
</script>


<style scoped>
.el-menu--horizontal {
  align-content: center !important;
  align-items: center !important;
  border-bottom: 2px white solid !important;
}

.nav--left {
  margin-right: auto;
}

a {
  text-decoration: none;
}

.nav--logo {
  margin: 16px 30px;
  height: 50px;
}

.nav--login {
  background-color: transparent;
  color: white;
  margin: 0px 30px 0px 10px;
  border-color: greenyellow;
  border-radius: 8px;
  font-size: 15px;
  padding: 8px 20px;
  border-style: groove;
}

.nav--signup {
  background-color: transparent;
  color: white;
  margin: 0px 30px 0px -20px;
  border-color: rgb(255, 78, 47);
  border-radius: 8px;
  font-size: 15px;
  padding: 8px 20px;
  border-style: groove;
}


.nav--login:hover {
  cursor: pointer;
  background-color: rgba(22, 160, 133);
}
.nav--signup:hover {
  cursor: pointer;
  background-color: rgb(160, 77, 22);
}
/* 680 */
@media only screen and (max-width: 790px) {
  .nav--login, .nav--signup {
    margin: 5px;
  }
  .nav--left {
    display: none;
  }
  .nav--container {
    display: flex;
    justify-content: center;
    height: 86px;
  }
}
</style>