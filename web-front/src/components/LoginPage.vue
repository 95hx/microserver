<template>
  <div>
    <div>loginPage</div>
    <el-form :model="loginForm">
      <el-form-item label="账号 ">
        <el-input v-model="loginForm.username" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="密码 ">
        <el-input type="password" v-model="loginForm.password" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">登陆</el-button>
      </el-form-item>
    </el-form>
    <el-alert
      v-show="isFalse"
      title="账号或密码错误"
      type="error">
    </el-alert>

  </div>
</template>

<script>
  import {get_main_page_path} from '../router/path'

  export default {
    data() {
      return {
        isFalse: false,
        loginForm: {
          username: '',
          password: ''
        },
      }
    },
    created: function () {
    },
    methods: {
      onSubmit: function () {
        this.axios.post('/api/user/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(response => {
          if (response.data.code === 200) {
            this.$router.push({path: get_main_page_path()});
          }
        }).catch(e => {
          console.log(e+'login fail');
          this.isFalse=true
        });
      }
    }
  }


</script>

<style scoped>
</style>
