<template>
    <div id="page-header-container">
        <ImgButton @click="click" class="fa-line-chart" text="인기" v-bind:selected="isPopular"></ImgButton>
        <ImgButton @click="click" class="fa-road" text="거리" v-bind:selected="isDistance"></ImgButton>
    </div>
</template>
<script>
import ImgButton from '../button/ImgButton.vue';
export default {
    data(){
        return{
            isPopular : false,
            isDistance : false
        }
    },
    props:['searchType'],
    methods:{
        click(e){
            let val = e.target.innerText;
            this.$emit('searchTypeChange',val);
        },
        changeSelect(){
            if(this.searchType == 'popular')
            {
                this.isPopular = true;
                this.isDistance = false;
            }else if(this.searchType == 'distance')
            {
                this.isPopular = false;
                this.isDistance = true;
            }
        }
    },
    created(){
        this.changeSelect();
    },
    updated(){
        this.changeSelect();
    },
    components: { ImgButton }
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";

  #page-header-container{
    font-size: $font-size-400;
    font-weight: $font-weight-semibold;
    
    height : 60px;

    @include h-center();
    justify-content:flex-start;
    padding-left: 20px;
    padding-right: 20px;

    i{
        &:nth-child(2){
            padding-left: 15px;
        }
    }
}
</style>