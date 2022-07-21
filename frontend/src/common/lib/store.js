import { createStore } from "vuex";
import root from '@/views/main/store';
import account from '@/views/main/store/modules/account'
export default createStore({
  modules: { root, account }
});
