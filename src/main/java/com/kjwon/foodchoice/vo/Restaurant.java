package com.kjwon.foodchoice.vo;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.restaurant.RestaurantOverviewDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
    long seq;
    String name;
    String marketType;
    boolean isOperating;
    String startDate;
    float latitude;
    float longitude;
    String roadAddress;
    String roadZipCode;
    String titleImageUrl;
    int likesNumber;
    int commentsNumber;

    public static RestaurantOverviewDto of(Restaurant restaurantDto, String distance, String keyword){
        return  new RestaurantOverviewDto(restaurantDto.getName(),
                restaurantDto.getTitleImageUrl(),
                "foodDetail/" + restaurantDto.getSeq(),
                keyword + " " + distance + "m",
                restaurantDto.getMarketType(),
                restaurantDto.getLikesNumber(),
                restaurantDto.getCommentsNumber(),
                new LatLongPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude()));
    }

    public static RestaurantOverviewDto of(Restaurant restaurantDto){
        return  new RestaurantOverviewDto(restaurantDto.getName(),
                restaurantDto.getTitleImageUrl(),
                "foodDetail/" + restaurantDto.getSeq(),
                "",
                restaurantDto.getMarketType(),
                restaurantDto.getLikesNumber(),
                restaurantDto.getCommentsNumber(),
                new LatLongPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude()));
    }
}
