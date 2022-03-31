<template>
    <div class = "import--wrapper"
    v-loading="loadBool"
    element-loading-text="Loading..."
    :element-loading-spinner="svg"
    element-loading-svg-view-box="-10, -10, 50, 50"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    
    >
        <h1>Upload XLSX file here:</h1>

        <el-button type="primary" class = "import--download" @click = "downloadJson">
        Download Current Mapping<el-icon class="el-icon--right"><Upload/></el-icon>
        </el-button>


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
                CSV/XLSX files with a size less than {{"<insert size here>"}}
                </div>
            </template>
        </el-upload>

        <button @click.prevent="uploadButton" class = "import--button">Start Importing</button>
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
        }
    },
    methods: {
        stoploading: (l) =>{
            l.close()
        },
        downloadJson: function() {
            console.log("downloading now")
            Feedback.open1("Downloading now...", "info")

            console.log("invoking API 20: Get all fields + Mappings")
            ApiDataService.getMappingsAndFields(this.$store.getters.user)
            .then((response)=>{
                // console.log(response)                
                this.downloadObjectasJson(response.data)
                Feedback.open1("Download Success!", "success")

            }).catch((e)=>{
                var errMsg 
                (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                console.log(errMsg)
                Feedback.open1("Download Faliure!", "error")
            })
        },
        downloadObjectasJson: function(data){
            // console.log(data, this.exportName)
            // console.log(data.corporateFieldList)
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
            // Validation for type and size #TODO:

            var validationType = this.acceptable.includes(file.type)

            if (validationType) {
                console.log("This is an excel file!!!")
                return true
            } else {
                console.log("This is not an excel file!")
                this.stoploading(this.loadinstance)
                Feedback.open1("Unacceptable file type!", "error")
                // this.loadBool = false
                // this.stoploading(this.loadinstance)
                return false
            }            
            // return false
        },
        uploadSuccess: function(file){
            // #TODO: after knowing endpoint
            console.log("Upload success!")
            this.stoploading(this.loadinstance)
            this.importStep = 1;
            Feedback.open1("Upload Success!", "success")
            // this.loadBool = false
            
        },
        uploadError: function(msg){
            //  #TODO: 
            this.stoploading(this.loadinstance)
            Feedback.open5(msg, "Something's Wrong!")
            // this.loadBool = false
            
        },
        uploadButton: function(){
            // #FIXME: change just to make sure everything flows
            if (this.fileList.length == 0) {
                console.log("no file uploaded!")
                Feedback.open1("No file detected!", "error")
             } else {
                Feedback.open4()
                .then((number)=>{

                    if (number){
                        this.headerNum = number
                        console.log("uploading files now...")
                        // this.loadBool = true;

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
            // NOT USING THIS
            // this.loadBool = true
            console.log(event)
        },
        uploadFiles: function(event){   
            // Ajax call here (need merge with the if statement on top)
            const data = new FormData()
            this.uploadFile = event.file
            data.append("file", event.file)


            console.log("invoking API 20: Get all fields + Mappings")
            ApiDataService.getMappingsAndFields(this.$store.getters.user)
            .then((response)=>{
                // console.log(response)
                this.mapFields = response.data
                
                console.log("invoking API 29: Get Excel Headers")
                ApiDataService.getFileHeaders(data, this.$store.getters.user, this.headerNum)
                .then((response)=>{
                    // console.log(response)
                    this.headersCol = response.data
                    event.onSuccess()
                }).catch((e)=>{
                    var errMsg 
                    (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                    console.log(errMsg)
                    event.onError(errMsg)
                })
            }).catch((e)=>{
                var errMsg 
                (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                console.log(errMsg)
                event.onError(errMsg)
            })

        },
    },
    mounted(){
        // console.log(this.$store.getters.login)
        // console.log(this.$store.getters.user)
        // console.log(this.$store.getters.cookie)
        // console.log(this.$refs.upload)
        // console.log(this.step)
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
        // downloadJsonData(){
        //     if (mapFields.length > 0){
        //         return mapFields.corporateFieldList
        //     } else{
        //         return []
        //     }
        // }, 
    },
    watch: {
        // mapFields: function (val, oldVal) {
        //     console.log("old: ", oldVal, ", New:", val)
        // }
    }
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
    /* text-decoration: none; */
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
    /* color: black !important; */
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
}
.import--button:hover{
    cursor: pointer;
}

</style>