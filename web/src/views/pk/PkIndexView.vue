<template>
  <PlayGround v-if="$store.state.pk.status === 'playing'"/>
  <MatchGround v-if="$store.state.pk.status === 'matching'"/>
  <ResultBoard v-if="$store.state.pk.loser !== 'none'"/>

  <div class="user-color"
       v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.a_id)">
    左下角
  </div>
  <div class="user-color"
       v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.b_id)">
    右上角
  </div>

</template>


<script>
import PlayGround from "@/components/PlayGround";
import MatchGround from "@/components/MatchGround";
import {onMounted, onUnmounted} from "vue";
import {useStore} from "vuex";
import ResultBoard from "@/components/ResultBoard.vue";

export default {
  components: {
    PlayGround,
    MatchGround,
    ResultBoard
  },
  setup() {
    const store = useStore();
    store.commit("updateOpponent", {
      opponent_username: "我的对手",
      opponent_photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png"
    })
    const socketUrl = `wss://ykexc.work/websocket/${store.state.user.token}/`;
    let socket = null;
    store.commit("updateLoser", "none");
    store.commit("updateIsRecord", false);
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
          store.commit("updateGame", data.game);
          setTimeout(() => {
            store.commit("updateStatus", "playing");
          }, 200);
        } else if (data.event === "move") {
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;
          snake0.set_direction(data.a_direction);
          snake1.set_direction(data.b_direction);
          console.log(data);
        } else if (data.event === "result") {
          console.log(data);
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;
          if (data.loser === "all") {
            snake0.status = "die";
            snake1.status = "die";
          } else if (data.loser === "A") {
            snake0.status = "die";
          } else if (data.loser === "B") {
            snake1.status = "die";
          }
          store.commit("updateLoser", data.loser);
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
      store.commit("updateLoser", "none");
    })
  }

}
</script>


<style scoped>
div.user-color {
  text-align: center;
  color: white;
  font-size: 30px;
  font-weight: 600;
}
</style>