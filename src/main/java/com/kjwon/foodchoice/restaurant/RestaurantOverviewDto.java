package com.kjwon.foodchoice.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjwon.foodchoice.clazz.LatLongPosition;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class RestaurantOverviewDto extends RestaurantDto {
    public RestaurantOverviewDto(String name, String imageUrl, String clickLink, String info, String foodType, int like, int comment, LatLongPosition position){
        super(name,imageUrl, clickLink, info, foodType, like, comment);

        this.position = position;
    }

    public RestaurantOverviewDto(LatLongPosition position){
        super();
        this.position = position;
    }
    @JsonIgnore
    private LatLongPosition position;


}
