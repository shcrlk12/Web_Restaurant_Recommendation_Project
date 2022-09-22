<template>
    <div>
        <div class="food-overview-body">
            <div>{{name}}</div>
            <div class="food-name">{{foodType}}</div>
        </div>
        <div class="food-overview-footer">
            <div class="location">{{distance}}</div>
            <div class="righStide">
                <i class="fa fa-comment" aria-hidden="true">{{comment}}</i>
                <i class="fa fa-heart" aria-hidden="true">{{like}}</i>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props:['name', 'distance', 'foodType', 'like', 'comment']
}
</script>
<style>

</style>