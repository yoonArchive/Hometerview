import { createStore } from "vuex";
import root from '@/views/main/store';
import accounts from '@/views/main/store/modules/accounts'
import notice from '@/views/main/store/modules/notice'
import createPersistedState from 'vuex-persistedstate';
import membersrecruitment from '@/views/main/store/modules/membersrecruitment'
export default createStore({
  modules: { root, accounts, notice, membersrecruitment },
  Plugins:[
    createPersistedState()
  ]
});
