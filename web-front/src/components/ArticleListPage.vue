<template>
  <div>

    <!--添加文章按钮-->
    <el-button type="primary" icon="el-icon-search" @click="addArticleClick">添加</el-button>

    <!--添加 文章dialog-->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" :before-close="handleArticleClose">
      <el-form :model="form">
        <el-form-item label="标题" :label-width="formLabelWidth">
          <el-input v-model="form.title" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容" :label-width="formLabelWidth">
          <el-input v-model="form.content" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveArticleClick">确 定</el-button>
      </div>
    </el-dialog>

    <!--table column-->
    <el-container>
      <el-footer>
        <el-row :gutter="20" v-for="item in tableData"  :key="item.id" v-loading="loading">
          <el-col :span="16">
            <div class="grid-content bg-purple">
              <el-button @click="articleDetailClick(item.id)">{{item.title}}</el-button>
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
  import {get_article_detail_page_path} from '../router/path'

  export default {
    /**
     * 跳转到文章详情
     */
    methods: {
      articleDetailClick: function (id) {
        this.$router.push({name: get_article_detail_page_path(), params: {articleId: id}})
      },
      handleArticleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            console.log("close")
            done();

          })
          .catch(_ => {
            console.log("close")
          });
      },
      saveArticleClick: function () {
        this.axios.post('/api/article/add', {
          id: this.form.id,
          title: this.form.title,
          content: this.form.content,
        }).then(response => {
          if (response.data.code === 200) {
            console.log("success save")
            this.refreshArticle()
          } else {
            console.log("http code not 200")
          }
        })
        this.dialogFormVisible = false
      },
      refreshArticle: function () {
        this.axios.get('/api/article/all').then(response => {
          if (response.data.code === 200) {
            this.tableData = response.data.data;
            this.loading = false;
            console.log("http code is 200")
          } else {
            console.log("http code not 200");
          }
        })
      },
      addArticleClick: function () {
        this.title = '保存文章'
        this.form = {
          id: '',
          title: '',
          content: '',
          readCount: '',
          thumbsUpCount: '',
          userId: ''
        }
        this.dialogFormVisible = true
      },
    },
    created: function () {
      this.refreshArticle();
    },
    data() {
      return {
        tableData: [],
        loading: true,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
          id: '',
          title: '',
          content: '',
          readCount: '',
          thumbsUpCount: '',
          userId: ''
        },
        title: '添加用户'
      }

    }
  }

</script>

<style scoped>
  .bg-purple {
    background: #d3dce6;
  }

  .grid-content {
    border-radius: 10px;
    min-height: 120px;
  }
</style>
