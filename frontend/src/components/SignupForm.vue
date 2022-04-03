<template>        
        <el-form 

        label-position = "top"
        label-width="120px" 
        class = "signup--form"
        @submit.prevent
        :rules= "rules"
        :model = "ruleForm"
        ref="signupForm"
        >
            <h1 class = "signup--title">Sign Up</h1>



            <h4 class="signup--header">Username:</h4>
            <el-form-item class="signup--input" prop = "name">
                <el-input 
                    v-model="name"
                    placeholder="Enter username"
                />
            </el-form-item>

            <h4 class="signup--header">Email:</h4>
            <el-form-item class="signup--input" prop = "email">
                <el-input 
                    v-model="email"
                    type="email"
                    placeholder="Enter email"
                />
            </el-form-item>

            <h4 class="signup--header">Password:</h4>
            <el-form-item class="signup--input" prop = "password">
                <el-input
                    v-model="password"
                    type="password"
                    placeholder="Enter password"
                    show-password
                />
            </el-form-item>

            <h4 style="margin-top: 20px;" class="signup--header">Role:
            <el-select
            v-model="roles"
            multiple
            filterable
            disabled
            style="margin: 0px 20px;"
            >
            <el-option
                v-for="item in rolesArray"
                :key="item"
                :label="item"
                :value="item"
            />
            </el-select>
            </h4>


            <p v-if="errorBool" class = "signup--error">{{this.errormsg}}</p>

            <button class = "signup--button" @click="signup">Submit</button>
            
        </el-form>
</template>


<script >
import ApiDataService from "../api/ApiDataService";
import Feedback from './Feedback.vue'


export default {
    name: 'SignupForm',
    props: ['signupToggle'],
    data() {
        return { 
            name: "",
            email:"",
            password: "",
            roles: ["user"],
            errorBool: false,
            errormsg : "",
            rolesArray: ["user", "moderator", "admin"],
            rules: {
                name: [
                    { required: true, message: 'Please enter your username', trigger: 'blur' },
                    { min: 3, max: 15, message: 'Username must be between 3 and 15 characters', trigger: 'blur' }
                ],
                email: [
                    { required: true, message: 'Please enter your email', trigger: 'blur' },
                    { type: 'email', message: 'Please enter a valid email', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please enter your password', trigger: 'blur' },
                    { min: 6, max: 20, message: 'Password must be between 6 and 20 characters', trigger: 'blur' }
                ],
                roles: [
                    { required: true, message: 'Please select a role', trigger: 'blur' }
                ]
            }
        }
    },
    methods:{
        signup() {
            this.$refs["signupForm"].validate((valid)=>{
                if (valid){
                    ApiDataService.signup({
                        "username": this.name,
                        "email": this.email,
                        "password": this.password,
                        "roles": this.roles
                    })
                    .then(response => {
                        this.name = ""
                        this.password = ""
                        this.email = ""
                        this.errorBool = false
                        Feedback.open3(response.data.message, "Success")
                        this.visible = !this.visible

                    }).catch(e =>{
                        (e?.response?.data) ? (this.errormsg = e.response.data.message) : (this.errormsg = e.message)
                        this.errorBool = true
                        this.name = ""
                        this.password = ""
                        this.email = ""
                    })
                } else{
                    this.name = ""
                    this.password = ""
                    this.email = ""
                    this.errorBool = false
                }
            })
        }
    },
    computed: {
        visible: {
            get(){
                return this.signupToggle
            },
            set(value){
                this.$emit('update:signupToggle', value)
            }
        },
        ruleForm(){
            return {
                name: this.name,
                email: this.email,
                password: this.password,
                roles: this.roles
            }
        }
    },
    watch: {
        visible: function (val, oldVal) {
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

.signup--title{
    text-align: center;
}

.signup--form{
    background-color: white;
    padding: 10px 30px;
    border-radius: 15px;
    font-family: 'Poppins', sans-serif;
}

.signup--header{
    margin-bottom: 5px;
}

.signup--button{
    width: 100%;
    background-color: #409eff;
    border-radius: 5px;
    height: 30px;
    color: white;
    margin: 10px 0px;
}
.signup--button:hover{
    background-color: mediumblue;
    cursor: pointer;
}

.signup--error{
    color: red;
    text-align: center;
}

</style>