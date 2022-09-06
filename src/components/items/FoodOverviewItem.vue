<template>
    <div id="food-overview-container">
        <FoodOverviewHeader v-bind:imageUrl="foodOverviewData.titleImageUrl" v-bind:link="foodOverviewData.id"></FoodOverviewHeader>
        <FoodOverviewInfo v-bind:name="foodOverviewData.name" v-bind:distance="foodOverviewData.distance" v-bind:foodType="foodOverviewData.marketType" v-bind:like="foodOverviewData.likesNumber" v-bind:comment="foodOverviewData.commentsNumber"></FoodOverviewInfo>
    </div>
</template>
<script>
import FoodOverviewHeader from './FoodOverviewHeader.vue';
import FoodOverviewInfo from './FoodOverviewInfo.vue';

export default {
    props:['foodOverviewData'],
    components: { FoodOverviewHeader, FoodOverviewInfo },
}
</script>
<style>
</style>
