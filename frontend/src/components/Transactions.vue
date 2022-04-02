<template>
  <div class="transactions">
    <h3>hello world</h3>
    <!-- Created -->
    <!-- response.data -->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="date" label="Date" width="250"></el-table-column>
      <el-table-column prop="type" label="Type" align="center" width="150">
      </el-table-column>
      <el-table-column
        prop="description"
        label="Desc"
        align="center"
        width="180"
      >
      </el-table-column>
      <el-table-column prop="income" label="Income" align="center" width="170">
      </el-table-column>
      <el-table-column prop="expend" label="Expand" align="center" width="170">
      </el-table-column>
      <el-table-column prop="cash" label="Cash" align="center" width="170">
      </el-table-column>
      <el-table-column prop="remark" label="Remark" align="center" width="220">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import ApiDataService from "../api/ApiDataService";
import { reactive } from "vue";

let tableData = reactive({
  arr: [this.tableData], //store the table data here
});
console.log(tableData);
console.log("Reactive tableData");

export default {
  name: "Transactions",
  props: ["masterApiRequestsProp"],
  data() {
    return {
      tableData: tableData.arr,
    };
  },
  methods: {
    history() {
      console.log("calling API - transactions");
      ApiDataService.getTransactionReq(this.$store.getters.user)
        .then((response) => {
          tableData = response.data;
          console.log(tableData);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  computed: {
    masterApiRequests:{
        get: function(){
            return this.masterApiRequestsProp
        },
        // set: function(newVal){
        //     this.masterApiRequestsProp = newVal
        // }
    }
  },
  mounted() {
    console.log("Transactions mounted");
    this.history();
  },
};
</script>
