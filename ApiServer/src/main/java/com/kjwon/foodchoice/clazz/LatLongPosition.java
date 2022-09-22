package com.kjwon.foodchoice.clazz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LatLongPosition {
    public LatLongPosition(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    private String city;
    private String smallCity;
    private double latitude;
    private double longitude;
}
