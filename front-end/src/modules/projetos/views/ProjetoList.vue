<template>
  <DefaultLayout>
    <v-container>
      <v-row justify="end" class="mb-4">
        <v-btn color="primary" prepend-icon="mdi-plus" @click="novoProjeto">
          Novo Projeto
        </v-btn>
      </v-row>
      <ProjetoForm v-model="formAberto" :projeto="projetoEmEdicao" @salvo="carregarProjetos" />

      <h1 class="text-h5 font-weight-bold mb-4">Lista de Projetos</h1>
      <v-data-table :headers="headers" :items="projetos" class="elevation-1">
        <template #item.remover="{ item }">
          <v-btn size="small" icon color="red" @click="removerProjeto(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>

        <template #item.editar="{ item }">
          <v-btn size="small" icon color="blue" @click="editarProjeto(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </template>

        <template #item.usuario="{ item }">
          {{ item.usuario?.nome || '—' }}
        </template>

        <template #item.descricao="{ item }">
          {{ item.descricao || '—' }}
        </template>

        <template #item.acoes="{ item }">
          <v-btn size="small" color="primary" @click="abrirFerramentas(item)">
            Ferramentas
          </v-btn>
        </template>
      </v-data-table>

      <v-dialog v-model="ferramentasVisivel" max-width="1600">
        <FerramentasDoProjeto v-if="projetoSelecionado" :projeto="projetoSelecionado"
          :ferramentas="projetoSelecionado.ferramentas || []" />
      </v-dialog>
    </v-container>
  </DefaultLayout>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import ProjetoForm from './ProjetoForm.vue'
import FerramentasDoProjeto from '../../ferramentas/FerramentasDoProjeto.vue'
import projetoService from '../services/projetosService'

const projetos = ref([])
const formAberto = ref(false)
const projetoEmEdicao = ref(null)

const ferramentasVisivel = ref(false)
const projetoSelecionado = ref(null)

const headers = [
  { text: '', value: 'remover', sortable: false, width: 60 },
  { text: '', value: 'editar', sortable: false, width: 60 },
  { text: 'ID', value: 'id' },
  { text: 'Nome', value: 'nome' },
  { text: 'Descricao', value: 'descricao' },
  { text: 'Ações', value: 'acoes', sortable: false }
]

function carregarProjetos() {
  projetoService.listar().then(({ data }) => {
    projetos.value = data
  })
}

function novoProjeto() {
  projetoEmEdicao.value = { id: null, nome: '', descricao: '' }
  formAberto.value = true
}

function editarProjeto(projeto) {
  projetoEmEdicao.value = { ...projeto }
  formAberto.value = true
}

function removerProjeto(projeto) {
  if (confirm(`Deseja remover o projeto "${projeto.nome}"?`)) {
    projetoService.remover(projeto.id).then(() => {
      carregarProjetos()
    })
  }
}

function abrirFerramentas(projeto) {
  projetoSelecionado.value = projeto
  ferramentasVisivel.value = true
}

onMounted(carregarProjetos)
</script>
