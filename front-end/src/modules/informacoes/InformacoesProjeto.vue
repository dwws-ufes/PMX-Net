
<template>
  <DefaultLayout>
    <v-container>
      <v-row justify="start" class="mb-4">
        <v-select
          v-model="projetoSelecionado"
          :items="projetos"
          item-title="nome"
          item-value="id"
          label="Selecione um Projeto"
          dense
          clearable
          style="max-width: 300px"
        />

        <v-btn color="primary" class="ml-4" prepend-icon="mdi-magnify" @click="filtrarProjeto">
          Visualizar
        </v-btn>
      </v-row>

      <h1 class="text-h5 font-weight-bold mb-4">Visualização de Rede</h1>
      <div id="cy" style="height: 500px; border: 1px solid #ccc; margin-bottom: 30px" />

      <h2 class="text-h6 font-weight-bold mt-8 mb-2">Participações por Pessoa</h2>
      <canvas id="barChart" style="max-height: 300px; margin-bottom: 30px" />

      <h2 class="text-h6 font-weight-bold mt-8 mb-2">Distribuição de Tamanhos de Cliques</h2>
      <v-simple-table dense>
        <thead>
          <tr>
            <th class="text-left">Tamanho do Clique</th>
            <th class="text-left">Quantidade</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(qtd, tamanho) in distribuicaoCliques" :key="tamanho">
            <td>{{ tamanho }} membros</td>
            <td>{{ qtd }}</td>
          </tr>
        </tbody>
      </v-simple-table>

      <h2 class="text-h6 font-weight-bold mt-8 mb-2">Cliques por Tamanho (Membros)</h2>
      <div v-for="(grupo, index) in gruposEquipe" :key="index" class="mb-2">
        <strong>Clique {{ index + 1 }} ({{ grupo.length }} membros):</strong>
        <span>{{ grupo.join(', ') }}</span>
      </div>


      <h2 class="text-h6 font-weight-bold mt-8 mb-2">Métricas de Centralidade</h2>
      <ul>
        <li v-for="(valor, pessoa) in centralidade" :key="pessoa">
          {{ pessoa }} - Centralidade: {{ valor.toFixed(2) }}
        </li>
      </ul>

      <v-alert type="warning" v-if="pontosCriticos.length" class="mt-6">
        <strong>Pontos críticos de comunicação identificados:</strong>
        <ul>
          <li v-for="p in pontosCriticos" :key="p">{{ p }}</li>
        </ul>
      </v-alert>

      <h2 class="text-h6 font-weight-bold mt-8 mb-2">Pontes de Informação (Bridges)</h2>
      <ul>
        <li v-for="p in pontes" :key="p">{{ p }}</li>
      </ul>

      <v-dialog v-model="ferramentasVisivel" max-width="1600">
        <FerramentasDoProjeto
          v-if="projetoSelecionadoObj"
          :projeto="projetoSelecionadoObj"
          :ferramentas="projetoSelecionadoObj.ferramentas || []"
        />
      </v-dialog>
    </v-container>
  </DefaultLayout>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import cytoscape from 'cytoscape'
import Chart from 'chart.js/auto'
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import informacaoService from './services/informacoesService.js'
import projetoService from '../projetos/services/projetosService.js'

const projetos = ref([])
const projetoSelecionado = ref(null)
const projetoSelecionadoObj = ref(null)
const ferramentasVisivel = ref(false)
const centralidade = ref({})
const pontosCriticos = ref([])
const pontes = ref([])
const gruposEquipe = ref([])
const distribuicaoCliques = ref({})

function carregarProjetos() {
  projetoService.listar().then(({ data }) => {
    projetos.value = data
  }).catch(err => console.error('Erro ao carregar projetos:', err))
}

function filtrarProjeto() {
  const projeto = projetos.value.find(p => p.id === projetoSelecionado.value)
  if (!projeto) return

  projetoSelecionadoObj.value = projeto

  informacaoService.listar(projetoSelecionado.value).then(({ data }) => {
    calcularCentralidade(data)
    identificarPontes(data)
    identificarCliques(data)
    renderClusterGraph(data)
    renderBarChart(data)
  })
}

function abrirFerramentas(projeto) {
  projetoSelecionadoObj.value = projeto
  ferramentasVisivel.value = true
}

