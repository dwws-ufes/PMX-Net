<template>
  <v-dialog v-model="dialogModel" max-width="500">
    <v-card>
      <v-card-title>
        <span class="text-h6">
          {{ projeto.id ? 'Editar' : 'Novo' }}
          <span
            v-if="descricaoFoaf"
            :title="descricaoFoaf"
            style="text-decoration: underline dotted; cursor: help;"
          >
            Projeto
          </span>
          <span v-else>Projeto</span>
        </span>
      </v-card-title>

      <v-card-text>
        <v-form @submit.prevent="salvar">
          <v-text-field
            v-model="projeto.nome"
            label="Nome"
            required
          />
          <v-textarea
            v-model="projeto.descricao"
            label="Descrição"
            auto-grow
            rows="2"
          />
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer />
        <v-btn text @click="fechar">Cancelar</v-btn>
        <v-btn color="primary" @click="salvar">Salvar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { QueryEngine  } from '@comunica/query-sparql'

const props = defineProps({
  modelValue: Boolean,
  projeto: Object
})

const emit = defineEmits(['update:modelValue', 'salvo'])

const dialogModel = ref(props.modelValue)
const projeto = ref({ id: null, nome: '', descricao: '' })
const descricaoFoaf = ref('')

// 🔍 Consulta SPARQL usando @comunica/query-sparql
async function buscarDescricaoFOAFProject() {
  try {
    const engine = new QueryEngine()
    const query = `
      PREFIX dbr: <http://dbpedia.org/resource/>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      SELECT ?comment WHERE {
        dbr:Project rdfs:comment ?comment .
        FILTER (lang(?comment) = "pt")
      }
    `
    const result = await engine.queryBindings(query, {
      sources: ['https://dbpedia.org/sparql']
    })
    const bindings = await result.toArray()
    descricaoFoaf.value = bindings[0]?.get('comment')?.value || ''
  } catch (err) {
    console.error('Erro ao consultar DBpedia:', err)
  }
}

onMounted(buscarDescricaoFOAFProject)

// Sincronização com modelValue
watch(() => props.modelValue, val => {
  dialogModel.value = val
})
watch(dialogModel, val => {
  emit('update:modelValue', val)
})
watch(() => props.projeto, (novoProjeto) => {
  if (novoProjeto) {
    projeto.value = { ...novoProjeto }
  } else {
    projeto.value = { id: null, nome: '', descricao: '' }
  }
}, { immediate: true })

function salvar() {
  if (!projeto.value.nome) return

  import('@/services/api').then(({ default: api }) => {
    api.post('/projetos', projeto.value).then(() => {
      emit('salvo')
      dialogModel.value = false
      projeto.value = { id: null, nome: '', descricao: '' }
    })
  })
}

function fechar() {
  dialogModel.value = false
}
</script>
