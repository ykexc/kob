<template>
<Navbar/>
<router-view></router-view>
</template>



<script>
import Navbar from './components/NavBar.vue';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
export default {
  components: {
    Navbar,
  },
  setup() {
  }
}
</script>


<style>

body {
  background-image: url("https://ykexc-1314584214.cos.ap-nanjing.myqcloud.com/yy21.png");
  background-size: cover;
}
</style>
