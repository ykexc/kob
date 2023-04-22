<template>
  <div>

  </div>
</template>


<script>
import router from "@/router";
import {useStore} from "vuex";
import {useRoute} from "vue-router";
import $ from 'jquery'
export default {
    name: "UserAccountQQReceiveCodeView",
    setup() {
        const store = useStore()
        const route = useRoute()
        $.ajax({
            url: "https://ykexc.work/api/user/account/qq/receive_code",
            type: "GET",
            data: {
                code: route.query.code,
                state: route.query.state
            },
            success: resp => {
                if (resp.result === 'success') {
                    localStorage.setItem("jwt_token", resp.jwt_token);
                    store.commit("updateToken", resp.jwt_token);
                    router.push({name: "home"});
                    store.commit("updatePullingInfo", false);
                } else {
                    router.push({name: "user_account_login"});
                }
            }
        })
    }
}
</script>



<style scoped>

</style>