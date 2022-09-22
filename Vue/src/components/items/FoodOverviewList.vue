<template lang="">
    <div id="food-overview-list">
        <FoodOverviewItem v-for="foodOverviewData in foodOverviewList" v-bind:key="foodOverviewData" v-bind:foodOverviewData="foodOverviewData"></FoodOverviewItem>
    </div>
</template>
<script>
    import FoodOverviewItem from './FoodOverviewItem'
export default {
    props:['foodOverviewList'],
    components:{
        FoodOverviewItem
    }
}
</script>
<style lang="">
    
</style>