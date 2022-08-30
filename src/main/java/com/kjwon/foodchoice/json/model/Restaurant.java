package com.kjwon.foodchoice.json.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    private String name;
    private String imageUrl;
    private String clickLink;
    private String info;
    private String foodType;
    private int like;
    private int comment;
}
