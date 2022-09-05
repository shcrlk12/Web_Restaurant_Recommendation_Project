package com.kjwon.foodchoice.restaurant;

import com.kjwon.foodchoice.dto.CommentDto;
import com.kjwon.foodchoice.dto.MenuDto;
import com.kjwon.foodchoice.errors.NotFoundKeywordException;
import com.kjwon.foodchoice.clazz.LatLongPosition;


import java.util.List;

public interface RestaurantInfo
{
    LatLongPosition getLatitudeLongitudeLocation(String name) throws NotFoundKeywordException;

    List<MenuDto> getMenus(int restaurantId);

    List<CommentDto> getComments(int restaurantId, int offset, int number);

    RestaurantDto getRestaurantDetailInfo(int restaurantId);
}
