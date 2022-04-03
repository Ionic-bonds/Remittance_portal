<template>
  <div class="transactions">
    <!-- <span style="color: white">{{ tableData }}</span> <br><br>
    <span style="color: white">{{ tableData[0].message }}</span> <br><br>
    <span style="color: white">{{ tableData.length }}</span> -->
    <!-- <span style="color: white">{{ getStatus(0) }}</span> -->

    <!-- Main design -->
      <div class="common-layout" align="center" style="padding: 1rem 2rem 0">
        <el-container>
          <el-header class="standard_text">History</el-header>
          <el-main>
            <!-- Date Picker -->
            <div class="demo-date-picker">
              <!-- <p>test{{value}}</p> -->
              <div class="block">
                <el-date-picker
                  v-model="value"
                  type="daterange"
                  start-placeholder="Start date"
                  end-placeholder="End date"
                  :default-time="defaultTime"
                />
              </div>
            </div>
            <el-scrollbar height="650px">    
               <!--Table-->
                <el-descriptions
                  title=""
                  direction="vertical"
                  :column="3"
                  :size="small"
                  border
                  v-for="each_data in tData" :key="each_data"
                >
                  <el-descriptions-item label="API Req ID">{{ each_data.userApiRequestId }}</el-descriptions-item>
                  <el-descriptions-item label="Date">{{ new Date(each_data.requestDate) }}</el-descriptions-item>
                  <el-descriptions-item label="Status">
                    <el-tag v-if="each_data.message == 'Failed'" size="large">Failed</el-tag>
                    <el-tag v-else size="large" class="success">Sucess</el-tag>
                  </el-descriptions-item>
                  <el-descriptions-item :span="3" label="Remarks">
                    <ol>
                      <li v-for="err in each_data.errList" :key="err">
                        {{ err }}
                      </li>
                    </ol>
                  </el-descriptions-item>
                </el-descriptions>
            </el-scrollbar>
          </el-main>
        </el-container>
      </div>
  </div>
</template>

<script>
import ApiDataService from "../api/ApiDataService";
// import { reactive } from "vue";

// let tableData = reactive({
//   arr: ["tableData"], //store the table data here
// });
// console.log(tableData);
// console.log("Reactive tableData");

export default {
  name: "Transactions",
  data() {
    return {
      tableData: [],
      value: [],
      defaultTime: ([
        new Date(2000, 1, 1, 0, 0, 0),
        new Date(2000, 2, 1, 23, 59, 59),
      ]),
    };
  },
  methods: {
    history() {
      // console.log("calling API - transactions");
      // ApiDataService.getTransactionReq(this.$store.getters.user)
      //   .then((response) => {
      //     this.tableData = response.data;
      //     console.log(this.tableData);
      //   })
      //   .catch((e) => {
      //     console.log(e);
      //   });
    },
    checkDate(from, to, tocheck){
      var fDate, lDate, cDate;
      fDate = Date.parse(from);
      lDate = Date.parse(to);
      cDate = Date.parse(tocheck);

      if((cDate <= lDate && cDate >= fDate)) {
          return true;
      }
      return false;
      }
  },
  // to get / filter the tableData by date range (value : [])
  computed: {
    tData: function(){
      // if value is empty, return all the data (aka no filtering done)
      if (this.value.length == 0){
        return this.tableData;
      }
      // if value has elements, means user wants to filter by date
      return this.tableData.filter(each_data => {
        return this.checkDate(this.value[0], this.value[1], each_data.requestDate);
      });
    }
  },
  created() {
    // console.log("Transactions mounted");
    // this.history();
    console.log("calling API - transactions");
    ApiDataService.getTransactionReq(this.$store.getters.user)
    .then((response) => {
      this.tableData = response.data.reverse();
      for (var each_data of this.tableData){
        console.log(each_data.message);

        // get error list
        this.post_status = each_data["message"].substring(each_data["message"].indexOf(":") + 2);
        each_data["errList"] = this.post_status.split("\n");
        each_data["errList"].pop();

        // get status - Failed or not?
        each_data["message"] = each_data["message"].substring(each_data["message"].indexOf(" ") + 1, each_data["message"].indexOf(":"));
      }
      console.log(this.tableData);
      console.log(this.value);
      console.log(this.defaultTime);
    })
    .catch((e) => {
      console.log(e);
    });
  },
};
</script>


<style scoped>
.standard_text{
  text-align: center; 
  font-size: 3.5rem; 
  font-weight: bold;
  color: rgba(123, 245, 166, 0.932);
  margin: 3rem 0 3.5rem;
}

/* Date picker / filter */
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-date-picker .block:last-child {
  border-right: none;
}

.el-descriptions {
  margin-top: 40px;
}

.el-header {
  height: 10px;
}

/* Fail vs Success Tags */
.el-tag {
	--el-tag-bg-color: var(--el-color-danger-light-9);
  --el-tag-border-color: var(--el-color-danger-light-5);
  --el-tag-text-color: red;
}

.success {
	--el-tag-bg-color: var(--el-color-success-light-9);
  --el-tag-border-color: green;
  --el-tag-text-color: green;
}
</style>