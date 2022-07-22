<template>
  <div class="wrap">
    <div class="article">
      <div class="article-top">
        {{article.title}}
      </div>
      <div class="article-content">
        {{article.content}}
      </div>
      <div class="article-bottom">
        <div class="component1" v-if="isAuthor">
          <span class="edit">
            <router-link :to="{ name: 'articleEdit', params: { articlePk } }">
              <button>Edit</button>
            </router-link>
          </span>
          <span class="delete">
            <button @click="deleteArticle(articlePk)">Delete</button>
          </span>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  import CommentList from '@/components/CommentList.vue'


import FavoriteIcon from "./FavoriteIcon";
  export default {
    name: 'ArticleDetail',
    components: { CommentList, FavoriteIcon },
    data() {
      return {
        articlePk: this.$route.params.articlePk,
        animating: false
      }
    },
    computed: {
      ...mapGetters(['isAuthor', 'article']),
      likeCount() {
        return this.article.like_users?.length
      },
      iconClasses() {
      return {
        "toggle-favorite__icon--animate": this.animating
      };
    }


    },
    methods: {
      ...mapActions([
        'fetchArticle',
        'likeArticle',
        'deleteArticle',
      ]),

    onIconAnimationEnds() {
      this.animating = false;
    }

    },
    created() {
      this.fetchArticle(this.articlePk)
    },
    mounted(){
      console.log(this.article.comments)
      console.log(this.$route.params)

    }
  }
</script>

<style lang="scss" scoped >
@import url("../assets/fonts/font.css");
.wrap{  width: 70vw; height: 50vh; margin: auto; padding-top : 30px;}
.article{ display: flex; border: 1px solid; flex-direction: column; width: auto; height:100%; font-family : '엘리스디지털regular';}
.article-top{ border : 1px solid; background-color: white; padding: 10px; font-weight: bold;}
.article-content{padding: 20px; color:white;}
.article-bottom{ margin-top : auto; margin-left: auto; display: flex;}
.components{}
.article-comment{ margin: 20px; }

$particles-animation-duration: 0.8s;
$icon-animation-duration: 0.42s;
$icon-color: hsl(1, 89%, 61%);
$icon-border-color: grey;
// $icon-border-color: hsl(0, 0%, 10%);

@keyframes favorite-icon-animation {
  0% {
    opacity: 1;
    transform: scale(0.1);
  }

  50% {
    opacity: 1;
    transform: scale(1.1);
  }

  80% {
    opacity: 1;
    transform: scale(0.9);
  }

  100% {
    opacity: 1;
    transform: scale(1);
  }
}

@keyframes favorite-particles-animation {
  0% {
    background-position: left center;
  }
  100% {
    background-position: right center;
  }
}

// Particles animation.
.favorite-particles-transition-enter-active {
  background-image: url("@/../public/particles-sprite.png");
  background-size: 2500% auto;
  background-position: left center;
  background-repeat: no-repeat;

  animation-duration: $particles-animation-duration;
  animation-timing-function: steps(24);
  animation-name: favorite-particles-animation;
}

.toggle-favorite {
  font-size: 20px;
  position: relative;
  background-color: transparent;
  &__icon {
    height: 1.5em;
    width: 1.5em;

    // Transition mainly for when un-favoriting
    transition: fill-opacity 0.2s, stroke 0.2s;
    fill: $icon-color;
    fill-opacity: 0;
    stroke: $icon-border-color;



    // Icon animation
    &--animate {
      opacity: 0;
      transform: scale(0);
      animation-duration: $icon-animation-duration;
      animation-delay: $particles-animation-duration - $icon-animation-duration;
      animation-name: favorite-icon-animation;
    }
  }

  &__particles {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 3em;
    height: 3em;
  }
}
</style>
