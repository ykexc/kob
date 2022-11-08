<template>
<Navbar/>
<router-view></router-view>
</template>



<script>
import Navbar from './components/NavBar.vue';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
//import $ from 'jquery';
export default {
  components: {
    Navbar,
  },
  setup() {
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: 'huya',
    //     password: '123456',
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYWM4MjZkN2IyNWM0MzE3YWNiNGMxM2I2NzJlYTdjNSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzkxODQ4MSwiZXhwIjoxNjY5MTI4MDgxfQ.XIZ-WkM50d0Fpr8z62Lp3F53KPnicDsYf2yKnaZIzhA",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "hmy",
    //     password: "phmy",
    //     confirmedPassword: "phmy",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
  }
}
</script>


<style>

body {
  background-image: url("@/assets/images/8.jpg");
  background-size: cover;
}
</style>
