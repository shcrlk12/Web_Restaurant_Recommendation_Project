<template>
    <div class="food-overview-item-image">
        <router-link v-bind:to="link">
            <img v-bind:src="imageUrl" alt="test">
        </router-link>
    </div>
</template>
<script>
export default {
    props:['imageUrl', 'link']
}
</script>
<style>

</style>