<template>
    <div class = "map--wrapper">

    <!-- First part of the mapping -->

    <div class="map--transaction--container" v-if ="!this.secondaryStep">
        <h1>Map Transaction Column</h1>
        <MapTable v-model:masterTableDataProp = "masterTableData"  v-model:masterOptionListProp = "masterOptionList" v-model:usedOptionProp = "usedOption"></MapTable>
    </div>
    
    <!-- Error List -->
    <div class="map--transaction--container" v-if ="this.secondaryStep">
        <h1 class="error--color">SOMETHING WRONG! <br>Please rectify and try again :)</h1> 
        <ErrorTable v-model:errorListProp = "errorList"></ErrorTable>>
    </div>
    
    <!-- Buttons -->
    <div class = "button--wrapper">
        <el-button type="info" plain @click = "this.back" class = "button--back">{{'<'}} Back</el-button>
        <el-button type="primary" plain @click="this.continue" class = "button--continue">{{this.continueText}}</el-button>
    </div>
    </div>

</template>



<script >
import MapTable from "./MapTable.vue"
import Feedback from './Feedback.vue'
import ErrorTable from './ErrorTable.vue'
import { ElLoading } from 'element-plus'
import ApiDataService from "../api/ApiDataService";

export default {
    name: 'RemitMap',
    props: ["step","masterObjectProp", "file", "manualMappingProp","secStepProp", "outcomeListProp"],
    components: {MapTable, ErrorTable},
    data() {
        return {
            value: "",
            truthyValue: false,
            excludeOption: [],
            loadinstance: "",
            errorList:  [],
            continueText: "Continue >",
        }
    },
    methods: {
        stoploading: (l) =>{
            l.close()
        },
        continue: function(){
            if (this.secondaryStep){
                this.continueText =  "Continue >"
                this.secondaryStep = false
                this.importStep = 0

            } else{
                Feedback.open6().then((status)=>{
                    if(status){
                        this.loadinstance = ElLoading.service({
                            lock: true,
                            text: 'Upload Mappings...',
                            background: 'rgba(0, 0, 0, 0.7)',
                        })


                        const data = new FormData()
                        this.uploadFile = this.file
                        data.append("file", this.file)


                        ApiDataService.addMapping(this.fieldMappingList)
                        .then((response)=>{

                            this.loadinstance = ElLoading.service({
                                lock: true,
                                text: 'Validating and uploading...',
                                background: 'rgba(0, 0, 0, 0.7)',
                            })

                            const data = new FormData()
                            this.uploadFile = this.file
                            data.append("file", this.file)
                            ApiDataService.validateMap(data, this.$store.getters.user)
                            .then((response)=>{

                                this.stoploading(this.loadinstance)
                                if (response.data.errorList.length > 0 ){
                                    this.errorList = response.data.errorList
                                    this.secondaryStep = true
                                    this.continueText =  "Remit Again >"
                                } else{
                                    this.outcomeList = response.data.transactOutcomeList
                                    this.importStep = 2
                                    window.scrollTo(0,0)
                                }
                                

                            }).catch((e)=>{
                                var errMsg 
                                (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                                Feedback.open5(errMsg, "Something's Wrong!")
                                this.stoploading(this.loadinstance)
                            })

                        }).catch((e)=>{
                            var errMsg 
                            (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                            Feedback.open5(errMsg, "Something's Wrong!")
                            this.stoploading(this.loadinstance)
                        })
                    }
                })
            }


        },
        back: function(){
            if (this.secondaryStep)  {
                this.continueText =  "Continue >"
                this.secondaryStep = !this.secondaryStep
                Feedback.open1("Map Again!", "info")
            } else {
                // this.importStep = 0
                Feedback.open2().then((d)=>{
                    d && (this.importStep = 0)
                })

            }
        }
    },
    computed: {
        importStep: {
            get(){
                return this.step
            },
            set(value){
                this.$emit('update:step', value)
            }
        },
        outcomeList: {
            get(){
                return this.outcomeListProp
            },
            set(value){
                this.$emit('update:outcomeListProp', value)
            }
        },
        secondaryStep: {
            get(){
                return this.secStepProp
            },
            set(value){
                this.$emit('update:secStepProp', value)
            }
        },
        masterObject: {
            get(){
                return this.masterObjectProp
            },
            set(value){
                this.$emit('update:masterObjectProp', value)
            }
        },
        masterTableData:{
            get(){
                return this.masterObject["masterList"]
            },
        },
        masterOptionList:{
            get(){
                return this.masterObject["masterApi"]
            },
        },
        usedOption(){
            var api = this.masterObject["masterApi"]
            for (var item of api){
                item["used"] = []
                for (var each_field of this.masterTableData){
                    var filteredApi = each_field.apiFields.filter((x)=>{
                        return x.apiId == item.apiId
                    })
                    filteredApi[0]["apiFieldList"].length > 0 && item.used.push(...filteredApi[0]["apiFieldList"])
                }
            }
            return api
        },
        fieldMappingList(){
            var result = []
            for (var item of this.masterTableData){
                for (var each_api of item.apiFields){
                    for (var each_apiFieldId of each_api.apiFieldList){
                        result.push({
                            "corporateFieldId": item.corporateFieldId,
                            "apiFieldId" : each_apiFieldId
                        })
                    }
                }
            }
            return result
        },
        
    },
}
</script>


<style scoped>
h1{ 
    background-color:rgba(248, 218, 48, 0.898);
    padding: 15px 10px;
    font-size: 25px;
    text-align: center;
    margin: 0px;
}

.error--color{
    background-color: hsla(336, 77%, 57%, 0.965);
}

.map--transaction--container{
    margin: 30px auto;
    border-radius: 20px;
    width: 80%;
    margin-bottom: 70px;
}

.map--api--container{
    width: 500px;
    margin: 20px 0px;
}

.api1--color{
    background-color: rgb(245, 164, 164);
}

.api2--color{
    background-color: rgb(164, 245, 195);
}

.api3--color{
    background-color: rgb(238, 240, 146);
}

.button--wrapper{
    display: flex;
    justify-content: flex-end;
    margin: 20px 10%;
    gap:0px 10px;
}

/* Post Uploading */
.button--back, .button--continue {
    font-family: 'Poppins', sans-serif;
    font-size: 16px;
    font-weight: 580;
}

.button--back{
    color: rgb(77, 77, 77);
}

.button--back:hover, .button--continue:hover {
    cursor: pointer;
}

@media only screen and (max-width: 1580px) {
    .map--api--container{
        width: 70%;
        min-width: 400px;
    }
}


</style>