<template>
  <div>
    <el-container>

      <el-footer>
        <el-row :gutter="20" v-for="item in tableData">
          <el-col :span="16">
            <div class="grid-content bg-purple">
              <a href="https://www.baidu.com">{{item.title}}</a>
              <p>{{item.content}}</p>
              <div>
                <a href="https://www.baidu.com">{{item.readCount}}</a>
                <a href="https://www.baidu.com">{{item.thumbsUpCount}}</a>
                <span>{{item.createTime}}</span>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">图片</div>
          </el-col>
        </el-row>

      </el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    methods: {
      refresh: function () {
        this.axios.get('/api/article/all').then(response => {
          if (response.data.code === 200) {
            this.tableData = response.data.data;
          } else {
            console.log("http code not 200");
          }
        })
      },
    },
    created: function () {
      this.refresh();
    },
    data() {
      return {
        tableData: []
      }

    }
  }

</script>

<style scoped>
  .bg-purple {
    background: #d3dce6;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 120px;
  }
</style>
