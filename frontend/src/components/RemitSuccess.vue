<template>
    <div class = "success--wrapper">
    <div class = "success--result">
      <el-result
        icon="success"
        title="Remit Successful!"
        sub-title="Check Transaction Details Below"
        class = "success--result"
      >
      </el-result>
      <button class = "success--button" @click="goBack">Remit Again~</button>
    </div>

    <div v-for="apiList in this.masterList" class="success--table">
        <h1 class = "success--h">{{capitalizeFirstLetter(apiList.apiName)}}</h1>

        <el-table :data="apiList.outcome" class = "map--table" max-height="700" style="width: 100%"> 
                <el-table-column :fixed="each_key == 'Index'" :prop = "each_key" :label="each_key" align="center" sortable min-width="170" v-for="each_key in Object.keys(apiList.outcome[0])"/>            
        </el-table>
    </div>

    </div>

</template>


<script >
export default {
    name: 'RemitSuccess',
    data() {
        return { 

        }
    },
    methods: {
        capitalizeFirstLetter: function(string) {
            return string.charAt(0).toUpperCase() + string.slice(1);
        },
        goBack: function(){
            this.importStep = 0
        }

    },
    props: ["outcomeListProp"],
    computed: {
        importStep: {
            get(){
                return this.step
            },
            set(value){
                this.$emit('update:step', value)
            }
        },
        apiList(){
            var allApiList = this.outcomeListProp.map((x) => x["apiName"])
            return allApiList.filter((x, i, a) => a.indexOf(x) === i)
        },
        masterList(){
            var result = []
            for (var each_api of this.apiList){
                var subresult = []
                var filteredList = this.outcomeListProp.filter((x) => x["apiName"] == each_api)

                var counter = 1
                for (var each_outcome of filteredList){
                    subresult.push({
                        Index: counter,
                        ... each_outcome["commonApi"],
                        outcome : each_outcome["outcome"]
                    })
                    counter ++
                }

                result.push({
                    apiName: each_api,
                    outcome: subresult
                })
            }
            return result
        }
    },
}
</script>


<style scoped>
.success--result{
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgb(252, 230, 234);
    width: 80%;
    margin: 0 auto;
    border-radius: 10px;
    flex-direction: column;
    margin-bottom: 0px;
}

.success--table{
    display: flex !important;
    flex-direction: column;
    align-items: center;
    width: 80% !important;
    margin: 0 auto !important;
}


.success--h{
    background-color: rgb(133, 240, 222);
    width: 100%;
    text-align: center;
    margin: 20px 0px 0px 0px;
    border-radius: 10px 10px 0px 0px;
}

/* CSS */
.success--button {
  background-image: linear-gradient(#42A1EC, #0070C9);
  border: 1px solid #0077CC;
  border-radius: 4px;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  direction: ltr;
  display: block;
  font-family: "SF Pro Text","SF Pro Icons","AOS Icons","Helvetica Neue",Helvetica,Arial,sans-serif;
  font-size: 17px;
  font-weight: 400;
  letter-spacing: -.022em;
  line-height: 1.47059;
  min-width: 30px;
  overflow: visible;
  padding: 4px 15px;
  text-align: center;
  vertical-align: baseline;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  margin-bottom: 20px;
}

.success--button:disabled {
  cursor: default;
  opacity: .3;
}

.success--button:hover {
  background-image: linear-gradient(#51A9EE, #147BCD);
  border-color: #1482D0;
  text-decoration: none;
}

.success--button:active {
  background-image: linear-gradient(#3D94D9, #0067B9);
  border-color: #006DBC;
  outline: none;
}

.success--button:focus {
  box-shadow: rgba(131, 192, 253, 0.5) 0 0 0 3px;
  outline: none;
}

</style>