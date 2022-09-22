<template>
    <div id="main-header">
        <div id="main-header-container">
            <div>
                <LeftHeader v-on:screenModeChange="screenModeChange" v-bind:link="link"></LeftHeader>
                <RightHeader></RightHeader>
            </div>
            <div>
                <LocationInput></LocationInput>
            </div>
        </div>
    </div>
</template>
<script>
import LeftHeader from '../header/LeftHeader.vue'
import LocationInput from '../header/LocationInput.vue';
import RightHeader from '../header/RightHeader.vue';

export default {
    props:['link'],
    components:{
        LeftHeader,
        LocationInput,
        RightHeader
    },
    methods:{
        screenModeChange(screenMode){
            this.$emit('screenModeChange', screenMode);
        }
    }

}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";
#main-header{

    height: 130px;
    width:100%;

    #main-header-container{
        height : 100%;
        margin : 0 10px;
        > div{
            &:nth-child(1){
                @include h-center;
                justify-content: space-between;
            }
            &:nth-child(2){

            }
        }
    }
}
</style>