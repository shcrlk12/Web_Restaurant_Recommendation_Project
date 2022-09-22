<template>
  <div v-bind:id="[this.screenMode ? 'dark-mode' : 'white-mode']">
    <MainHeader v-on:screenModeChange="screenModeChange" link="/"></MainHeader>
    <router-view></router-view>
  </div>
</template>
<script>
  import MainHeader from'./components/mainComponents/MainHeader.vue'

export default {
  data(){
    return{
      screenMode : {
        type : Boolean
      }
    }
  },
  methods:{
    screenModeChange(screenMode){
      this.screenMode = screenMode;
      localStorage.setItem('screenMode', this.screenMode);
      console.log(this.screenMode)
    },
    getBoolean(data){
      if(typeof(data) === 'string'){
          if(data === 'true')
              data = true;
          else
              data = false;
      }
      return data;
    }
  },
  created(){
      this.screenMode = localStorage.getItem('screenMode');
      this.screenMode = this.getBoolean(this.screenMode);
  },
  components:{
    MainHeader
  }
 }
</script>
<style lang="scss">
@import './assets/main.scss';

</style>