package com.kjwon.foodchoice.restaurant;

import com.kjwon.foodchoice.errors.NotFoundKeywordException;

import java.util.List;

public interface RestaurantSearch {
    //위치를 입력받아서 가까운곳들을 리턴
    List<RestaurantDto> findNearRestaurant(String location, int start, int end) throws NotFoundKeywordException;

    //현재 가장 인기있는 곳들을 리턴
    List<RestaurantDto> findPopularRestaurant(String location, int offset, int number)  throws NotFoundKeywordException;

    List<RestaurantDto> findMostPopularRestaurant(int offset, int number);
}
