package com.kjwon.foodchoice.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterKeyword {
    long id;
    String name;
    String type;
    double latitude;
    double longitude;
}
