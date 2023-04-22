<template>
    <LoginAndRegisterContentField v-if="!$store.state.user.pulling_info">
        <div class="row justify-content-center">
            <div class="col-12">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username"
                               placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password"
                               placeholder="请输入密码">
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
                <div style="font-size: 14px;color: gray; text-align: center; margin-top: 10px">其他方式登录</div>
                <div @click="qq_login" style="text-align: center; margin-top: 10px;">
                    <img height="30"
                         style="cursor: pointer"
                         src="https://ykexc-1314584214.cos.ap-nanjing.myqcloud.com/202209081054778.png"
                         alt=""/>
                </div>
            </div>
        </div>
    </LoginAndRegisterContentField>
</template>

<script>
import LoginAndRegisterContentField from '@/components/LoginAndRegisterContentField.vue'
import {useStore} from 'vuex'
import {ref} from 'vue'
import router from "@/router/index";
import $ from 'jquery';
export default {
    components: {
        LoginAndRegisterContentField,
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("getInfo", {
                success() {
                    router.push({name: "home"});
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    store.commit("updatePullingInfo", false);
                }
            })
        } else {
            store.commit("updatePullingInfo", false);
        }

        //触发函数
        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                    store.dispatch("getInfo", {
                        success() {
                            router.push({name: 'home'});
                        }
                    });
                },
                error() {
                    error_message.value = "用户名密码错误";
                }
            });
        }

        const qq_login = () => {
            $.ajax({
                url: "https://www.ykexc.work/api/user/account/qq/apply_code/",
                type: "GET",
                success: resp => {
                    if (resp.result === "success") {
                        window.location.replace(resp.apply_code_url)
                    }
                }
            })
        }

        return {
            username,
            password,
            error_message,
            login,
            qq_login
        }
    }
}
</script>

<style scoped>
button {
    width: 100%;
}

div.mb-3 {
    font-size: 14px;
    color: gray;
}


div.error-message {
    color: red;
}
</style>