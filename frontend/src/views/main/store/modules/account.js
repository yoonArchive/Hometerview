export default {
  state: ()=>({
    profile: "",
    loginUser: null,
    isLogin: false,
    isLoginError: false,
    pwallowedchar: ["!", "@", "#", "$", "%", "^", "&", "*"],
  }),
  mutations:{
    LOGIN_USER(state,user){
      state.loginUser = user;
    }
  },
  getters:{
    getloginuser(state){
      return state.loginuser;
    }
  },
  actions:{
    loginUser({commit},user){
      console.log("로그인 옴?");
    }
  }
}
