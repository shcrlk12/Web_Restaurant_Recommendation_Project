package com.kjwon.foodchoice.keyword;

import com.kjwon.foodchoice.dto.ResisterKeywordDto;
import com.kjwon.foodchoice.restaurant.RestaurantDto;
import com.kjwon.foodchoice.util.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kjwon.foodchoice.util.ApiUtils.*;
import java.util.List;

import static com.kjwon.foodchoice.util.ApiUtils.success;

@RestController
@RequestMapping("api/JSON/keyword")
@AllArgsConstructor
public class KeywordController {

    private final SearchKeywordServiceImpl searchKeywordService;
    @GetMapping
    public ApiResult<List<ResisterKeywordDto>> getSimilarKeyword(String keyword){
        List<ResisterKeywordDto> resisterKeywordDtoList= searchKeywordService.searchKeyword(keyword);

        return success(resisterKeywordDtoList);
    }
}
