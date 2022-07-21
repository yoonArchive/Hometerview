import { createStore } from "vuex";
import root from '@/views/main/store';
import accounts from '@/views/main/store/modules/accounts'
export default createStore({
  modules: { root, accounts }
});
