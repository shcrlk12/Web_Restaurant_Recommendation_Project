<template>
    <i class="fa" v-bind:class="{'selected' : selected}" aria-hidden="true">{{text}}</i>
</template>
<script>
export default {
    props:{
        text:{
            type:String,
            defulat:''
        },
        selected:{
            type:Boolean,
            defulat:false
        }
    }
}
</script>
<style lang="scss">
    .selected{
        background-color: rgba($color: #767676, $alpha: 0.25)
    }
</style>