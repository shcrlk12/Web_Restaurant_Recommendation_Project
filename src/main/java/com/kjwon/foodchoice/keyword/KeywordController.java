package com.kjwon.foodchoice.keyword;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/JSON/keyword")
@AllArgsConstructor
public class KeywordController {

    private final SearchKeywordServiceImpl searchKeywordService;
    @GetMapping
    public void Test(String keyword){
        searchKeywordService.searchKeyword(keyword);
    }
}
