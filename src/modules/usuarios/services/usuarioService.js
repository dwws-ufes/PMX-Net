import api from '@/services/api'

export default {
  listar() {
    return api.get('/usuarios')
  },
  salvar(usuario) {
    return api.post('/usuarios', usuario)
  },
  buscarPorId(id) {
    return api.get(`/usuarios/${id}`)
  },
  remover(id) {
    return api.delete(`/usuarios/${id}`)
  }
}