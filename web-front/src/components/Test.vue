<template>
  <div>
    <button @click="go">test</button>
  </div>
</template>

<script>
export default {
  methods: {
    go: function () {
      this.$router.push({path: '/'})
    }
  }
}
</script>

<style scoped>
</style>
