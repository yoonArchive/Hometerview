import { createStore } from 'vuex'
import root from '@/views/main/store'
import accounts from '@/views/main/store/modules/accounts'
import notice from '@/views/main/store/modules/notice'
import membersrecruitment from '@/views/main/store/modules/membersrecruitment'
import resume from '@/views/main/store/modules/resume'
import review from '@/views/main/store/modules/review'
import commonquestion from '@/views/main/store/modules/commonquestion'
import studyspace from '@/views/main/store/modules/studyspace'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  modules: {
    root,
    accounts,
    notice,
    resume,
    review,
    membersrecruitment,
    studyspace,
    commonquestion
  },
  plugins:[
    createPersistedState()
  ]
});
