package com.kjwon.foodchoice.json.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class RestaurantOverview extends Restaurant{
    public RestaurantOverview(String name, String imageUrl, String clickLink, String info, String foodType, int like, int comment, LatLongPosition position){
        super(name,imageUrl, clickLink, info, foodType, like, comment);

        this.position = position;
    }

    public RestaurantOverview(LatLongPosition position){
        super();
        this.position = position;
    }
    @JsonIgnore
    private LatLongPosition position;


}
