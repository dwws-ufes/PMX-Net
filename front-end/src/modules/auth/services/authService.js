import api from '@/services/api'

export default {
  login(payload) {
    console.log(payload)
    return api.post('/auth/login', payload)
  },
  register(payload) {
    return api.post('/auth/register', payload)
  },
  logout() {
    localStorage.removeItem('token')
  }
}