<template>
  <div>

    <!--添加用户按钮-->
    <el-button type="primary" icon="el-icon-search" @click="addUserClick">添加</el-button>

    <!--添加 用户dialog-->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" :before-close="handleUserClose">
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
        <el-button type="primary" @click="saveUserClick">确 定</el-button>
      </div>
    </el-dialog>

    <!--table column-->
    <el-table :data="tableData" v-loading="loading">
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
          <el-button @click="deleteUserClick(scope.row)" type="text" size="small">删除</el-button>
          <el-button @click="editUserClick(scope.row)" type="text" size="small">编辑</el-button>
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
        loading: true ,
        form: {
          id: '',
          name: '',
          age: '',
          username: ''
        },
        title: '添加用户'
      }
    },

    created: function () {
      this.refreshUser();
    },

    methods: {

      editUserClick: function (row) {
        this.title = '编辑用户'

        this.dialogFormVisible = true
        this.form = JSON.parse(JSON.stringify(row));
      },

      addUserClick: function () {
        this.title = '保存用户'
        this.form = {
          id: '',
          name: '',
          age: '',
          username: ''
        }
        this.dialogFormVisible = true
      },

      deleteUserClick: function (row) {
        this.axios.delete('/api/user/'+row.id).then(response => {
          if (response.data.code === 200) {
            this.refreshUser()
          } else {
          }
        })
      },

      refreshUser: function () {
        this.axios.get('/api/user/all').then(response => {
          if (response.data.code === 200) {
            this.tableData = response.data.data
            this.loading = false;
            console.log("http code is 200");
          } else {
            console.log("http code not 200");
          }
        })
      },

      handleUserClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            console.log("close")
            done();

          })
          .catch(_ => {
            console.log("close")
          });
      },

      //保存/更新 user
      saveUserClick() {
        this.axios.post('/api/user/add', {
          id: this.form.id,
          username: this.form.username,
          name: this.form.name,
          age: this.form.age
        }).then(response => {
          if (response.data.code === 200) {
            console.log("success save")
            this.refreshUser()
          } else {
            console.log("http code not 200")
          }
        })
        this.dialogFormVisible = false
      }

    }
  };
</script>
<style scoped>
</style>
