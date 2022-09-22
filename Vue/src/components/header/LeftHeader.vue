<template>
    <div id="left-side-header">
        <h1>
            <router-link v-bind:to="this.link">
                Food it !
            </router-link>
        </h1>
        <div><i v-on:click="screenModeChange" class="fa fa-sun-o" v-bind:class="this.getClassName()" aria-hidden="true"></i></div>
    </div>
</template>
<script>
export default {
    data(){
      return{
        screenMode : false,
      }
    },
    props:['link'],
    methods:{
        screenModeChange(){
            this.screenMode = !this.getBoolean(this.screenMode);
            this.$emit('screenModeChange', this.screenMode);
        },
        getClassName(){
            this.screenMode = localStorage.getItem('screenMode');
            
            if(this.getBoolean(this.screenMode))
                return 'fa-moon-o';
            else
                return 'fa-sun-o';
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
    }
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";
    #left-side-header{
        flex: 0 0 130px;
        display : flex;

        h1{
            width: 90px;
            font-size : $font-size-400;
            font-weight: $font-weight-semibold;
            display:flex;
            align-items: center;
            padding-left:8px;
            line-height: 70px;
            margin : 0;
        }
        div{
            width: 40px;
            display:flex;
            align-items: center;
            padding-left:10px;
            padding-right:10px;
        }
    }
</style>