package com.kjwon.foodchoice.keyword;

import com.kjwon.foodchoice.dto.ResisterKeywordDto;
import com.kjwon.foodchoice.mapper.RegisterKeywordMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchKeywordServiceImpl {

    private final RegisterKeywordMapper registerKeyword;
    public List<ResisterKeywordDto> searchKeyword(String keyword){
        return ResisterKeywordDto.of(registerKeyword.findCompareKeyword(keyword+"%"));
    }
}
