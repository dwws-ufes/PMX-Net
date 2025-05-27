import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import LoginView from '@/modules/auth/views/LoginView.vue'
import ProjetoList from '@/modules/projetos/views/ProjetoList.vue'
import RegisterView from '@/modules/auth/views/RegisterView.vue'
import UsuarioList from '@/modules/usuarios/views/UsuarioList.vue'
import InformacoesProjeto from '@/modules/informacoes/InformacoesProjeto.vue'

const routes = [
  { path: '/projetos', name: 'Projetos', component: ProjetoList, meta: { requiresAuth: true } },
  { path: '/', name: 'Home', component: HomeView },
  { path: '/login', name: 'Login', component: LoginView },
  { path: '/register', name: 'Register', component: RegisterView },
  { path: '/usuarios', name: 'Usuarios', component: UsuarioList, meta: { requiresAuth: true } },
  { path: '/projetos', name: 'Projetos', component: ProjetoList },  // Placeholder
  { path: '/informacoes', name: 'Informacoes', component: InformacoesProjeto } // Placeholder
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    next({ name: 'Login' })
  } else {
    next()
  }
})

export default router