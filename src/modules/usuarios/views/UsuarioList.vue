<template>
  <DefaultLayout>
    <v-container>
      <v-btn color="primary" @click="abrirForm">Novo Usuário</v-btn>
      <v-data-table :headers="headers" :items="usuarios" class="mt-4">
        <template #item.actions="{ item }">
          <v-btn icon @click="editarUsuario(item)"><v-icon>mdi-pencil</v-icon></v-btn>
          <v-btn icon @click="removerUsuario(item.id)"><v-icon>mdi-delete</v-icon></v-btn>
        </template>
      </v-data-table>

      <UsuarioForm v-if="formAberto" :usuario="usuarioSelecionado" @salvo="carregarUsuarios" @cancelar="fecharForm" />
    </v-container>
  </DefaultLayout>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import UsuarioForm from './UsuarioForm.vue'
import usuarioService from '../services/usuarioService'

const usuarios = ref([])
const formAberto = ref(false)
const usuarioSelecionado = ref(null)

const headers = [
  { text: 'ID', value: 'id' },
  { text: 'Nome', value: 'nome' },
  { text: 'Email', value: 'email' },
  { text: 'Ações', value: 'actions', sortable: false }
]

function carregarUsuarios() {
  usuarioService.listar().then(({ data }) => usuarios.value = data)
  fecharForm()
}

function abrirForm() {
  usuarioSelecionado.value = null
  formAberto.value = true
}

function editarUsuario(usuario) {
  usuarioSelecionado.value = { ...usuario }
  formAberto.value = true
}

function removerUsuario(id) {
  if (confirm('Tem certeza que deseja remover?')) {
    usuarioService.remover(id).then(carregarUsuarios)
  }
}

function fecharForm() {
  formAberto.value = false
  usuarioSelecionado.value = null
}

onMounted(carregarUsuarios)
</script>