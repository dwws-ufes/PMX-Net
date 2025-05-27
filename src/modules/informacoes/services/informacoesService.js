import api from '@/services/api'

export default {
  listar() {
    return api.get('/informacoes')
  },
  salvar(obj) {
    return api.post('/informacoes', obj)
  },
  buscarPorId(id) {
    return api.get(`/informacoes/{id}`)
  },
  remover(id) {
    return api.delete(`/informacoes/{id}`)
  }
}
