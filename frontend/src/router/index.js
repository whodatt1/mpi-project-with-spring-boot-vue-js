import { createRouter, createWebHistory } from 'vue-router'
import CommunityVue from '../views/Community.vue'
import LoginVue from '../views/Login.vue'
import Main from '../views/Main.vue'
import MovieVue from '../views/Movie.vue'
import SignUpVue from '../views/SignUp.vue'
import MovieDetailVue from '../views/MovieDetail.vue'
import MovieSearchVue from '../views/MovieSearch.vue'
import CommunityCreateVue from '../views/CommunityCreate.vue'
import CommunityDetailVue from '../views/CommunityDetail.vue'
import CommunityUpdateVue from '../views/CommunityUpdate.vue'
import BookmarkVue from '../views/Bookmark.vue'
import MyPageVue from '@/views/MyPage.vue'
import UpdateUserVue from '@/views/UpdateUser.vue'
import DeleteUserVue from '@/views/DeleteUser.vue'
import AdminPageVue from '@/views/AdminPage.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: Main,
    meta: { requiredAuth: false }
  },
  {
    path: '/loginvue',
    name: 'loginvue',
    component: LoginVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/signupvue',
    name: 'signupvue',
    component: SignUpVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/movievue',
    name: 'movievue',
    component: MovieVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/moviesearchvue',
    name: 'moviesearchvue',
    component: MovieSearchVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/moviedetailvue/:id',
    name: 'moviedetailvue',
    component: MovieDetailVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/communityupdatevue',
    name: 'communityupdate',
    component: CommunityUpdateVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/bookmarkvue',
    name: 'bookmark',
    component: BookmarkVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/communityvue',
    name: 'communityvue',
    component: CommunityVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/communitycreatevue',
    name: 'communitycreatevue',
    component: CommunityCreateVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/communitydetailvue',
    name: 'communitydetailvue',
    component: CommunityDetailVue,
    meta: { requiredAuth: false }
  },
  {
    path: '/mypagevue',
    name: 'mypagevue',
    component: MyPageVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/updateuservue',
    name: 'updateuservue',
    component: UpdateUserVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/deleteuservue',
    name: 'deleteuservue',
    component: DeleteUserVue,
    meta: { requiredAuth: true }
  },
  {
    path: '/adminpagevue',
    name: 'adminpagevue',
    component: AdminPageVue,
    meta: { requiredAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('user')

  if (to.meta.requiredAuth) {
    if (!loggedIn) {
      alert('권한이 필요합니다. 로그인 해주세요')
      return next({ path: '/loginvue' })
    } else {
      return next()
    }
  }
  return next()
})

export default router
