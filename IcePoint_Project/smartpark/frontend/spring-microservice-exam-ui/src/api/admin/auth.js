import request from '@/router/axios'

const baseUserUrl = '/api/auth/v1/authentication/ty'

export function getAuthUrl () {
  return request({
    url: baseUserUrl + '/auth_url',
    method: 'get'
  })
}
