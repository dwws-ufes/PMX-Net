<template>
  <v-dialog v-model="dialogModel" max-width="500">
    <v-card>
      <v-card-title>
        <span class="text-h6">
          {{ projeto.id ? 'Editar Projeto' : 'Novo Projeto' }}
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
import { ref, watch } from 'vue'

const props = defineProps({
  modelValue: Boolean,
  projeto: Object // Recebe o projeto para edição ou criação
})

const emit = defineEmits(['update:modelValue', 'salvo'])

const dialogModel = ref(props.modelValue)
const projeto = ref({ id: null, nome: '', descricao: '' })

// Sincroniza o estado do modal
watch(() => props.modelValue, val => {
  dialogModel.value = val
})
watch(dialogModel, val => {
  emit('update:modelValue', val)
})

// Atualiza o formulário ao receber novo projeto como prop
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
