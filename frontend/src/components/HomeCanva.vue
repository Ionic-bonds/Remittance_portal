<template>
  <div>
    <el-row class="home--container">
      <el-col :sm="11" class="nav--image--container hidden-sm-and-down">
        <img class="nav--image" src="../assets/TechG_front.jpg" />
      </el-col>
      <el-col :sm="22" :md="13" class="home--intro--container">
        <h1 class="home--intro--title">
          Support transactions for what matters the most to you. <br />
          <img class="g-image" src="../assets/G.png" />
          uaranteed easy.
        </h1>
        <p class="home--intro--subtitle">
          Import your own data file to our system.
          <span style="font-weight: bolder; color: rgb(123, 245, 166)"
            >It's that simple.</span
          >
        </p>
      </el-col>
    </el-row>

    <el-divider class="divider"></el-divider>

    <el-row :gutter="20" style="margin: 3rem 5rem">
      <el-col :span="24" class="standard_text" style="padding-bottom:1rem">OUR SEAMLESS SOLUTION, YOUR DIGITAL HEADSTART</el-col>
      <el-col :span="24">
        <el-carousel style="margin: 10px" :interval="4000" type="card" height="250px">

          <el-carousel-item align="center">
            <img class="icon-image" style="padding-top:20px" src="../assets/budget.png" />
            <h3 style="text-align: center">
              Easy Reconciliation & Tracking</h3>
            <p>Our simple and intuitive account statements are hard not to love. Download your transaction history anytime and make your reconciliations a breeze.</p>
          </el-carousel-item>

          <el-carousel-item align="center">
            <img class="icon-image" style="padding-top:20px" src="../assets/collaborative.png" />
            <h3 style="text-align: center">
              Excel Uploads for Mass Payments</h3>
            <p>Simply upload an Excel File with multiple transactions and let our solutions sort the APIs for you. No technical integration required.</p>
          </el-carousel-item>

          <el-carousel-item align="center">
            <img class="icon-image" style="padding-top:20px" src="../assets/budget.png" />
            <h3 style="text-align: center">
              Easy Reconciliation & Tracking</h3>
            <p>Our simple and intuitive account statements are hard not to love. Download your transaction history anytime and make your reconciliations a breeze.</p>
          </el-carousel-item>

        </el-carousel>
      </el-col>
    </el-row>

    <el-row :gutter="50" style="margin: 7rem">
      <el-col :span="24" class="standard_text" style="padding-bottom:3rem">WHAT DO WE HAVE?</el-col>
      <el-col :span="24"><div/>
        <el-tabs :tab-position="this.tabPosition" style="height: 200px" class="demo-tabs">
          <el-tab-pane label="User">User</el-tab-pane>
          <el-tab-pane label="Config">Config</el-tab-pane>
          <el-tab-pane label="Role">Role</el-tab-pane>
          <el-tab-pane label="Task">Task</el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>

    <div class="demo-image">
      <div v-for="i in 6" :key="i" class="block">
        <span class="demonstration">{{ names[i - 1] }}</span>
        <el-image style="width: 100px; height: 100px" :src="url" :fit="cover" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeCanva",
  data() {
    return {
      //   fits: ["fill", "contain", "cover", "none", "scale-down"],
      names: [
        "Cheow Tianle",
        "David Lim",
        "Gan Shaohong",
        "Leonard Wong",
        "Wang Shurui",
        "Yeo Yu Quan",
      ],
      url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      tabPosition: "left",
    };
  },
};
</script>

<style scoped>
.home--intro--container {
  display: flex;
  /* align-items: center; */
  justify-content: center;
  flex-direction: column;
  padding: 6rem 4rem 6rem 3rem;
}

.home--intro--title {
  font-family: "Poppins", sans-serif;
  font-style: 700px;
  font-size: 30px;
  color: white;
  /* margin: 120px 50px 40px 80px; */
}

.home--intro--subtitle {
  font-family: "Poppins", sans-serif;
  color: white;
  /* margin: 10px 50px 40px 80px; */
}

.nav--image--container {
  display: flex;
  justify-content: center; /* align horizontal */
  align-items: center; /* align vertical */
}
.nav--image {
  margin: 0px 10px;
  width: 30rem;
}

.divider {
  margin: 0 auto;
  width: 50%;
}

.g-image {
  margin: 0px -7px -3px -2px;
  width: 22px;
}

.standard_text{
  text-align: center; 
  font-size: 25px; 
  font-weight: bold;
  color: rgba(123, 245, 166, 0.932);
}

/* Carousel */

/* .el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
} */

/* .carousel_title{
  color: rgb(255, 78, 47); 
  font-size: 18px;
  font-weight: bold;
  background: transparent;
} */

.icon-image{
  width: 55px;
}

.el-carousel__item h3 {
  border-radius: 10px;
  /* opacity: 0.95; */
  line-height: 45px;
  margin: 0;
  text-align: center;
  color: rgb(255, 78, 47); 
  font-size: 18px;
  font-weight: bold;
}

.el-carousel__item > p{
  margin: 10px 35px;
  color: black;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
  border-radius: 10px;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
  border-radius: 10px;
}

/* Tabs - Extra Info */
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.el-tabs--right .el-tabs__content,
.el-tabs--left .el-tabs__content {
  height: 100%;
}


/* Final demographics image */
.demo-image .block {
  padding: 20px 0;
  text-align: center;
  border-right: solid 1px #9ea19c;
  display: inline-block;
  width: 16%;
  box-sizing: border-box;
  vertical-align: top;
}

.demo-image .block:last-child {
  border-right: none;
}

.demo-image .block:first-child {
  margin-left: 20px;
}

.demo-image .demonstration {
  display: block;
  color: rgb(81, 49, 22);
  color: rgb(123, 245, 166);
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 20px;
}
</style>
