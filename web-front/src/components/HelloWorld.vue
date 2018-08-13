<script src="../../../../rootxj/static/admin-master/src/api/api.js"></script>
<template>
  <div>
    <button @click="go">hello</button>
  </div>
</template>

<script>
export default {
  methods: {
    go: function () {
      this.$router.push({path: '/test'})
      this.axios.get('/user/1').then(response => {
        console.log(response.data)
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>
<style scoped>
</style>
