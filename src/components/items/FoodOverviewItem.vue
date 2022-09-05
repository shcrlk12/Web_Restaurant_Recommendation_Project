<template>
    <div id="food-overview-container">
        <FoodOverviewHeader v-bind:imageUrl="foodOverviewData.imageUrl" v-bind:link="foodOverviewData.link">{{foodOverviewData.imageUrl}}</FoodOverviewHeader>
        <FoodOverviewInfo v-bind:name="foodOverviewData.name" v-bind:distance="foodOverviewData.distance" v-bind:foodType="foodOverviewData.foodType" v-bind:like="foodOverviewData.like" v-bind:comment="foodOverviewData.comment"></FoodOverviewInfo>
    </div>
</template>
<script>
import FoodOverviewHeader from './FoodOverviewHeader.vue';
import FoodOverviewInfo from './FoodOverviewInfo.vue';

export default {
    props:{
        foodOverviewData:{
            type:Object,
            default: function(){
                return {
                    imageUrl : 'testUrl',
                    link : 'testLink',
                    name : '',
                    distance : '',
                    foodType: '',
                    like: 0,
                    comment: 0}
            }
        }
    },
    components: { FoodOverviewHeader, FoodOverviewInfo },
}
</script>
<style>
</style>
