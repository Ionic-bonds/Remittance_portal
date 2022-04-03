<template>
    <div class="footer">
        <p class="footer--left">© SMUX - Object Oriented Programming - G2 - Group 2</p>
        <p class="footer--right">Partner with TechG: January - April 2022</p>
    </div>

</template>


<script >
export default {
    name: 'Footer',
    data() {
        return { 
        }
    },

}
</script>


<style scoped>
.footer{
    font-family: 'Open Sans Condensed';
    font-weight: 700;
    font-size: 18px;
    line-height: 33px;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    background-color: rgba(51, 51, 51, 1);
    color: white;
    min-width: 550px;
    z-index: 99;
}

.footer--left{
    float: left;
    margin: 20px;
}
.footer--right{
    float: right;
    margin: 20px;
}

.empty--div{
    height: 100px;
}

@media only screen and (max-width: 730px) {

    .footer--left, .footer--right{
        float: none;
        text-align: center;
        margin: 20px 0px;
    }

}

</style>