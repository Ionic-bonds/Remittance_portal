<template>
    <div class = "map--wrapper">

    <!-- First part of the mapping -->

    <div class="map--transaction--container">
        <h1>Map Transaction Column</h1>
        <MapTable v-model:masterTableDataProp = "masterTableData"  v-model:masterOptionListProp = "masterOptionList" v-model:usedOptionProp = "usedOption"></MapTable>
    </div>   


    <!-- Buttons -->
    <div class = "button--wrapper">
        <button @click = "this.back" class = "button--back">{{'<'}} Back</button>
        <button @click="this.continue" class = "button--continue">Continue {{'>'}}</button>
    </div>
    </div>

</template>



<script >
import MapTable from "./MapTable.vue"
import Feedback from './Feedback.vue'
import { ElLoading } from 'element-plus'
import ApiDataService from "../api/ApiDataService";


// <el-icon><arrow-right-bold /></el-icon>
// <el-icon><d-arrow-right /></el-icon>

export default {
    name: 'RemitMap',
    props: ["step","masterObjectProp", "file", "manualMappingProp"],
    components: {MapTable},
    data() {
        return {
            value: "",
            // amountJson: paramJson.param.slice(0,1), // first occurance as the money param .slice(0,1)
            // paramJson: paramJson.param.slice(1),
            // optionJson: optionJson.option,
            secondaryStep: false,
            truthyValue: false,
            excludeOption: [],
            loadinstance: "",
        }
    },
    methods: {
        stoploading: (l) =>{
            l.close()
        },
        continue: function(){

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


                    console.log("invoking API 24: Add Mappings")
                    ApiDataService.addMapping(this.fieldMappingList)
                    .then((response)=>{
                        // console.log(response) // result not required since its just uploading to db

                        this.loadinstance = ElLoading.service({
                            lock: true,
                            text: 'Validating and uploading...',
                            background: 'rgba(0, 0, 0, 0.7)',
                        })

                        console.log("invoking API 27: validate and upload file")        
                        const data = new FormData()
                        this.uploadFile = this.file
                        data.append("file", this.file)
                        ApiDataService.validateMap(data, this.$store.getters.user)
                        .then((response)=>{
                            console.log(response)

                            this.stoploading(this.loadinstance)


                        }).catch((e)=>{
                            var errMsg 
                            (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                            console.log(errMsg)
                            Feedback.open5(errMsg, "Something's Wrong!")
                        })

                    }).catch((e)=>{
                        var errMsg 
                        (e?.response?.data) ? (errMsg = e.response.data.message) : (errMsg = e.message)
                        console.log(errMsg)
                        Feedback.open5(errMsg, "Something's Wrong!")
                    })


                }
            })
            return
            // console.log(this.masterTableData)
            // console.log(this.fieldMappingList)
            // this.masterTableData[0]["apiFields"][0]["apiFieldList"] = []


            // const loadingInstance = ElLoading.service({
            //     lock: true,
            //     text: 'Loading',
            //     background: 'rgba(0, 0, 0, 0.7)',
            // })

            // if (!this.secondaryStep) {
            //     if (this.paramAmount[0]["map"]){
            //         // TODO: Get API involved
            //         this.secondaryStep = !this.secondaryStep
            //         loadingInstance.close()
                    
            //     } else{
            //         // Feedback.loadingScreen
            //         loadingInstance.close()
            //         Feedback.open5("Please select a column.", "Error")
            //     }
                
            // } else {
            //     // TODO: validation before uploading
            //     console.log("validation Before uploading")
            //     loadingInstance.close()
            // }
        },
        back: function(){
            if (this.secondaryStep)  {
                this.secondaryStep = !this.secondaryStep
            } else {
                // this.importStep = 0
                Feedback.open2().then((d)=>{
                    d && (this.importStep = 0)
                })

            }

            // console.log(this.importParam)
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
                    // console.log(filteredApi[0]["apiFieldList"])
                    // filteredApi[0]["apiFieldList"].length > 0 && console.log(filteredApi[0]["apiFieldList"])
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
    mounted() {
        // console.log(this.file)
        // console.log(this.optionProp);
        // console.log(this.paramProp);
        // console.log(this.api1Param)
        // console.log(this.tableData)
        // console.log(this.amountJson)
        // console.log(JSON.parse(JSON.stringify(this.amountJson)));
        // console.log(JSON.parse(JSON.stringify(this.paramJson)));
    },
    watch: {
        // masterTableData: {
        //     handler: function (val, oldVal) {
        //         console.log(val, oldVal)
        //         // var getAmountFields = this.paramProp.filter((x) => x["datatype"] == "Number")
        //         // getAmountFields.map((x)=>{x["map"] = val[0]["map"]})

        //         // var api = this.masterObject["masterApi"]
        //         // for (var item of api){
        //         //     item["used"] = []
        //         //     for (var each_field of this.masterTableData){
        //         //         var filteredApi = each_field.apiFields.filter((x)=>{
        //         //             return x.apiId == item.apiId
        //         //         })
        //         //         // console.log(filteredApi[0]["apiFieldList"])
        //         //         filteredApi[0]["apiFieldList"].length > 0 && console.log(filteredApi[0]["apiFieldList"])
        //         //         filteredApi[0]["apiFieldList"].length > 0 && item.used.push(...filteredApi[0]["apiFieldList"])
        //         //     }
        //         // }
        //         // console.log(api)
        //     },
        //     deep:true
        // },
    },
}
</script>


<style scoped>
h1{ 
    background-color:rgba(248, 218, 48, 0.898);
    padding: 15px 10px;
    font-size: 25px;
    /* border-radius: 5px; */
    text-align: center;
    /* border-radius: 20px 20px 0px 0px; */
    /* height: 50px; */
    margin: 0px;
    /* display: inline-block; */

}


.map--transaction--container{
    margin: 30px auto;
    border-radius: 20px;
    width: 80%;
    margin-bottom: 70px;
}




.map--api--container{
    /* display: flex; */
    /* justify-content: center; */
    /* flex-direction: column; */
    width: 500px;
    /* align-content: flex-start; */
    margin: 20px 0px;
    /* height: 50px; */    
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

.button--back, .button--continue {
    font-family: 'Poppins', sans-serif;
    font-size: 18px;
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