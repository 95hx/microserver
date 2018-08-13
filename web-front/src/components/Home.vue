<template>
  <div>
    <el-button type="primary" icon="el-icon-search" @click="go">Home</el-button>
    <el-table :data="tableData">
      <el-table-column prop="username" label="账号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="age" label="年龄">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    const item = {
      username: '用户',
      name: '王小虎',
      age: '23'
    };
    return {
      tableData: Array(20).fill(item)
    }
  },
  methods: {
    go: function () {
      this.axios.get('/api/user/all').then(response => {
        this.tableData=response.data
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>
<style scoped>
</style>
