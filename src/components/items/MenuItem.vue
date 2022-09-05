<template lang="">
    <ul id="menuItem">
        <li>{{menuItem.name}}</li>
        <li>{{menuItem.price}}</li>
        <li>{{menuItem.likesNumber}}</li>
    </ul>
</template>
<script>
export default {
    props : ['menuItem']
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";

    #menuItem{
        display: flex;
        border-bottom: 1px solid $border2;
        padding-bottom: 5px;
        margin-bottom: 10px;
        li{
            &:first-child{
                width: 50%;
            }
            &:nth-child(2){
                width: 25%;
            }
            &:last-child{
                width: 25%;
            }
        }
    }
</style>