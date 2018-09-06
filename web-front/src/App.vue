<template>
  <div>
    <el-container style="height: 710px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="userItemClick">用户</el-menu-item>
              <el-menu-item index="1-2" @click="articleItemClick">文章</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-button type="primary" @click="mainPage">主页</el-button>
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
              <el-dropdown-item>设置</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>DB.Cooper</span>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import {get_list_article_page_path,get_list_user_page_path,get_main_page_path} from './router/path'
  export default {
    methods: {
      mainPage:function () {
        this.$router.push({path: get_main_page_path()})
      },
      userItemClick: function () {
        this.$router.push({path: get_list_user_page_path()})
      },
      articleItemClick: function () {
        this.$router.push({path: get_list_article_page_path()})
      }
    }
  };
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
