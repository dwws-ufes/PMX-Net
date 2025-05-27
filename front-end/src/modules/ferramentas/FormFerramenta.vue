<template>
  <v-card>
    <v-card-title>
      Ferramentas do Projeto #{{ projeto.nome }}
    </v-card-title>

    <v-card-text>
      <v-btn class="mb-4" color="primary" @click="formVisivel = true">
        Nova Ferramenta
      </v-btn>

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

      <v-dialog v-model="formVisivel" max-width="600">
        <FormFerramenta
          :projeto-id="projeto.id"
          @salvo="onFerramentaSalva"
          @fechar="formVisivel = false"
        />
      </v-dialog>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import ferramentaService from './ferramentaService'
import FormFerramenta from './FormFerramenta.vue'

const props = defineProps({
  projeto: { type: Object, required: true }
})

const ferramentas = ref([])
const formVisivel = ref(false)

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
  ferramentaService.listar(props.projeto.id).then(({ data }) => {
    ferramentas.value = data
  })
}

function onFerramentaSalva() {
  formVisivel.value = false
  carregarFerramentas()
}

watch(() =>  carregarFerramentas)
onMounted(carregarFerramentas)
</script>
