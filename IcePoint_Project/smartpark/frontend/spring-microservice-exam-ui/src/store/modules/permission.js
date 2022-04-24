import { constantRouterMap } from '@/router'
import { setStore, getStore } from '@/utils/store'
import { initMenu } from '@/utils/util'
import { GetMenu } from '@/api/admin/menu'
// import { getAuthUrl } from '@/api/admin/auth'
import router from '../../router'

const permission = {
  state: {
    routers: getStore({
      name: 'routers'
    }) || {},
    addRouters: [],
    authUrl: ''
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
      setStore({
        name: 'routers',
        content: state.routers
      })
      router.addRoutes(state.addRouters) // 动态添加可访问路由表
    },
    SET_AUTH_URL: (state,url) => {
      state.authUrl = url
    }
  },
  actions: {
    GenerateRoutes ({ commit }, data) {
      let accessedRouters
      GetMenu().then(data => {
        accessedRouters = initMenu(data.data)
        commit('SET_ROUTERS', accessedRouters)
      })
    },
    GET_AUTH_URL ({ commit }){
      return new Promise((resolve,reject) => {
        getAuthUrl().then(res => {
          if(res.data.code === 200) {
            commit('SET_AUTH_URL', res.data.data)
            resolve(res.data.data)
          }else reject(res.data.msg)
        }).catch(e => reject(e))
      })
    }
  }
}

export default permission
