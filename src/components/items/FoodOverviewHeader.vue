<template>
    <div class="food-overview-item-image">
        <router-link v-bind:to="clickLink">
            <img v-bind:src="imageUrl" alt="test">
        </router-link>
    </div>
</template>
<script>
export default {
    data(){
        return{
            clickLink:''
        }
    },
    props:['imageUrl', 'link'],
    created(){
        this.clickLink = 'foodDetail/' + this.link;
    },
    updated(){
        this.clickLink = 'foodDetail/' + this.link;
    }
}
</script>
<style>

</style>