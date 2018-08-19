import Vue from 'vue'
import Router from 'vue-router'
import UserList from '@/components/UserListPage'
import ArticleList from '@/components/ArticleListPage'
import {get_list_user_page_path,get_list_article_page_path} from './path'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: get_list_user_page_path(),
      name: 'user',
      component: UserList
    },
    {
      path: get_list_article_page_path(),
      name: 'article',
      component: ArticleList
    }
  ]
})
