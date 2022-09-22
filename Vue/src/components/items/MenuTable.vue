<template lang="">
    <div>
        <MenuItem v-for="menuitem in menuItemList" v-bind:key="menuitem" v-bind:menuItem="menuitem"></MenuItem>
    </div>
</template>
<script>
    import MenuItem from './MenuItem.vue'
    
export default {
    props:['menuItemList'],
    components:{
        MenuItem
    }
}
</script>
<style lang="scss">
    
</style>