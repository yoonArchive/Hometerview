// API
import $axios from "axios";
// import * as Mutation from './mutation-types'

export function requestLogin({ state }, payload) {
  console.log("requestLogin", state, payload);
  const url = "/auth/login";
  let body = payload;
  return $axios.post(url, body);
}
