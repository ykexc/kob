<template>
    <Navbar/>
    <router-view></router-view>
    <footer style="text-align: center">
        <p style="color: snow">
            All rights reserved.
            <a class="link-style" href="https://beian.miit.gov.cn" target="_blank">
                陕ICP备2023000609号-1</a>
        </p>
    </footer>
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
.link-style {
    text-decoration: none;
    color: snow;
}
</style>
