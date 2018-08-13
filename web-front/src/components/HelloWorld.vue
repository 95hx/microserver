<template>
  <div>
    <el-button type="primary" icon="el-icon-search" @click="go">选项一</el-button>
    <el-table :data="tableData">
      <el-table-column prop="date" label="日期" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(20).fill(item)
    }
  },
  methods: {
    go: function () {
      // this.axios.get('/user/1').then(response => {
      //   console.log(response.data)
      // }).catch(function (error) {
      //   console.log(error)
      // })
      console.log(this.tableData)
    }
  }
}
</script>
<style scoped>
</style>
