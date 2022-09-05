<template>
    <div>
        <RestaurantDetail v-bind:restuarantDetail="restuarantDetail"></RestaurantDetail>
        <MenuTable v-bind:menuItemList="menuItemList"></MenuTable>
        <div id="comment-input">
            <CommentInput></CommentInput>
        </div>
        <CommentList v-bind:commentItemList="commentItemList"></CommentList>
    </div>
</template>
<script>
    import RestaurantDetail from '../items/RestaurantDetail'
    import MenuTable from '../items/MenuTable.vue'
    import CommentInput from '../items/CommentInput';
    import CommentList from '../items/CommentList';
    import axios from 'axios';

export default {
    data(){
        return{
            restuarantDetail : {},
            menuItemList : [],
            commentItemList : []
        }
    },
    methods:{
        fetchRestuarantDetail(url){
            const vue = this;
            axios.get(url)
            .then(function(response) {
                let data = response.data;

                if(data.success === true){
                    vue.restuarantDetail = {...data.response};
                    console.log(vue.restuarantDetail)

                }
            })
        },
        fetchData(url){
        const vue = this;

        axios.get(url)
        .then(function(response) {
            let data = response.data;

            if(data.success === true){
                response.data.response.forEach(d => {
                    vue.menuItemList.push(d);
                });
            }
        })
      },
      fetchComment(url){
        const vue = this;

        axios.get(url)
        .then(function(response) {
            let data = response.data;

            if(data.success === true){
                console.log(response.data.response)
                response.data.response.forEach(d => {
                    vue.commentItemList.push(d);
                });
            }
        })
      }
    },
    created(){
        let baseUrl;
        
        if(window.location.href.includes('localhost')){
            baseUrl = 'http://localhost:1110/';
        }
        else{
            baseUrl = 'http://115.139.45.137:1110/';
        }
        let restaurantQuery = this.$route.params.id ? `restaurantId=${this.$route.params.id}` : '';

        //1. restuarant detail
        this.fetchRestuarantDetail(`${baseUrl}api/JSON/restaurant/detail?${restaurantQuery}`);

        //2. fetch menu

        this.fetchData(`${baseUrl}api/JSON/restaurant/getMenus?${restaurantQuery}`);

        //3. fetch comment
        let offsetQuery = 'offset=0';
        let numberQuery = 'number=3';

        this.fetchComment(`${baseUrl}api/JSON/restaurant/getComments?${restaurantQuery}&${offsetQuery}&${numberQuery}`)
    },
    components:{
        MenuTable,
        CommentInput,
        CommentList,
        RestaurantDetail
    }
}
</script>
<style lang="">
    
</style>