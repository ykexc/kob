<template>
<div class="container content-field">
        <div class="card">
            <div class="card-body" :style="{height: h}" >
                <slot></slot>
            </div>
        </div>
    </div>
</template>


<script>

export default {
    props: {
        h: {
            type: String,
            required: false
        }
    }
}

</script>


<style scoped>

div.content-field {
    margin-top: 10px;
}

</style>