<template>
  <LoginAndRegisterContentField>
    <div class="row justify-content-md-center">
      <div class="col-12">
        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
          </div>
          <div class="error-message">{{ error_message }}</div>
          <button type="submit" class="btn btn-primary">提交</button>
        </form>
      </div>
    </div>
  </LoginAndRegisterContentField>
</template>

<script>
import LoginAndRegisterContentField from '@/components/LoginAndRegisterContentField.vue'
import { ref } from 'vue'
import router from "@/router"
import $ from 'jquery'
export default {
  components: {
      LoginAndRegisterContentField,
  },
  setup() {
    let username = ref('');
    let password = ref('');
    let confirmedPassword = ref('');
    let error_message = ref('');

    const register = () => {
      $.ajax({
        url: "https://www.ykexc.work/api/user/account/register/",
        type: 'post',
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value
        },
        success(resp) {
          if (resp.error_message === 'success') {
            router.push({name : "user_account_login"});
          } else {
            error_message.value = resp.error_message;
          }
        }
      });
    }
    return {
      username,
      password,
      confirmedPassword,
      register,
      error_message
    }
  }
}
</script>

<style scoped>
button {
  width: 100%;
}
div.error-message {
  color: red;
  justify-content: center;
}
</style>