function renderClusterGraph(informacoes) {
  const cy = cytoscape({
    container: document.getElementById('cy'),
    elements: [],
    style: [
      { selector: 'node', style: { label: 'data(label)', color: '#000' } },
      { selector: 'edge', style: { width: 2, 'line-color': '#ccc' } }
    ],
    layout: { name: 'cose' }
  })

  const edges = new Set()
  const pessoasSet = new Set()

  informacoes.forEach(info => {
    const conteudo = JSON.parse(info.conteudo)
    const pessoas = conteudo.pessoas || []

    pessoas.forEach(p1 => {
      pessoasSet.add(p1)
      pessoas.forEach(p2 => {
        if (p1 !== p2) {
          const key = [p1, p2].sort().join('-')
          edges.add(key)
        }
      })
    })
  })

  Array.from(pessoasSet).forEach(pessoa => {
    let color = '#1976D2'
    if (pontosCriticos.value.includes(pessoa)) {
      color = '#E53935'
    } else if (pontes.value.includes(pessoa)) {
      color = '#FB8C00'
    } else if (gruposEquipe.value.some(grupo => grupo.includes(pessoa))) {
      color = '#2E7D32'
    }
    cy.add({ data: { id: pessoa, label: pessoa }, style: { 'background-color': color } })
  })

  edges.forEach(edge => {
    const [source, target] = edge.split('-')
    cy.add({ data: { source, target } })
  })

  cy.layout({ name: 'cose' }).run()
}

function renderBarChart(informacoes) {
  const contagem = {}

  informacoes.forEach(info => {
    const pessoas = JSON.parse(info.conteudo).pessoas || []
    pessoas.forEach(p => {
      contagem[p] = (contagem[p] || 0) + 1
    })
  })

  const ctx = document.getElementById('barChart')
  if (ctx.chart) ctx.chart.destroy()
  ctx.chart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: Object.keys(contagem),
      datasets: [{
        label: 'Número de Participações',
        data: Object.values(contagem),
        backgroundColor: '#42A5F5'
      }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } }
    }
  })
}

function identificarCliques(informacoes) {
  const grafo = {}

  // Construir grafo simétrico
  informacoes.forEach(info => {
    const pessoas = JSON.parse(info.conteudo).pessoas || []
    pessoas.forEach((p1, i) => {
      for (let j = i + 1; j < pessoas.length; j++) {
        const p2 = pessoas[j]
        grafo[p1] = grafo[p1] || new Set()
        grafo[p2] = grafo[p2] || new Set()
        grafo[p1].add(p2)
        grafo[p2].add(p1)
      }
    })
  })

  const vertices = Object.keys(grafo)
  const cliques = []
  const tamanhoMax = 6  // Pode ser ajustado para aumentar/diminuir

  for (let k = 3; k <= tamanhoMax; k++) {
    const combinacoes = getCombinations(vertices, k)
    combinacoes.forEach(grupo => {
      const isClique = grupo.every((p1, i) =>
        grupo.every((p2, j) => i === j || grafo[p1]?.has(p2))
      )
      if (isClique) {
        cliques.push(grupo)
      }
    })
  }

  gruposEquipe.value = cliques

  const distribuicao = {}
  gruposEquipe.value.forEach(grupo => {
    const tamanho = grupo.length
    distribuicao[tamanho] = (distribuicao[tamanho] || 0) + 1
  })
  distribuicaoCliques.value = distribuicao
}

// Gera todas as combinações possíveis de k elementos
function getCombinations(arr, k) {
  const result = []
  const comb = (prefix, rest, k) => {
    if (k === 0) {
      result.push(prefix)
      return
    }
    for (let i = 0; i < rest.length; i++) {
      comb([...prefix, rest[i]], rest.slice(i + 1), k - 1)
    }
  }
  comb([], arr, k)
  return result
}

function calcularCentralidade(informacoes) {
  const conexoes = {}

  informacoes.forEach(info => {
    const pessoas = JSON.parse(info.conteudo).pessoas || []
    pessoas.forEach(p1 => {
      conexoes[p1] = conexoes[p1] || new Set()
      pessoas.forEach(p2 => {
        if (p1 !== p2) conexoes[p1].add(p2)
      })
    })
  })

  const centralidadeMap = {}
  Object.entries(conexoes).forEach(([pessoa, conexoesSet]) => {
    centralidadeMap[pessoa] = conexoesSet.size
  })

  centralidade.value = centralidadeMap

  pontosCriticos.value = Object.entries(centralidadeMap)
    .filter(([_, valor]) => valor <= 1)
    .map(([pessoa]) => pessoa)
}

function identificarPontes(informacoes) {
  const mapa = {}

  informacoes.forEach(info => {
    const pessoas = JSON.parse(info.conteudo).pessoas || []
    pessoas.forEach(p => {
      mapa[p] = mapa[p] || new Set()
      pessoas.forEach(outro => {
        if (p !== outro) mapa[p].add(outro)
      })
    })
  })

  pontes.value = Object.keys(mapa).filter(p => {
    const ligacoes = Array.from(mapa[p])
    return ligacoes.length === 1
  })
}

onMounted(() => {
  carregarProjetos()
})
</script>

<style>
#cy {
  background-color: #fafafa;
}
</style>
