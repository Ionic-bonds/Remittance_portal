<template>
    <div class = "progressContainer">
        <el-steps v-if = "!this.secStep" :active="this.step" align-center :process-status = "this.processProp" :finish-status = "finish">
            <el-step title="Step 1" description="Import files"></el-step>
            <el-step title="Step 2" description="Mapping Columns"></el-step>
            <el-step title="Step 3" description="Remit Details"></el-step>
        </el-steps>
        <el-steps v-if = "this.secStep" :active="this.step" align-center :process-status = "this.processProp" :finish-status = "finish">
            <el-step title="Step 1" description="Import files"></el-step>
            <el-step  title="Step 2" description="Error!"></el-step>
            <el-step title="Step 3" description="Remit Details"></el-step>
        </el-steps>
    </div>
</template>


<script >
// status wait / process / finish / error / success
// finish - success; error - error, process - process
// direction="vertical"
export default {
    name: 'Progress',
    data() {
        return { 
            // process: "process",
            finish: "success",
        }
    },
    computed: {
        processProp() {
            if (this.secStep){
                return "error"
            } else {
                return "process"
            }
        },
    },
    watch: {
        // secStep: function (val, oldVal) {
        //     console.log("old: ", oldVal, ", New:", val)
        //     console.log(this.processProp)
        // }
    },
    props: ["step", "secStep"]
}
</script>


<style scoped>
.progressContainer{
    background-color: rgba(255, 255, 255, 1);
    padding: 30px 0px;
    margin: 20px 40px;
    border-radius: 10px;
    /* text-weight: bold; */
}
</style>