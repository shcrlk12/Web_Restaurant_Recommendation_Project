package com.kjwon.foodchoice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    long id;
    String name;
    int price;
    int likesNumber;
    int foodId;

    public static MenuDto of(MenuDto menuDto){
        return MenuDto.builder()
                .id(menuDto.getId())
                .name(menuDto.getName())
                .price(menuDto.getPrice())
                .likesNumber(menuDto.getLikesNumber())
                .foodId(menuDto.getFoodId())
                .build();
    }

    public static List<MenuDto> of(List<MenuDto> menuDtoList){
        List<MenuDto> MenuList = new ArrayList<>();
        for(MenuDto menuDto : menuDtoList){
            MenuList.add(MenuDto.of(menuDto));
        }
        return MenuList;
    }
}
