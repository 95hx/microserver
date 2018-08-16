<template>
  <div>
    <el-button type="primary" icon="el-icon-search" @click="refresh">Home</el-button>
    <el-table :data="tableData">
      <el-table-column prop="username" label="账号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="age" label="年龄">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      const item = {
        username: '默认账户',
        name: '默认名',
        age: '23'
      };
      return {
        tableData: Array(20).fill(item)
      }
    },
    created: function () {
      this.axios.get('/api/user/all').then(response => {
        this.tableData = response.data
      }).catch(function (error) {
        console.log(error)
      })
    },
    methods: {
      handleClick: function (row) {
        console.log(row.age)
      },
      refresh: function () {
        this.axios.get('/api/user/all').then(response => {
          this.tableData = response.data
        }).catch(function (error) {
          console.log(error)
        })
      }
    }
  }
</script>
<style scoped>
</style>
