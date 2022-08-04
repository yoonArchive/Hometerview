<template>
<!-- 가능한 빨리 재생을 시작하는 video속성 -->
	<video autoplay/>
</template>

<script>
export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	mounted () {
		// 비디오 html에 화면 띄우기
		this.streamManager.addVideoElement(this.$el);
		console.log(this.$el) // <video autoplay="" id="local-video-undefined"></video> 가 뜨게 된다.
	},
};
</script>
