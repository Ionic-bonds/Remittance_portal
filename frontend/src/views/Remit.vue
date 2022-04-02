<template>
  <div class="remit">
      <RemitHeader :step = "remitStep" :secStepProp = "secStep"></RemitHeader>

      <Import v-if = "this.remitStep == 0" v-model:file = "file" v-model:step = "remitStep" v-model:mapFieldsProp = "mapFields" v-model:headersColProp = "headersCol" ></Import>

      <importJson v-if = "this.remitStep == 1 && !this.secStep" v-model:manualMappingProp = "manualMapping"></importJson>

      <RemitMap v-if="this.remitStep == 1" v-model:outcomeListProp = "outcomeList" v-model:file = "file" v-model:secStepProp = "secStep" v-model:step = "remitStep" v-model:masterObjectProp = "masterObject" v-model:manualMappingProp = "manualMapping"></RemitMap>
      
      <RemitSuccess v-if = "this.remitStep == 2" :outcomeListProp = "outcomeList" v-model:step = "remitStep"></RemitSuccess>
  </div>
</template>

<script>
// @ is an alias to /src
import RemitHeader from '@/components/RemitHeader.vue'
import Import from '@/components/Import.vue'
import RemitMap from '@/components/RemitMap.vue'
import importJson from '@/components/importJson.vue'
import RemitSuccess from '@/components/RemitSuccess.vue'


export default {
  name: 'Remit',
  data(){
    return{
      remitStep: 0,
      mapFields: [],
      headersCol: [], 
      file: "",
      manualMapping: [],
      masterObject: {},
      secStep: false,
      outcomeList: [],
    }
  },
  components: {
    RemitHeader, Import, RemitMap, importJson, RemitSuccess
  },
  methods: {
    // clickbutton: function (){
    //   console.log(this.mapFields)
    // }
  },
  watch: {
    remitStep: function (val, oldVal) {
        // console.log("old: ", oldVal, ", New Param:", val)
        if (val == 1){
          if (this.mapFields?.apiFieldList && this.headersCol.length > 0){

            // Get Array of unique Apis + add apiFieldList for later mapping
            var subResultObject = this.mapFields.apiFieldList.map((x) => {return {...x["apiData"], apiFieldList: []}}).filter((value, index, arr) => {
              var apiArr = arr.map((x) => x["apiId"])
              return apiArr.indexOf(value["apiId"]) === index
            })

            // Get apiFieldList for each api
            var masterAPI = structuredClone(subResultObject)
            
            for (var each_api of masterAPI){
              var filteredFields = this.mapFields.apiFieldList.filter((x) => x.apiData["apiId"] == each_api["apiId"])
              each_api["apiFieldList"] = filteredFields
            }

            // Merge with Excel Headers (DEEP CLONE: SPEND 4 HOURS DEBUGGING)
            var resultObject = this.headersCol
            resultObject.map((x) => {x.apiFields = structuredClone(subResultObject)})

            // Check initial mapping and add if any
            if(this.manualMapping.length > 0){
              this.mapFields.corporateFieldList = this.manualMapping
            }

            for (var x of resultObject){
              var corporateFieldNameArray = this.mapFields.corporateFieldList.map((y)=> y.corporateFieldName)
              var matchIndex = corporateFieldNameArray.indexOf(x.corporateFieldName)

              if(matchIndex != -1 && this.mapFields.corporateFieldList[matchIndex]["apiFields"].length > 0){
                var currApiFields = this.mapFields.corporateFieldList[matchIndex]["apiFields"]
                for (var apiObject of x.apiFields){
                  var filteredApiFields = currApiFields.filter((y)=>{
                    return y.apiData.apiName == apiObject.apiName
                  }).map((z)=>z["apiFieldId"])
                  apiObject.apiFieldList.push(...filteredApiFields)
                }
              }
            }
            this.masterObject =  {masterApi: masterAPI, masterList: resultObject}
          } 
        }
    },
    // headersCol: function (val, oldVal) {
    //     console.log("old: ", oldVal, ", New Option:", val)
    // },
    // masterObject: function (val, oldVal) {
    //     console.log("old : ", oldVal, ", New Option:", val)
    // },
    manualMapping: function (val, oldVal) {
      if (val.length > 0 && this.masterObject != {}){
        
        for (var x of this.masterObject.masterList){
          
          var corporateFieldNameArray = val.map((y)=> y.corporateFieldName)
          var matchIndex = corporateFieldNameArray.indexOf(x.corporateFieldName)

          if(matchIndex != -1 && val[matchIndex]["apiFields"].length > 0){
            var currApiFields = val[matchIndex]["apiFields"]
            for (var apiObject of x.apiFields){
              var filteredApiFields = currApiFields.filter((y)=>{
                return y.apiData.apiName == apiObject.apiName
              }).map((z)=>z["apiFieldId"])
              apiObject.apiFieldList = []
              apiObject.apiFieldList.push(...filteredApiFields)
            }
          }
        }

      }
      // console.log("old : ", oldVal, ", New Option:", val)
    },
  },
  computed:{

  },
  mounted(){
    // console.log(this.mapFields)
    // console.log(this.headersCol)
    // console.log(this.masterObject)
  }
}
</script>


<style scoped>
/* 
.remit--content--container{
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 50px 10px;
  flex-direction: column;
} */

/* .remit--import{
  width: 100%;
  height: 100%;
} */

</style>