import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vuetify from 'vite-plugin-vuetify'
import path from 'path'

export default defineConfig({
  plugins: [
    vue(),
    vuetify({ autoImport: true }) // 👈 necessário para reconhecer <v-app>, etc.
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  }
})
