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
            var validationType = this.acceptable.includes(file.type)

            if (validationType) {
                return true
            } else {
                Feedback.open1("Unacceptable file type!", "error")
                return false
            }            
        },
        uploadSuccess: function(file){
            Feedback.open1("Upload Success!", "success")
        },
        uploadError: function(msg){
            Feedback.open5(msg, "Something's Wrong!")
        },
        submitUpload: function(){

            if (this.fileList.length == 0 ) {
                Feedback.open1("No file detected!", "error")
             } else {
                this.$refs.upload.submit()
            }            
        },
        uploadFiles: function(event){

            var read = new FileReader()
            read.readAsText(event.file);
            read.onloadend = ()=>{
                var data = JSON.parse(read.result)
                this.manualMapping = data
                event.onSuccess()
            }

        },
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
}
</script>


<style scoped>
.json--wrapper{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.upload-demo{
    background-color: antiquewhite;
    padding: 20px;
    border-radius: 5px;
    margin: 20px 0px;
}
</style>