<template>
  <v-card class="mt-4">
    <v-card-title>
      <span class="headline">{{ usuario.id ? 'Editar' : 'Novo' }} Usuário</span>
    </v-card-title>
    <v-card-text>
      <v-form @submit.prevent="salvar">
        <v-text-field v-model="usuario.nome" label="Nome" required />
        <v-text-field v-model="usuario.email" label="Email" required />
        <v-text-field v-model="usuario.senha" label="Senha" :type="mostrarSenha ? 'text' : 'password'">
          <template #append-inner>
            <v-icon @click="mostrarSenha = !mostrarSenha">{{ mostrarSenha ? 'mdi-eye-off' : 'mdi-eye' }}</v-icon>
          </template>
        </v-text-field>

        <v-card-actions>
          <v-btn color="primary" type="submit">Salvar</v-btn>
          <v-btn text @click="$emit('cancelar')">Cancelar</v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref, watch } from 'vue'
import usuarioService from '../services/usuarioService'

const props = defineProps({ usuario: Object })
const emit = defineEmits(['salvo', 'cancelar'])

const usuario = ref({ nome: '', email: '', senha: '' })
const mostrarSenha = ref(false)

watch(() => props.usuario, (novo) => {
  usuario.value = novo ? { ...novo } : { nome: '', email: '', senha: '' }
}, { immediate: true })

function salvar() {
  usuarioService.salvar(usuario.value).then(() => emit('salvo'))
}
</script>