package com.kjwon.foodchoice.json.mapper;

import com.kjwon.foodchoice.json.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FoodMapper {

    List<RestaurantDto> findByAll();

    List<RestaurantDto> findNearLocation(double latitude, double longitude, int offset, int number);
}
