package com.kjwon.foodchoice.restaurant;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.vo.Restaurant;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    long id;
    String city;
    String smallCity;
    String name;
    String marketType;
    String description;
    boolean isOperating;
    String startDate;
    double latitude;
    double longitude;
    String roadAddress;
    String roadZipCode;
    String titleImageUrl;
    int likesNumber;
    int commentsNumber;
    String clickLink;
    String distance;

    public static RestaurantDto of(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .city(restaurant.getCity())
                .smallCity(restaurant.getSmallCity())
                .name(restaurant.getName())
                .marketType(restaurant.getMarketType())
                .description(restaurant.getDescription())
                .isOperating(restaurant.isOperating())
                .startDate(restaurant.getStartDate())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .roadAddress(restaurant.getRoadAddress())
                .roadZipCode(restaurant.getRoadZipCode())
                .titleImageUrl(restaurant.getTitleImageUrl())
                .likesNumber(restaurant.getLikesNumber())
                .commentsNumber(restaurant.getCommentsNumber())
                .build();
    }

        public static RestaurantDto of(Restaurant restaurant, String distance, String keyword){
            RestaurantDto restaurantDto = RestaurantDto.of(restaurant);

            restaurantDto.setClickLink("foodDetail/" + restaurant.getId());
            restaurantDto.setDistance(keyword + " " + distance + "m");

            return restaurantDto;
    }
}
