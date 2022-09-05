package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterKeyword {
    LatLongPosition findLocationByName(String name);

    List<String> findCompareKeyword(String keyword);

}
