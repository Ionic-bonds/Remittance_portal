<template>
    <div>
        <h1 class = "rh--header">Remit</h1>
        <p class = "rh--subheader">A seemless and efficient remittance platform that connects you across countries</p>
        <Progress :step = "step" :secStep = "secStepProp"/>

        <hr/>
    </div>
</template>


<script >
import Progress from "./Progress.vue"

export default {
    name: 'RemitHeader',
    data() {
        return { 
        }
    },
    components: {
        Progress
    },
    props: ['step', "secStepProp"],
}
</script>


<style scoped>
.rh--header{
    color: rgba(255, 255, 255, 1);
    font-family: 'Poppins', sans-serif;
    font-weight: bold;
    font-size: 48px;
    text-align: center;
}

.rh--subheader{
    color: white;
    font-family: 'Poppins', sans-serif;
    text-align: center;
}

</style>