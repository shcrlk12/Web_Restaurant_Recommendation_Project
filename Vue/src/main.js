import { createApp } from 'vue'
import App from './App.vue'
// import BootstrapVue3 from 'bootstrap-vue-3'
import Vuex from 'vuex'
import router from './router/router'
// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

const store = new Vuex.Store({
    state: {
      count: 0
    },
    mutations: {
      increment (state) {
        state.count++
      }
    }
  })

store.commit('increment')

const app = createApp(App)
// app.use(BootstrapVue3)
app.use(Vuex)
app.use(router)
app.mount('#app')