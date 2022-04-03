<template>
    <div class = "import--wrapper"
    v-loading="loadBool"
    element-loading-text="Loading..."
    :element-loading-spinner="svg"
    element-loading-svg-view-box="-10, -10, 50, 50"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    
    >
        <h1>Upload XLSX file here:</h1>
        <span>
            <el-button type="primary" class = "import--download" @click = "downloadJson">
            Download Current Mapping<el-icon class="el-icon--right"><Upload/></el-icon>
            </el-button>
            <el-button type="success" class = "import--template" @click = "downloadTemplate">
            Download Template File<el-icon class="el-icon--right"><Upload/></el-icon>
            </el-button>
        </span>


        <el-upload
        ref="upload"
        class="upload-demo import--container"
        drag
        action="http://localhost:3000/uploads/" 
        multiple
        :limit="1"
        :on-exceed="handleExceed"
        :auto-upload="false"
        :before-upload = "beforeUpload"
        :on-success = "uploadSuccess"
        :on-error = "uploadError"
        :on-progress = "uploadProgress"
        :http-request= "uploadFiles"
        :file-list="fileList"
        >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            
            <div class="el-upload__text">
                Drop file here or <em>click to upload</em>
            </div>

            <template #tip>
                <div class="el-upload__tip import--tip">
                XLSX files with a size less than {{"<TechG insert size here>"}}
                </div>
            </template>
        </el-upload>

        <el-button type="info" plain @click.prevent="uploadButton" class = "import--button">Start Importing</el-button>
        <iframe id="my_iframe" style="display:none;"></iframe>
    </div>
</template>

<script setup>
import { UploadFilled, Upload } from '@element-plus/icons-vue'
</script>

<script >
import Feedback from './Feedback.vue'
import ApiDataService from "../api/ApiDataService";
import { ElLoading } from 'element-plus'

export default {
    name: 'Import',
    data() {
        return { 
            // "application/vnd.ms-excel" <=(CSV)
            acceptable: ["application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"],
            templateLink: "/",
            svg : `
            <path class="path" d="
            M 30 15
            L 28 17
            M 25.61 25.61
            A 15 15, 0, 0, 1, 15 30
            A 15 15, 0, 1, 1, 27.99 7.5
            L 15 15
            " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>`,
            loadBool: false,
            fileList: [],
            headerNum: false,
            exportName: "mapping",
            loadinstance: "",
            templateUrl: "/TEMPLATE_DATA.xlsx",
        }
    },
    methods: {
        stoploading: (l) =>{
            l.close()
        },
        downloadTemplate: function (){
            document.getElementById('my_iframe').src = this.templateUrl
        },
        downloadJson: function() {
            Feedback.open1("Downloading now...", "info")

            ApiDataService.getMappingsAndFields(this.$store.getters.user)
            .then((response)=>{
                this.downloadObjectasJson(response.data)
                Feedback.open1("Download Success!", "success")

            }).catch((e)=>{
                var errMsg 
                (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                Feedback.open1("Download Faliure!", "error")
            })
        },
        downloadObjectasJson: function(data){
            var exportObj = data.corporateFieldList
            var exportName = this.exportName
            var dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(exportObj));
            var downloadAnchorNode = document.createElement('a');
            downloadAnchorNode.setAttribute("href",     dataStr);
            downloadAnchorNode.setAttribute("download", exportName + ".json");
            document.body.appendChild(downloadAnchorNode); // required for firefox
            downloadAnchorNode.click();
            downloadAnchorNode.remove();
        },
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
                this.stoploading(this.loadinstance)
                Feedback.open1("Unacceptable file type!", "error")

                return false
            }            
        },
        uploadSuccess: function(file){
            this.stoploading(this.loadinstance)
            this.importStep = 1;
            Feedback.open1("Upload Success!", "success")
            
        },
        uploadError: function(msg){
            this.stoploading(this.loadinstance)
            Feedback.open5(msg, "Something's Wrong!")
            
        },
        uploadButton: function(){
            if (this.fileList.length == 0) {
                Feedback.open1("No file detected!", "error")
             } else {
                Feedback.open4()
                .then((number)=>{

                    if (number){
                        this.headerNum = number

                        this.loadinstance = ElLoading.service({
                            lock: true,
                            text: 'Uploading...',
                            background: 'rgba(0, 0, 0, 0.7)',
                        })


                        this.$refs.upload.submit()
                    }    
                }) 
            }            
        },
        uploadProgress: function(event){
        },
        uploadFiles: function(event){   
            const data = new FormData()
            this.uploadFile = event.file
            data.append("file", event.file)


            ApiDataService.getMappingsAndFields(this.$store.getters.user)
            .then((response)=>{
                this.mapFields = response.data
                
                ApiDataService.getFileHeaders(data, this.$store.getters.user, this.headerNum)
                .then((response)=>{
                    this.headersCol = response.data
                    event.onSuccess()
                }).catch((e)=>{
                    var errMsg 
                    (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                    event.onError(errMsg)
                })
            }).catch((e)=>{
                var errMsg 
                (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                event.onError(errMsg)
            })

        },
    },
    props: ["step", "mapFieldsProp", "headersColProp", "file"],
    computed: {
        importStep: {
            get(){
                return this.step
            },
            set(value){
                this.$emit('update:step', value)
            }
        },
        mapFields: {
            get(){
                return this.optionProp
            },
            set(value){
                this.$emit('update:mapFieldsProp', value)
            }
        },
        headersCol: {
            get(){
                return this.paramProp
            },
            set(value){
                this.$emit('update:headersColProp', value)
            }
        },
        uploadFile: {
            get(){
                return this.file
            },
            set(value){
                this.$emit('update:file', value)
            }
        },
    },
}
</script>


<style scoped>
.import--wrapper { 
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 50px 10px;
    flex-direction: column;
}


h1{ 
    background-color:rgba(253, 221, 150, 0.75);
    padding: 5px 10px;
    font-size: 25px;
    border-radius: 5px;
}
h1 > a{
    font-style: italic;
    color: black;
}

.import--download{
    margin: -5px 0px 10px 0px;
}
.import--container{
    background-color: rgba(225, 225, 225, 1);
    color: black;
    border-radius: 10px;
}
.el-icon--upload{
    font-size: 200px !important;
}

.import--tip{
    padding: 10px;
}

.import--button{
    width: 60vw;
    max-width: 650px;
    min-width: 350px;
    margin: 20px 0px 40px 0px;
    height: 30px;
    font-family: 'Poppins', sans-serif;
    color: black;
}

.import--button:hover{
    cursor: pointer;
}

.import--template, .import--download{
    width: 250px;
    margin: 0px 0px 20px 0px;
    height: 30px;
    font-family: 'Poppins', sans-serif;
}
.import--template:hover, .import--download:hover{
    cursor: pointer;
}
</style>