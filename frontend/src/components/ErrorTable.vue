<template>

    <div class = "map--wrapper">

        <el-table :data="masterErrorList" class = "map--table" max-height="700" style="width: 100%"> 
            <el-table-column prop="error" label="Error List" align="center" sortable fixed  min-width="160"/>          
        </el-table>
    </div>

</template>


<script >

export default {
    name: 'ErrorTable',
    props: ["errorListProp"],
    data() {
        return {
            sampleErrorList: [
                "Error row12: Amount is not within API range",
                "Error row13: Amount is not within API range"
            ]
        }
    },
    computed: {
        masterErrorList() {
            var result = [];

            for (var each_error of this.errorList) {
                result.push({
                    error: each_error
                })
            }
            return result
        },
        errorList: {
            get(){
                return this.errorListProp
            },
            set(value){
                this.$emit('update:errorListProp', value)
            }
        },
    },

}
</script>


<style scoped>

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