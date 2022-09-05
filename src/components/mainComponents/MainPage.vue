<template>
    <div id="main-page">
        <PageHeader></PageHeader>
        <FoodOverviewList v-bind:foodOverviewList="foodOverviewList"></FoodOverviewList>
    </div>
</template>
<script>
import PageHeader from '../page/PageHeader.vue';
import FoodOverviewList from '../items/FoodOverviewList.vue';
import axios from 'axios';

export default {
    data(){
        return{
            foodOverviewList : []
        }
    },
    methods:{
      fetchData(url){
        const vue = this;

        axios.get(url)
        .then(function(response) {
            let data = response.data;
            console.log(data)
            if(data.success === true){
                response.data.response.forEach(d => {
                    let foodOverviewData = {
                        imageUrl : d.titleImageUrl,
                        link : d.clickLink,
                        name : d.name,
                        foodType : d.foodType,
                        distance : d.distance,
                        comment : d.commentsNumber,
                        like : d.likesNumber
                    }
                    vue.foodOverviewList.push(foodOverviewData);
                });
            }
        })
      }
    },
    created(){
        if(window.location.href.includes('localhost')){
            console.log('local');
            this.fetchData('http://localhost:1110/api/JSON/restaurant/overview?number=3');
        }
        else{
            let locationQuery = this.$route.query.location ? `&location=${this.$route.query.location}` : '';
            let classificationTypeQuery = this.$route.query.classificationType ? `&classificationType=${this.$route.query.classificationType}` : '';

            console.log('external');

            this.fetchData(`http://115.139.45.137:1110/api/JSON/restaurant/overview?number=3${locationQuery ? locationQuery : ''}${classificationTypeQuery ? classificationTypeQuery : ''}`);
        }
    },
    components:{
    PageHeader,
    FoodOverviewList
}    
}
</script>
<style>

</style>