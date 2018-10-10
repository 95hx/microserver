import Vue from 'vue'
import Router from 'vue-router'
import UserList from '@/components/UserListPage'
import ArticleList from '@/components/ArticleListPage'
import ArticleDetail from '@/components/ArticleDetailPage'
import Video from '@/components/VideoPage.vue'
import Main from '@/components/MainPage'
import {get_list_user_page_path, get_list_article_page_path, get_article_detail_page_path,get_main_page_path,get_video_path} from './path'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: get_list_user_page_path(),
      name: '/user_list',
      component: UserList
    },
    {
      path: get_list_article_page_path(),
      name: '/article_list',
      component: ArticleList
    },
    {
      path: get_article_detail_page_path(),
      name: '/article_detail',
      component: ArticleDetail
    },
    {
      path: get_main_page_path(),
      name: '/main',
      component: Main
    },
    {
      path: get_video_path(),
      name: '/video',
      component: Video
    }
  ]
})
