<template>
    <div>
        <button v-bind:class="[{'normal':isNormal}, {'red':isRed}]" class="common-btn test123">{{this.myButtonName}}</button>
    </div>
</template>
<script>
export default {
    data() {
        return {
        }
    },
    props:{
        myButtonName:{
            type:String,
            default:''
        },
        isNormal:{
            type:Boolean,
            default:false
        },
        isRed:{
            type:Boolean,
            default:false
        }
    }
}
</script>
<style>
    .common-btn{
        border : 0;
        min-width: 60px;
        box-sizing : border-box;
        display: inline-block;
        cursor:pointer;
        text-align: center;
        height: 48px;
        border-radius: 24px;
        font-weight: 600;
        font-size: 16px;
        padding: 15px;
        font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Oxygen-Sans,Ubuntu,Cantarell,"Fira Sans","Droid Sans","Helvetica Neue",Helvetica,"ヒラギノ角ゴ Pro W3","Hiragino Kaku Gothic Pro",メイリオ,Meiryo,"ＭＳ Ｐゴシック",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    }
    
    .normal{
        color:#000;
        background-color:#efefef;
    }

    .red{
        color:#fff;
        background-color:#e60023;

    }
</style>