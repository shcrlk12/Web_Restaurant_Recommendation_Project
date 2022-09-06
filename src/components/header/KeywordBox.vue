<template>
    <div @click="click" class="keyword-box">
        <span >
            {{keyword}}
        </span>
    </div>
</template>
<script>
export default {
    props:['keyword'],
    methods:{
        click(event){
            this.$emit('keywordClick',event);
        }
    }
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";

    .keyword-box{
        border: 1px solid $border2;
        width: 100%;
        height: 30px;
        @include h-center;

        background-color: $bg-element8;
        z-index: 1;
        span{
            padding-left: 15px;
        }
        
    }
</style>