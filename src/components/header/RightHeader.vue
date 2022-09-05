<template lang="">
    <div id="header-right-container">
        <MyButton v-bind:isNormal="true" myButtonName="로그인"></MyButton>
    </div>
</template>
<script>
import MyButton from '../button/MyButton.vue'

export default {
    components:{
        MyButton
    }
}
</script>
<style>

</style>