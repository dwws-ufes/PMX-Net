<template>
  <v-container class="fill-height" justify="center" align="center">
    <v-card class="mx-auto" max-width="400">
      <v-card-title>Criar Conta</v-card-title>
      <v-card-text>
        <v-form @submit.prevent="register">
          <v-text-field v-model="nome" label="Nome" required />
          <v-text-field v-model="email" label="Email" required />
          <v-text-field v-model="senha" label="Senha" type="password" required />
          <v-btn color="primary" type="submit" class="mt-3">Registrar</v-btn>
        </v-form>
        <v-btn text class="mt-2" @click="router.push('/')">Já tenho conta</v-btn>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import authService from '../services/authService'

const nome = ref('')
const email = ref('')
const senha = ref('')
const router = useRouter()

async function register() {
  try {
    await authService.register({ nome: nome.value, email: email.value, senha: senha.value })
    alert('Usuário registrado com sucesso!')
    router.push('/')
  } catch (error) {
    alert('Erro ao registrar')
  }
}
</script>