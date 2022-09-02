package com.kjwon.foodchoice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterKeyword {
    long id;
    String city;
    String smallCity;
    String name;
    String type;
    double latitude;
    double longitude;
    String roadAddress;
    String readZipCode;

}
