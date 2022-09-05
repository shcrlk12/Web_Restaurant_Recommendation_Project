<template lang="">
    <div id="search-input">
        <div id="search-container">
            <i class="fa fa-search" aria-hidden="true"></i>
            <input @keyup.enter="submit" type="text" placeholder="지역 및 장소를 입력하세요.">
        </div>
    </div>
</template>
<script>
export default {
    methods:{
        submit(){
            console.log('submit')

            let location = document.querySelector("#search-container input").value;
            window.location = `/foodOverviewList?number=10&location=${location}&classificationType=distance`;
        }
    }
}
</script>
<style>
 
</style>