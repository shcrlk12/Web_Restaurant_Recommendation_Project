<template>
    <div id="main-header">
        <div id="main-header-container">
            <LeftHeader v-on:screenModeChange="screenModeChange" v-bind:link="link"></LeftHeader>
            <LocationInput></LocationInput>
            <RightHeader></RightHeader>
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
<style>

</style>