<template>
    <div id="main-page">
        <PageHeader v-on:searchTypeChange="searchTypeChange" v-bind:searchType="searchType"></PageHeader>
        <FoodOverviewList v-bind:foodOverviewList="foodOverviewList"></FoodOverviewList>
    </div>
</template>
<script>
import PageHeader from '../page/PageHeader.vue';
import FoodOverviewList from '../items/FoodOverviewList.vue';
import axios from 'axios';
import axiosUrlChange from '../util/axiosUrlChange';

export default {
    data(){
        return{
            searchType : '',
            foodOverviewList : [],
            offset : 0
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
                    vue.foodOverviewList.push(d);
                });
            }
        })
      },
      searchTypeChange(value){
        if(value === '인기'){
            this.searchType = 'popular';
        }else if(value === '거리'){
            this.searchType = 'distance';
        }

        localStorage.setItem('searchType', this.searchType);
      },
      fetchOveview(){
        this.searchType = localStorage.getItem('searchType');
    
        let locationQuery = this.$route.query.location ? `&location=${this.$route.query.location}` : '';
        let classificationTypeQuery = this.searchType ? `&classificationType=${this.searchType}` : '&classificationType=popular';
           
        let offsetQuery = this.offset ? `&offset=${this.offset}`: '';

        this.fetchData(axiosUrlChange.currentLocationUrl(`api/JSON/restaurant/overview?number=3
        ${locationQuery ? locationQuery : ''}
        ${classificationTypeQuery ? classificationTypeQuery : ''}
        ${offsetQuery ? offsetQuery : ''}`));

        this.offset += 3;
        
      }
    },
    created(){
        this.fetchOveview();
        window.addEventListener("scroll", () => {
                if(document.body.scrollHeight - 1 < window.scrollY + window.outerHeight){
                    console.log('scroll end');
                    this.fetchOveview();
                }
            });
    
    },
    updated(){
        if(window.location.href.includes('localhost')){
            console.log('local');
            this.fetchData('http://localhost:1110/api/JSON/restaurant/overview?number=3');
        }
        else{
            let locationQuery = this.$route.query.location ? `&location=${this.$route.query.location}` : '';
            let classificationTypeQuery = this.searchType ? `&classificationType=${this.searchType}` : '&classificationType=popular';

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