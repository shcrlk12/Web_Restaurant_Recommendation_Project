<template lang="">
    <div>
        <CommentItem v-for="commentItem in commentItemList" v-bind:commentItem="commentItem" v-bind:key="commentItem"></CommentItem>
    </div>
</template>
<script>
    import CommentItem from './CommentItem';
export default {
    props:['commentItemList'],
    components:{
        CommentItem
    }
}
</script>
<style lang="">
    
</style>