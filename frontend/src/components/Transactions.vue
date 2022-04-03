<template>
  <div class="transactions">

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
    ApiDataService.getTransactionReq(this.$store.getters.user)
    .then((response) => {
      this.tableData = response.data.reverse();
      for (var each_data of this.tableData){

        // get error list
        if (each_data["statusCode"] == 200){
          this.post_status = each_data["message"].substring(each_data["message"].indexOf(":") + 2);
          each_data["errList"] = this.post_status.split("\n");
          // removes empty string in the list, if any
          // cannot use pop as it might remove arr w one err messsages
          // each_data["errList"].filter(n => n);
          each_data["errList"] = each_data["errList"].filter((element) => {
            return /\S/.test(element);
          });
        } else {
          var err_list = new Array();
          err_list.push(each_data["message"]);
          each_data["errList"] = err_list;
        }

        // get status - Failed or not?
        if (each_data["statusCode"] == 200){
          each_data["message"] = each_data["message"].substring(each_data["message"].indexOf(" ") + 1, each_data["message"].indexOf(":"));
        };
      }
    })
    .catch((e) => {
      Feedback.open5(errMsg, "Something's Wrong!")
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