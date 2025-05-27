import api from '@/services/api'

export default {
  listar() {
    return api.get('/tipos')
  },
  salvar(obj) {
    return api.post('/tipos', obj)
  },
  buscarPorId(id) {
    return api.get(`/tipos/{id}`)
  },
  remover(id) {
    return api.delete(`/tipos/{id}`)
  }
}
