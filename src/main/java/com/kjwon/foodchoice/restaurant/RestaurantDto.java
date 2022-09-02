package com.kjwon.foodchoice.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDto {
    private String name;
    private String imageUrl;
    private String clickLink;
    private String info;
    private String foodType;
    private int like;
    private int comment;
}
