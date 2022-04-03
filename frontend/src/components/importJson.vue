<template>
    <div class = "json--wrapper">
        <el-upload
        ref="upload"
        class="upload-demo"
        action="http://localhost:3000/uploads/"
        :limit="1"
        :on-exceed="handleExceed"
        :auto-upload="false"

        :before-upload = "beforeUpload"
        :on-success = "uploadSuccess"
        :on-error = "uploadError"
        :http-request= "uploadFiles"
        :file-list="fileList"
        >
            
            <template #trigger>
                <el-button type="primary">1. select file</el-button>
            </template>

            <el-button class="ml-3" type="success" @click="submitUpload">
                2. upload for express mapping
            </el-button>

            <template #tip>
                <div class="el-upload__tip text-red">
                limit 1 file, Json file ONLY
                </div>
            </template>
        </el-upload>
    </div>
</template>


<script >
import Feedback from './Feedback.vue'

export default {
    name: 'ImportJson',
    data() {
        return { 
            fileList: [],
            acceptable: ["application/json"],
        }
    },
    methods: {
        handleExceed: function(files) {
            // Only accept 1 file
            this.$refs.upload.clearFiles()
            this.$refs.upload.handleStart(files[0])
        },
        beforeUpload: function(file) {
            // Validation for type and size #TODO:

            var validationType = this.acceptable.includes(file.type)

            if (validationType) {
                console.log("This is a json file!!!")
                return true
                // return false
            } else {
                console.log("This is not a json file!")
                Feedback.open1("Unacceptable file type!", "error")
                return false
            }            
            // return false
        },
        uploadSuccess: function(file){
            // #TODO: after knowing endpoint
            console.log("Upload success!")
            Feedback.open1("Upload Success!", "success")
        },
        uploadError: function(msg){
            //  #TODO: 
            Feedback.open5(msg, "Something's Wrong!")
        },
        submitUpload: function(){
            // #FIXME: change just to make sure everything flows
            // || this.fileList[0].status == "success"
            if (this.fileList.length == 0 ) {
                console.log("no file uploaded!")
                Feedback.open1("No file detected!", "error")
             } else {
                console.log("uploading files now...")
                this.$refs.upload.submit()
            }            
        },
        uploadFiles: function(event){

            var read = new FileReader()
            read.readAsText(event.file);
            read.onloadend = ()=>{
                var data = JSON.parse(read.result)
                this.manualMapping = data
                // console.log(this.manualMapping)
                event.onSuccess()
            }

        },
    },
    mounted(){
        // console.log(this.$store.getters.login)
        // console.log(this.manualMappingProp)
    },
    props: ["manualMappingProp"],
    computed: {
        manualMapping: {
            get(){
                return this.manualMappingProp
            },
            set(value){
                this.$emit('update:manualMappingProp', value)
            }
        }
    },
    watch: {
        // fileList: function (val, oldVal) {
        //     console.log("old: ", oldVal, ", New:", val)
        // }
    }
}
</script>


<style scoped>
.json--wrapper{
    /* width: 100%; */
    /* height: 100%; */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* background-color: rgba(240, 248, 255, 0.885); */
}
.upload-demo{
    background-color: antiquewhite;
    padding: 20px;
    border-radius: 5px;
    margin: 20px 0px;
}
</style>