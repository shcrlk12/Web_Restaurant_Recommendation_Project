<template lang="">
    <div id="header-right-container">
        <MyButton @click="loginBtnClick" v-bind:isNormal="true" myButtonName="로그인"></MyButton>
        <LoginModal v-on:exitClick="exitClick" v-bind:isVisable="isVisable"></LoginModal>

    </div>
</template>
<script>
import MyButton from '../button/MyButton.vue'
import LoginModal from '../items/LoginModal.vue'

export default {
    data(){
        return{
            isVisable:false
        }
    },
    methods:{
        loginBtnClick(){
            console.log('test')
            this.isVisable= true;
        },
        exitClick(){
            this.isVisable = false;
        }
    },
    components:{
        MyButton,
        LoginModal
    }
}
</script>
<style lang="scss">
#header-right-container{
    display: flex;
    flex: 0 0 100px;
    padding-left: 10px;
}
</style>