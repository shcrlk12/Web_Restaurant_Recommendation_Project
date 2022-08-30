package com.kjwon.foodchoice.json.controller;

import com.kjwon.foodchoice.json.dto.RestaurantDto;
import com.kjwon.foodchoice.json.exception.NotFoundKeywordException;
import com.kjwon.foodchoice.json.model.LatLongPosition;
import com.kjwon.foodchoice.json.model.RestaurantOverview;
import com.kjwon.foodchoice.json.service.FoodManagementServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/JSON/")
@AllArgsConstructor
public class JsonController {

    private final FoodManagementServiceImpl foodManagementService;

    @GetMapping("foodOverviewList")
    public List<RestaurantOverview> getRestaurantOverviewList(Optional<String> location, Optional<String> classificationType
            , Optional<Integer> offset, Optional<Integer> number) throws NotFoundKeywordException {

        List<RestaurantOverview> restaurantOverviewList = Collections.emptyList();

        String type = classificationType.orElse("popular");

        try {
            if (type.equals("distance")) {
                restaurantOverviewList = foodManagementService.findNearRestaurant(location.orElse(""), offset.orElse(0), number.orElse(10));
            } else if (type.equals("popular")) {
                restaurantOverviewList = foodManagementService.findPopularRestaurant(location.orElse(""), offset.orElse(0), number.orElse(10));
            }
        }catch (NotFoundKeywordException e){
            restaurantOverviewList = foodManagementService.findMostPopularRestaurant(offset.orElse(0), number.orElse(10));
        }

        return restaurantOverviewList;
    }
}
