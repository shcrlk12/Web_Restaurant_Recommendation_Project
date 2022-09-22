package com.kjwon.foodchoice.dto;

import com.kjwon.foodchoice.vo.RegisterKeyword;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResisterKeywordDto {
    long id;
    String name;
    String type;
    double latitude;
    double longitude;

    public static ResisterKeywordDto of(RegisterKeyword resisterKeyword){
        return ResisterKeywordDto.builder()
                .id(resisterKeyword.getId())
                .name(resisterKeyword.getName())
                .type(resisterKeyword.getType())
                .latitude(resisterKeyword.getLatitude())
                .longitude(resisterKeyword.getLongitude())
                .build();
    }

    public static List<ResisterKeywordDto> of(List<RegisterKeyword> resisterKeywordList){
        List<ResisterKeywordDto> resisterKeywordDtoList = new ArrayList<>();
        for(RegisterKeyword resisterKeyword : resisterKeywordList){
            resisterKeywordDtoList.add(ResisterKeywordDto.of(resisterKeyword));
        }
        return resisterKeywordDtoList;
    }

}
