<template>
  <div>
    <el-container>
      <el-header>{{item.title}}</el-header>

      <el-main>{{item.content}}</el-main>
      <el-footer>{{item}}</el-footer>

    </el-container>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        articleId: '',
        item: {}
      }
    },
    /**
     * 根据id获取文章详情
     */
    created: function () {
      if (this.$route.params.articleId == null)
        //默认1
        this.articleId = 1;
      else
        this.articleId = this.$route.params.articleId
      this.axios.get('/api/article/' + this.articleId).then(response => {
        if (response.data.code === 200) {
          this.item = response.data.data;
        } else {
          console.log("http code not 200");
        }
      })
    },
    methods: {}
  }


</script>

<style scoped>
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
</style>
