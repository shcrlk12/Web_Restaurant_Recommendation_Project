package com.kjwon.foodchoice.json.controller;

import com.kjwon.foodchoice.json.errors.NotFoundException;
import com.kjwon.foodchoice.json.errors.NotFoundKeywordException;
import com.kjwon.foodchoice.json.model.RestaurantOverview;
import com.kjwon.foodchoice.json.service.FoodManagementServiceImpl;
import com.kjwon.foodchoice.json.util.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.kjwon.foodchoice.json.util.ApiUtils.*;

import static com.kjwon.foodchoice.json.util.ApiUtils.success;

@RestController()
@RequestMapping("api/JSON/")
@AllArgsConstructor
public class JsonController {

    private final FoodManagementServiceImpl foodManagementService;

    @GetMapping("foodOverviewList")
    public ApiResult<List<RestaurantOverview>> getRestaurantOverviewList(Optional<String> location, Optional<String> classificationType
            , Optional<Integer> offset, Optional<Integer> number) throws NotFoundException {

        List<RestaurantOverview> restaurantOverviewList = Collections.emptyList();

        String type = classificationType.orElse("popular");

        if(!number.isPresent())
            throw new NotFoundException("read number is empty");

        if(location.isPresent()) {
            if (type.equals("distance")) {
                restaurantOverviewList = foodManagementService.findNearRestaurant(location.orElse(""), offset.orElse(0), number.orElse(10));
            } else if (type.equals("popular")) {
                restaurantOverviewList = foodManagementService.findPopularRestaurant(location.orElse(""), offset.orElse(0), number.orElse(10));
            }
        }else{
            restaurantOverviewList = foodManagementService.findMostPopularRestaurant(offset.orElse(0), number.orElse(10));
        }



        return success(restaurantOverviewList);
    }
}
