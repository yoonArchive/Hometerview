import { ref, onMounted } from "vue";
import { mapActions } from "vuex";
// import { getReviewInfo, getReviewDetail,createReview,updateReview,deleteReview} from '../main/store/modules/review'
import review from "../main/store/modules/review";

// action.mutations
export default function useEvent() {
  mapActions([
    "getReviewInfo",
    "getReviewDetail",
    "createReview",
    "updateReview",
    "deleteReview"
  ]);
  const getEvents = ref([]);

  const setEvents = async () => {
    getEvents.value = await fetchEvents();
  };

  const createEvent = event => {
    review.actions.createReview(event);
    console.log("김김");
    console.log($store.state.token);
  };

  const updateEvent = (event, id) => {
    review.actions.updateReview(event, id);
  };

  const deleteEvent = id => {
    review.deleteReview(id);
  };

  onMounted(setEvents);

  return {
    getEvents,
    setEvents,
    createEvent,
    updateEvent,
    deleteEvent
  };
}
