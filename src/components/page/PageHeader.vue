<template>
    <div id="page-header-container">
        <ImgButton class="fa-line-chart" text="인기" v-bind:selected="true"></ImgButton>
        <ImgButton class="fa-road" text="거리" v-bind:selected="false"></ImgButton>
    </div>
</template>
<script>
import ImgButton from '../button/ImgButton.vue';
export default {
    components: { ImgButton }
}
</script>
<style>
  
</style>