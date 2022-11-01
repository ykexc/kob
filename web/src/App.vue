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
  }
}
</script>


<style>

body {
  background-image: url("@/assets/8.jpg");
  background-size: cover;
}
</style>
