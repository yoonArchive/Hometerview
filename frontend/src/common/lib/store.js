import { createStore } from "vuex";
import root from '@/views/main/store';
import accounts from '@/views/main/store/modules/accounts'
import notice from '@/views/main/store/modules/notice'
import createPersistedState from 'vuex-persistedstate';
export default createStore({
  modules: { root, accounts, notice },
  Plugins:[
    createPersistedState()
  ]
});
