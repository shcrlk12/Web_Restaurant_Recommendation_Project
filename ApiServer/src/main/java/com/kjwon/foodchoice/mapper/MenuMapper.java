package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDto> findMenu(int restaurantId);
}
