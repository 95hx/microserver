<template>
  <div>
    <!--add dialog button-->
    <el-button type="primary" icon="el-icon-search" @click="dialogFormVisible=true">添加</el-button>
    <!--add dialog-->
    <el-dialog title="增加用户" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form :model="form">
        <el-form-item label="账号" :label-width="formLabelWidth">
          <el-input v-model="form.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input v-model="form.age" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--table column-->
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
        tableData: Array(20).fill(item),
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
          name: '',
          region: '',
          age: '',
          username: ''
        },
      }
    },
    created: function () {
      this.refresh();
    },
    methods: {
      handleClick: function (row) {
        console.log(row.age)
      },
      refresh: function () {
        this.axios.get('/api/user/all').then(response => {
          if (response.data.code === 200) {
            this.tableData = response.data.data
          } else {
            console.log("http code not 200");
          }
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            console.log("close")
            done();

          })
          .catch(_ => {
            console.log("close")
          });
      },/*axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });*/
      addUser(){
        this.axios.post('/api/user/add',{
          username: this.form.username,
          name: this.form.name,
          age: this.form.age
        }).then(response => {
          if (response.data.code === 200) {
            console.log("success save")
          } else {
            console.log("http code not 200")

          }
        })
        this.dialogFormVisible = false
      }
    }
  }
</script>
<style scoped>
</style>
