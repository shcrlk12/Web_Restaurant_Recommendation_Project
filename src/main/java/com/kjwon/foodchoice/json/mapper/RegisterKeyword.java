package com.kjwon.foodchoice.json.mapper;

import com.kjwon.foodchoice.json.model.LatLongPosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterKeyword {
    LatLongPosition findLocationByName(String name);
}
