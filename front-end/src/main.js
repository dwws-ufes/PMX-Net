import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createVuetify } from 'vuetify'
import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'


// Seu CSS global
import './assets/main.css'

const vuetify = createVuetify()

createApp(App)
  .use(router)
  .use(vuetify)
  .mount('#app')
