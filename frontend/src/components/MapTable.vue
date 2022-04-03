<template>

    <div class = "map--wrapper">


        <el-table :data="masterTableData" class = "map--table" max-height="700" style="width: 100%"> 
            <el-table-column prop="corporateFieldName" label="Header Columns" align="center" sortable fixed  min-width="170"/>
            
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

export default {
    name: 'MapTable',
    props: ["masterOptionListProp", "masterTableDataProp", "usedOptionProp"],
    data() {
        return {
        }
    },
    methods: {

        disabledFunc: function(idItem, idApi, currList){
            var filteredUsed = this.usedOption.filter((x) => {
                return x.apiId == idApi
            })
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

    },

}
</script>


<style scoped>

.map--wrapper{
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