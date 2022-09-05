package com.kjwon.foodchoice.restaurant;

import com.kjwon.foodchoice.dto.CommentDto;
import com.kjwon.foodchoice.dto.MenuDto;
import com.kjwon.foodchoice.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.kjwon.foodchoice.util.ApiUtils.*;

import static com.kjwon.foodchoice.util.ApiUtils.success;

@RestController()
@RequestMapping("api/JSON/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final FoodManagementServiceImpl foodManagementService;

    @GetMapping("overview")
    public ApiResult<List<RestaurantDto>> getRestaurantOverviewList(Optional<String> location, Optional<String> classificationType
            , Optional<Integer> offset, Optional<Integer> number) throws NotFoundException {

        List<RestaurantDto> restaurantOverviewList = Collections.emptyList();

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

    @GetMapping("getMenus")
    public ApiResult<List<MenuDto>> getMenus(Optional<Integer> restaurantId){
        List<MenuDto> menuList = null;

        menuList = foodManagementService.getMenus(restaurantId.get());

        return success(menuList);
    }

    @GetMapping("getComments")
    public ApiResult<List<CommentDto>> getComments(Optional<Integer> restaurantId, Optional<Integer> offset, Optional<Integer> number){
        List<CommentDto> menuList = null;

        menuList = foodManagementService.getComments(restaurantId.get(), offset.get(), number.get());

        return success(menuList);
    }

    @GetMapping("detail")
    public ApiResult<RestaurantDto> getRestaurantDetail(Optional<Integer> restaurantId){
        RestaurantDto menuList = null;

        menuList = foodManagementService.getRestaurantDetailInfo(restaurantId.get());

        return success(menuList);
    }
}
