<template>
  <div>
    <div>loginPage</div>
    <el-form :model="loginForm">

    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        loginForm: {
          account: '',
          pass: ''
        },
      }
    },
    created: function () {
    },
    methods: {
    }
  }


</script>

<style scoped>

</style>
