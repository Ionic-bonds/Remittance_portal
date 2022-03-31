<template>

    <div class = "map--wrapper">


        <el-table :data="masterTableData" class = "map--table" max-height="700" style="width: 100%"> 
            <el-table-column prop="corporateFieldName" label="Header Columns" align="center" sortable fixed  min-width="170"/>
            
            <!--  width = "70px" -->
            <el-table-column label="Map" align="center" width="80" fixed>
                <el-icon class = "icon--color"><arrow-right-bold /></el-icon>
            </el-table-column>
            

            <el-table-column  :label="api.apiName" align="center" v-for="api in masterOptionList" min-width="200"> 

                <template #default="scope">
                    <el-select  
                    v-model = "scope.row.apiFields[api.apiId - 1].apiFieldList" placeholder="Select" 
                    multiple 
                    >

                        <el-option
                            v-for="item in api.apiFieldList"
                            :key="item.apiFieldId"
                            :label="item.apiFieldName"
                            :value="item.apiFieldId"
                            :disabled = "disabledFunc(item.apiFieldId, api.apiId, scope.row.apiFields[api.apiId - 1].apiFieldList)"
                        />

                    </el-select>
                </template>

            </el-table-column>

        </el-table>

    </div>

</template>

<script setup>
import { ArrowRightBold } from '@element-plus/icons-vue'
</script>


<script >
// <el-icon><arrow-right-bold /></el-icon>
// <el-icon><d-arrow-right /></el-icon>

// v-model:paramImport = "this.paramAmount" v-model:optionImport = "this.optionProp" v-model:paramData = "this.importParam" v-model:excludeOption = "this.excludeOption" v-model:step = "this.step" v-model:secStep = "this.secondaryStep"
export default {
    name: 'MapTable',
    props: ["masterOptionListProp", "masterTableDataProp", "usedOptionProp"],
    // props: ["paramImport", "optionImport", "paramData", "excludeOption", "step", "secStep"],
    data() {
        return {
        }
    },
    methods: {
        // sortChange: function(sortProp){
        //     console.log(sortProp)
        // },
        disabledFunc: function(idItem, idApi, currList){
            // console.log(idItem, idApi)
            var filteredUsed = this.usedOption.filter((x) => {
                return x.apiId == idApi
            })
            // console.log(filteredUsed[0]["used"].includes(idItem))
            return filteredUsed[0]["used"].includes(idItem) && !currList.includes(idItem)
        }
    },
    computed: {
        masterOptionList:{
            get: function(){
                return this.masterOptionListProp
            },
            set: function(newVal){
                this.masterOptionListProp = newVal
            }
        },
        masterTableData:{
            get: function(){
                return this.masterTableDataProp
            },
            set: function(newVal){
                this.masterTableDataProp = newVal
            }
        },
        usedOption:{
            get: function(){
                return this.usedOptionProp
            },
            set: function(newVal){
                this.usedOptionProp = newVal
            }
        },
        
        // importStep: {
        //     get(){
        //         return this.step
        //     },
        //     set(value){
        //         this.$emit('update:step', value)
        //     }
        // },
        // tableData:{
        //     get(){
        //         return this.paramImport;
        //     },
        //     set(value){
        //         this.$emit('update:paramImport', value)
        //     }
        // },
        // optionData:{
        //     get(){
        //         // console.log(this.optionImport);
        //         return this.optionImport;
        //     },
        //     set(value){
        //         this.$emit('update:optionImport', value)
        //     }
        // },
    },
    mounted() {
        // console.log(this.tableData)
        // console.log(this.step)
        // console.log(this.secStep)
        // console.log(this.excludeOption)
    },

}
</script>


<style scoped>
/* .map--container{
    width: 80vw;
    display: flex;
    align-items: center;
    justify-content: center;
    align-content: center;
    flex-direction: column;
} */
.map--wrapper{
    /* width: 80vw; */
    display: flex;
    align-items: center;
    justify-content: center;
    align-content: center;
    flex-direction: column;
}
.icon--color{
    color: blue;
}
</style>