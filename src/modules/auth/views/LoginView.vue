<template>
  <v-container class="fill-height" justify="center" align="center">
    <v-card class="mx-auto" max-width="400">
      <v-card-title>Login</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="login">
          <v-text-field v-model="email" label="Email" required />
          <v-text-field v-model="senha" label="Senha" type="password" required />
          <v-btn color="primary" type="submit" class="mt-3">Entrar</v-btn>
        </v-form>
        <v-btn text class="mt-2" @click="router.push('/register')">Criar conta</v-btn>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import authService from '../services/authService'

const email = ref('')
const senha = ref('')
const router = useRouter()

async function login() {
  try {
    const { data } = await authService.login({ email: email.value, senha: senha.value })
    localStorage.setItem('token', data.token)
    router.push('/usuarios')
  } catch (error) {
    alert('Erro ao fazer login')
  }
}
</script>