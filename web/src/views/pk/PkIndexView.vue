<template>
  <PlayGround v-if="$store.state.pk.status === 'playing'" />
  <MatchGround v-if="$store.state.pk.status === 'matching'" />


</template>


<script>
import PlayGround from "@/components/PlayGround";
import MatchGround from "@/components/MatchGround";
import {onMounted, onUnmounted} from "vue";
import { useStore } from "vuex";

export default {
  components: {
    PlayGround,
    MatchGround
  },
  setup() {
    const store = useStore();
    store.commit("updateOpponent", {
      opponent_username: "我的对手",
      opponent_photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png"
    })
    const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;
    let socket = null;

    onMounted(() => {
      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        console.log("connected");
        store.commit("updateSocket", socket);
      }

      socket.onmessage = message => {
        const data = JSON.parse(message.data);
        if (data.event === "start-matching") {
          console.log(data.opponent_photo);
          console.log(data.opponent_username)
          store.commit("updateOpponent", {
            opponent_username: data.opponent_username,
            opponent_photo: data.opponent_photo
          });
          store.commit("updateGamemap", data.gamemap);
          setTimeout(() => {
            store.commit("updateStatus", "playing");
          }, 2000);
        }
      }

      socket.onclose = () => {
        console.log("close");
      }
      return {
        store
      }

    });

    onUnmounted(() => {
      socket.close();
      store.commit("updateStatus", "matching");
    })
  }

}
</script>


<style scoped>

</style>