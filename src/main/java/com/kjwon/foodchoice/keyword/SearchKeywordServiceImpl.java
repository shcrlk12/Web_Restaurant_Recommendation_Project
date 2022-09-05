package com.kjwon.foodchoice.keyword;

import com.kjwon.foodchoice.mapper.RegisterKeyword;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchKeywordServiceImpl {

    private final RegisterKeyword registerKeyword;
    public void searchKeyword(String keyword){
        registerKeyword.findCompareKeyword(keyword+"%");
    }
}
