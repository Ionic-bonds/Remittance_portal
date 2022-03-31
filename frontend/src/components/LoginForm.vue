<template>
    <!-- <el-card class="box-card"> -->
        
        <el-form 
        label-position = "top"
        label-width="120px" 
        class = "login--form"
        @submit.prevent
        :rules= "rules"
        :model = "ruleForm"
        ref="loginForm"
        >
            <h1 class = "login--title">Log-in</h1>

            <h4 class="login--header">Username:</h4>
            <el-form-item class="login--input" prop = "name">
                <el-input 
                    v-model="name"
                    placeholder="Enter username"
                />
            </el-form-item>

            <h4 class="login--header">Password:</h4>
            <el-form-item class="login--input" prop = "password">
                <el-input
                    v-model="password"
                    type="password"
                    placeholder="Enter password"
                    show-password
                />
            </el-form-item>

            <p v-if="errorBool" class = "login--error">{{this.errormsg}}</p>

            <button class = "login--button" @click="login">Submit</button>
            
        </el-form>
    <!-- </el-card> -->
</template>


<script >
// https://vuejs.org/guide/essentials/lifecycle.html
import ApiDataService from "../api/ApiDataService";
import router from "@/router";
import Feedback from './Feedback.vue'
import axios from "axios";


export default {
    name: 'LoginForm',
    props: ['loginToggle'],
    data() {
        return { 
            name: "",
            password: "",
            errorBool: false,
            errormsg : "",
            rules: {
                name: [
                    { required: true, message: 'Please enter your username', trigger: 'blur' },
                    { min: 3, max: 15, message: 'Username must be between 3 and 15 characters', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 6, max: 20, message: 'Password must be between 6 and 20 characters', trigger: 'blur' }
                ]
            }
        }
    },
    methods:{
        login() {
            this.$refs["loginForm"].validate((valid)=>{
                if (valid){
                    // console.log("login form validation: True")
                    console.log("calling API - login")
                    ApiDataService.login({
                        "username": this.name,
                        "password": this.password
                    })
                    .then(response => {
                        // console.log(response, "!")
                        // var cookie = response.data.cookie.split(";")[0]
                        // this.$store.commit("updateCookie", cookie)
                        // axios.defaults.headers.cookie = cookie

                        this.$store.commit("updateLogin", response.data.username)
                        this.$store.commit("updateUser", response.data.id)
                        this.name = ""
                        this.password = ""
                        this.errorBool = false
                        router.push("/remit")
                        this.visible = !this.visible
                        Feedback.open3(response.data.message, "Welcome " + this.$store.getters.login)
                        console.log("Log in user: " + this.$store.getters.login)
                    }).catch(e =>{
                        (e?.response?.data) ? (this.errormsg = e.response.data.message) : (this.errormsg = e.message)
                        this.errorBool = true
                        this.name = ""
                        this.password = ""
                    })

                } else{
                    // console.log("login form validation: False")
                    this.errorBool = false
                    this.name = ""
                    this.password = ""
                }
            })
            // if (this.name && this.password && this.password.length >= 6){
            //     ApiDataService.login({
            //         "username": this.name,
            //         "password": this.password
            //     })
            //     .then(response => {
            //         // console.log(response, "!")
            //         this.$store.commit("updateLogin", response.data.username)
            //         this.$store.commit("updateUser", response.data.id)
            //         this.name = ""
            //         this.password = ""
            //         this.errorBool = false
            //         router.push("/remit")
            //         this.visible = !this.visible
            //         Feedback.open3(response.data.message, "Welcome " + this.$store.getters.login)
            //         console.log("Log in user: " + this.$store.getters.login)
            //     }).catch(e =>{
            //         (e?.response?.data) ? (this.errormsg = e.response.data.message) : (this.errormsg = e.message)
            //         this.errorBool = true
            //         this.name = ""
            //         this.password = ""
            //     })
            // } else{

            //     if (!this.name && !this.password){
            //         this.errormsg = "Please enter username and password."
            //     } else if (!this.name){
            //         this.errormsg = "Username must not be empty!"
            //     } else if (!this.password){
            //         this.errormsg = "Password must not be empty!"
            //     } else if (this.password.length < 6){
            //         this.errormsg = "Password must be at least 6 characters!"
            //     }
            //     this.errorBool = true
            //     this.name = ""
            //     this.password = ""
            // }
            
        }
    },
    computed: {
        visible: {
            get(){
                return this.loginToggle
            },
            set(value){
                this.$emit('update:loginToggle', value)
            }
        },
        ruleForm(){
            return {
                name: this.name,
                password: this.password
            }
        }
        // loginStatus: {
        //     get(){
        //         return this.status
        //     },
        //     set(value){
        //         this.$emit('update:status', value)
        //     }
        // }
    },
    watch: {
        visible: function (val, oldVal) {
            // console.log("old: ", oldVal, ", New:", val)
            if (oldVal) {
                this.name = ""
                this.password = ""
                this.errorBool = false
            } 
        }
    },
}
</script>


<style scoped>
/* .box-card{
    font-family: 'Poppins', sans-serif;
    background-color:rgba(196, 196, 196, 0.8);
    border-color: rgba(196, 196, 196, 0.8);
    border-radius: 15px;
} */

.login--title{
    text-align: center;
}

.login--form{
    background-color: white;
    padding: 10px 30px;
    border-radius: 15px;
    font-family: 'Poppins', sans-serif;
}

.login--header{
    margin-bottom: 5px;
}

.login--button{
    width: 100%;
    background-color: #409eff;
    border-radius: 5px;
    height: 30px;
    color: white;
    margin: 10px 0px;
}
.login--button:hover{
    background-color: mediumblue;
    cursor: pointer;
}

.login--error{
    color: red;
    text-align: center;
    /* display: none; */
}

</style>