package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.vo.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    Restaurant findById(long id);

    List<Restaurant> findNearLocation(String city, String smallCity, double latitude, double longitude, int offset, int number);

    List<Restaurant> findMostLike(int offset, int number);

}
