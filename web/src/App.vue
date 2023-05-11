<template>
    <Navbar/>
    <router-view></router-view>
    <footer style="text-align: center">
        <p style="color: snow">
            <span>
                <img src="@/assets/images/beian.png" style="vertical-align: bottom; width: 18px;margin-bottom: 3px" alt="公安备案"/>
            <a class="link-style" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=61042402000127">陕公网安备 61042402000127号</a>
            </span>


            |
            <a class="link-style" href="https://beian.miit.gov.cn" target="_blank">
                陕ICP备2023000609号-1</a>

        </p>
        <p style="line-height: 0;color: snow">All rights reserved.</p>
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
