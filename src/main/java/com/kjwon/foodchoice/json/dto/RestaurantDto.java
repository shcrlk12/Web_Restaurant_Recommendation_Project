package com.kjwon.foodchoice.json.dto;

import com.kjwon.foodchoice.json.model.LatLongPosition;
import com.kjwon.foodchoice.json.model.RestaurantOverview;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
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

    public static RestaurantOverview of(RestaurantDto restaurantDto, String distance, String keyword){
        return  new RestaurantOverview(restaurantDto.getName(),
                restaurantDto.getTitleImageUrl(),
                "foodDetail/" + restaurantDto.getSeq(),
                keyword + " " + distance + "m",
                restaurantDto.getMarketType(),
                restaurantDto.getLikesNumber(),
                restaurantDto.getCommentsNumber(),
                new LatLongPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude()));
    }

    public static RestaurantOverview of(RestaurantDto restaurantDto){
        return  new RestaurantOverview(restaurantDto.getName(),
                restaurantDto.getTitleImageUrl(),
                "foodDetail/" + restaurantDto.getSeq(),
                "",
                restaurantDto.getMarketType(),
                restaurantDto.getLikesNumber(),
                restaurantDto.getCommentsNumber(),
                new LatLongPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude()));
    }
}
