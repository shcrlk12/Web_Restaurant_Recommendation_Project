<template>
    <div class="comment-container">
        <div class="comment-header">
            <div>
                <div>{{commentItem.userId}}</div>
                <div>{{commentItem.registerDate}}</div>
            </div>
            <div>{{commentItem.likesNumber}}</div>
        </div>
        <div class="comment-content">{{commentItem.content}}</div>
    </div>
</template>
<script>
export default {
    props:['commentItem']
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";

    .comment-container{
        border-bottom: 1px solid $border3;
        .comment-header{
            padding-top: 10px;
            display:flex;
            justify-content: space-between;
            > div{
                &:first-child{
                    padding-left : 20px;
                }
            }
        }
        .comment-content{
            padding: 20px 10px;

        }
    }
</style>