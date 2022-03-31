<template>
    <div class = "progressContainer">
        <el-steps :active="this.step" align-center :process-status = "process" :finish-status = "finish">
            <el-step title="Step 1" description="Import files"></el-step>
            <el-step title="Step 2" description="Mapping Columns"></el-step>
            <el-step title="Step 3" description="Remit"></el-step>
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
            process: "process",
            finish: "success",
        }
    },
    props: ["step"]
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