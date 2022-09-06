<template lang="">
    <div id="main-search-input">
        <div id="search-input">
            <div id="search-container">
                <i class="fa fa-search" aria-hidden="true"></i>
                <input @focusout="focusout" @input="keyInput" @focus="focus" @keyup.enter="submit" type="text" placeholder="지역 및 장소를 입력하세요.">
            </div>
        </div>
        <KeywordBox v-for="keyword in similarKeywordList" v-bind:key="keyword.name" v-bind:keyword="keyword.name" v-on:keywordClick="keywordClick">{{keyword}}</KeywordBox>
    </div>
</template>
<script>
import KeywordBox from './KeywordBox'
import axios from 'axios';
import consVow from '../util/consonantVowelParse';

export default {
    data(){
        return{
            similarKeywordList:[],
        }
    },
    methods:{
        keywordClick(event){
            let location = event.target.innerText;
            window.location = `/foodOverviewList?number=10&location=${location}`;
        },
        similarKeywordListClear(){
            this.similarKeywordList = [];
        },
        fetchSimilarKeyword(url){
            const vue = this;
            let baseUrl;
        
            if(window.location.href.includes('localhost')){
                baseUrl = 'http://localhost:1110/';
            }
            else{
                baseUrl = 'http://115.139.45.137:1110/';
            }

            axios.get(baseUrl + url)
            .then(function(response) {
                let data = response.data;

                if(data.success === true){
                    vue.similarKeywordListClear();

                    response.data.response.forEach(d => {
                        vue.similarKeywordList.push(d);
                    });
                }
            })
        },
        submit(){
            let location = document.querySelector("#search-container input").value;
            window.location = `/foodOverviewList?number=10&location=${location}`;
        },
        focus(){
            console.log('focus')

        },
        focusout(){
            setTimeout(() => {
                this.similarKeywordListClear();
            }, 10);
        },
        keyInput(e){
            let keyword = consVow.getConsonantVowel(e.target.value);
            let keywordQuery = keyword ? `keyword=${keyword}` : '';

            this.fetchSimilarKeyword(`api/JSON/keyword?${keywordQuery}`);
        }
    },
    components:{
        KeywordBox
    }
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";

#main-search-input{
    display:flex;
    flex-direction: column;
    #search-input{
        background-color: $bg-page2;
        height:48px;
        border-style: solid;
        border-radius: 2px;
        border-color: $color-gray-roboflow-300;
        border-width: 2px;
        width: 100%;
        overflow: hidden;
        #search-container{
            height:50%;
            width: 100%;
            margin: auto 0;
            overflow: hidden;
            i{
                @include wh-center;
                justify-content: center;
                width: 30px;
                color: $color-gray-roboflow-600;
            }
            input{
                width: 100%;
                border: none;

                &:focus{
                    outline: none;

                }
            }
        }
    }
}
</style>