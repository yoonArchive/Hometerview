import { createStore } from 'vuex'
import root from '@/views/main/store'
import accounts from '@/views/main/store/modules/accounts'
import notice from '@/views/main/store/modules/notice'
import createPersistedState from 'vuex-persistedstate'
import membersrecruitment from '@/views/main/store/modules/membersrecruitment'
import resume from '@/views/main/store/modules/resume'
import review from '@/views/main/store/modules/review'
import studyspace from '@/views/main/store/modules/studyspace'


export default createStore({
  modules: {
    root,
    accounts, 
    notice, 
    resume, 
    review, 
    membersrecruitment,
    studyspace
  },
  Plugins:[
    createPersistedState()
  ]
});
