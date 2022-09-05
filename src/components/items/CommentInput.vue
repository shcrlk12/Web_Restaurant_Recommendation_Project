<template>
    <div id="comment-input-container" class="clearfix">
        <textarea @focusout="inputFocusOut" @focus="inputFocus" id="comment-input-textarea" type="text"></textarea>
        <label for="comment-input-textarea">댓글을 입력하세요</label>
        <div id="comment-submit-btn">
            <button>등록</button>
        </div>
    </div>
</template>
<script>
export default {
    methods:{
        inputFocus(){
            let label = document.querySelector('#comment-input-container label');
            label.style.display = 'none';
        },
        inputFocusOut(){
            let textarea = document.querySelector('#comment-input-container textarea');
            if(textarea.value.length === 0){
                let label = document.querySelector('#comment-input-container label');
                label.style.display = 'block';
            }
        }
    }
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    @import "../../assets/main.scss";

    .clearfix::after{
        display : block;
        clear:both;
        content: '';
    }

    #comment-input-container{
        box-sizing: border-box;
        width: 100%;
        height: 70%;
        position:relative;
        #comment-input-textarea{
            box-sizing: border-box;
            border: 2px solid $border2;
            border-radius: 5px;
            padding: 10px;
            width: 100%;
            height: 100%;
            font-size: $font-size-300;

            &:focus{
                outline: none;
                animation-duration: 0.75s;
                animation-name: borderChange;
                animation-fill-mode: forwards;
            }
        }
        label{
            position:absolute;
            left: 12px;
            top : 12px;
        }

        #comment-submit-btn{
            height: 30%;
            width: 100%;
            button{
                margin-top: 5px;
                box-sizing: border-box;
                float: right;
                margin-right: 10px;
                border-radius: 3px;
                padding: 5px 10px;
                border : 1px solid black;
                background-color: $bg-element2;
                font-weight: $font-weight-semibold;
                font-size: 13px;
            }
        } 
    }

@keyframes borderChange {
    from {
        border-color: $border2;
    }

    to {
        border-color: $border1;
    }
}
</style>