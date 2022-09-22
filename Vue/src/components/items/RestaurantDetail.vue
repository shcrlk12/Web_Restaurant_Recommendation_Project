<template lang="">
    <div id="restaurant-detail-container">
        <div id="restaurant-detail-header">
            <div>
                <span>{{restuarantDetail.name}}</span>
                <span>({{restuarantDetail.marketType}})</span>
            </div>
            <div>
                <span>{{restuarantDetail.roadAddress}}</span>
            </div>
        </div>
        <div id="restaurant-detail-body">
            {{restuarantDetail.description}}
        </div>
    </div>
</template>
<script>
export default {
    props:['restuarantDetail']
}
</script>
<style lang="scss">
    @import "../../assets/color.scss";
    #restaurant-detail-container{
        margin: 0 10px;
        #restaurant-detail-header{
            height: 100px;
            display : flex;
            flex-direction: column;
            div{
                &:first-child{
                    span{
                        &:first-child{
                            font-weight: $font-weight-semibold;
                        }
                        &:last-child{
                            margin-left: 10px;
                        }
                    }
                }
                &:nth-child(2){
                    margin-top:30px;
                    padding-bottom: 10px;
                    border-bottom: 1px solid $border2;
                }
            }
            
        }

        #restaurant-detail-body{
            margin-bottom: 30px;
        }
    }
</style>