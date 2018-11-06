import Vue from 'vue'
import App from './views/App.vue'
import router from './router.js'

import '@/assets/css/fontawesome-all.min.css'
import '@/assets/css/modifiers.css'
import '@/assets/css/grids.css'
import '@/assets/css/containers.css'
import '@/assets/css/table.css'
import '@/assets/css/forms.css'
import '@/assets/css/buttons.css'
import '@/assets/css/index.css'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
