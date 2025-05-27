import api from '@/services/api'

export default {
  listar() {
    return api.get('/projetos')
  },
  salvar(obj) {
    return api.post('/projetos', obj)
  },
  buscarPorId(id) {
    return api.get(`/projetos/{id}`)
  },
  remover(id) {
    return api.delete(`/projetos/{id}`)
  }
}
