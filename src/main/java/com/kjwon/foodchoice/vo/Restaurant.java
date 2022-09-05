package com.kjwon.foodchoice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
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
}
