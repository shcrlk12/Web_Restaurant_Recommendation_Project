package com.kjwon.foodchoice.json.util;

import com.kjwon.foodchoice.json.model.LatLongPosition;
import com.kjwon.foodchoice.json.model.RestaurantOverview;
import com.kjwon.foodchoice.json.model.TmPosition;

public class FunctionUtil {
    static double westLongitude = 125;
    static double westLatitude = 34.5;
    static double earthRadius = 6367445.157;
    static double earthRound = 2 * Math.PI * earthRadius;

    public static double distance(TmPosition startPosition, TmPosition endPosition){
        double x = startPosition.getX() - endPosition.getX();
        double y = startPosition.getY() - endPosition.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static TmPosition convertTmCoordinate(LatLongPosition latLongPosition) {
        double latitude;
        double longitude;

        latitude = latLongPosition.getLatitude();
        longitude = latLongPosition.getLongitude();

        double radius = Math.cos(Math.toRadians(latitude)) * earthRadius;
        double round = 2 * Math.PI * radius;

        double y = round * (longitude - westLongitude) / 360;
        double x = earthRound * (latitude - westLatitude) / 360;

        return new TmPosition(x, y);
    }

}
