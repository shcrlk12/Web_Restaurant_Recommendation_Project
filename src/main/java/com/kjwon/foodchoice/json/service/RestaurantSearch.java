package com.kjwon.foodchoice.json.service;

import com.kjwon.foodchoice.json.errors.NotFoundKeywordException;
import com.kjwon.foodchoice.json.model.LatLongPosition;
import com.kjwon.foodchoice.json.model.RestaurantOverview;

import java.util.List;

public interface RestaurantSearch
{
    //위치를 입력받아서 가까운곳들을 리턴
    List<RestaurantOverview> findNearRestaurant(String location, int start, int end) throws NotFoundKeywordException;

    //현재 가장 인기있는 곳들을 리턴
    List<RestaurantOverview> findPopularRestaurant(String location, int offset, int number)  throws NotFoundKeywordException;

    List<RestaurantOverview> findMostPopularRestaurant(int offset, int number);

    LatLongPosition findLocation(String name) throws NotFoundKeywordException;

}
