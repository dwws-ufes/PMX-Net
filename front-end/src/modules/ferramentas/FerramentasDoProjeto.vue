<template>
  <v-card>
    <v-card-title>
      Ferramentas do Projeto #{{ projeto.id }} - {{ projeto.nome }}
    </v-card-title>

    <v-card-text>
      <v-container fluid>
        <v-row dense>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.nome" label="Nome da Ferramenta" dense hide-details />
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.link" label="Link" dense hide-details />
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.nome_usuario" label="Nome de Usuário" dense hide-details />
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.token" label="Token de Acesso" type="password" dense hide-details />
          </v-col>
          <v-col cols="12" md="6">
            <v-select
              v-model="form.tipo"
              :items="tiposDisponiveis"
              label="Tipo"
              dense
              hide-details
            />
          </v-col>
        </v-row>

        <v-btn class="mt-4 mb-4" color="primary" @click="adicionarFerramenta">
          Adicionar Ferramenta
        </v-btn>
      </v-container>

<v-data-table
  :items="ferramentas"
  :headers="headers"
  class="elevation-1"
  dense
  no-data-text="Nenhuma ferramenta cadastrada."
>
  <template #item.tipo="{ item }">
    {{ item.tipo }}
  </template>

  <template #item.link="{ item }">
    <a :href="item.link" target="_blank" rel="noopener">{{ item.link }}</a>
  </template>

  <template #item.token="{ item }">
    <span>••••••••</span>
  </template>
</v-data-table>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import ferramentaService from './ferramentaService'

const props = defineProps({
  projeto: { type: Object, required: true }
})

const ferramentas = ref([])

const form = ref({
  nome: '',
  link: '',
  nome_usuario: '',
  token: '',
  tipo: ''
})

const tiposDisponiveis = ['GitHub', 'Jira', 'Outros']

const headers = [
  { text: 'ID', value: 'id' },
  { text: 'Nome da Ferramenta', value: 'nome' },
  { text: 'Tipo', value: 'tipo' },
  { text: 'Link', value: 'link' },
  { text: 'Nome do Usuário', value: 'nome_usuario' },
  { text: 'Token', value: 'token' }
]

function carregarFerramentas() {
  if (!props.projeto?.id) return
  ferramentaService.listar(props.projeto.id)
    .then(({ data }) => {
      ferramentas.value = data
    })
}

function limparFormulario() {
  form.value = {
    nome: '',
    link: '',
    nome_usuario: '',
    token: '',
    tipo: ''
  }
}

function adicionarFerramenta() {
  if (!form.value.nome || !form.value.link || !form.value.nome_usuario || !form.value.token || !form.value.tipo) {
    return
  }

  ferramentaService.salvar({
    ...form.value,
    projetoId: props.projeto.id
  }).then(() => {
    limparFormulario()
    carregarFerramentas()
  })
}

watch(() => props.projeto.id, carregarFerramentas)
onMounted(carregarFerramentas)
</script>
