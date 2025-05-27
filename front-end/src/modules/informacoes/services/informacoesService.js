import api from '@/services/api'

export default {
  listar() {
    return api.get('/informacaos')
  },
  salvar(obj) {
    return api.post('/informacaos', obj)
  },
  buscarPorId(id) {
    return api.get(`/informacaos/{id}`)
  },
  remover(id) {
    return api.delete(`/informacaos/{id}`)
  }
}
