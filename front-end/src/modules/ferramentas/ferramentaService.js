import api from '@/services/api'

export default {
  listar() {
    return api.get('/ferramentas')
  },
  salvar(obj) {
    return api.post('/ferramentas', obj)
  },
  buscarPorId(id) {
    return api.get(`/ferramentas/{id}`)
  },
  remover(id) {
    return api.delete(`/ferramentas/{id}`)
  }
}
