package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterKeyword {
    LatLongPosition findLocationByName(String name);
}
