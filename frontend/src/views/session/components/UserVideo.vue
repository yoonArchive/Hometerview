<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager"/>
	<div><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			// 이름 띄우기
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			// console.log('확인')
			// console.log(connection.data)
			return JSON.parse(connection.data);
		},
	},
};
</script>
