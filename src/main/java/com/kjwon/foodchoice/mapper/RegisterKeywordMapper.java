package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.vo.RegisterKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterKeywordMapper {
    LatLongPosition findLocationByName(String name);

    List<RegisterKeyword> findCompareKeyword(String keyword);

}
