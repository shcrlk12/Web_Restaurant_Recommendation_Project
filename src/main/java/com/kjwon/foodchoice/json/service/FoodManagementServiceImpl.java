package com.kjwon.foodchoice.json.service;

import com.kjwon.foodchoice.json.dto.RestaurantDto;
import com.kjwon.foodchoice.json.errors.NotFoundException;
import com.kjwon.foodchoice.json.errors.NotFoundKeywordException;
import com.kjwon.foodchoice.json.mapper.FoodMapper;
import com.kjwon.foodchoice.json.mapper.RegisterKeyword;
import com.kjwon.foodchoice.json.model.LatLongPosition;
import com.kjwon.foodchoice.json.model.RestaurantOverview;
import com.kjwon.foodchoice.json.model.TmPosition;
import com.kjwon.foodchoice.json.util.FunctionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodManagementServiceImpl implements RestaurantSearch{
    private final FoodMapper foodMapper;
    private final RegisterKeyword registerKeyword;

    @Override
    public List<RestaurantOverview> findNearRestaurant(String location, int offset, int number) throws NotFoundException {
        LatLongPosition latLongPosition = this.findLocation(location);
        List<RestaurantDto> restaurantDtoList = foodMapper.findNearLocation(latLongPosition.getLatitude(), latLongPosition.getLongitude(), offset, number);

        List<RestaurantOverview> restaurantOverviewList = new ArrayList<>();
        TmPosition tmPosition = FunctionUtil.convertTmCoordinate(latLongPosition);
        for(RestaurantDto restaurantDto : restaurantDtoList){

            double distance = FunctionUtil.distance(tmPosition,
                    FunctionUtil.convertTmCoordinate(new LatLongPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude())));

            restaurantOverviewList.add(RestaurantDto.of(restaurantDto, String.valueOf((int)distance), location));
        }
        return restaurantOverviewList;
    }

    @Override
    public List<RestaurantOverview> findPopularRestaurant(String location, int offset, int number) throws NotFoundException {
        return null;
    }

    @Override
    public List<RestaurantOverview> findMostPopularRestaurant(int offset, int number) {
        List<RestaurantDto> restaurantDtoList = foodMapper.findMostLike(offset, number);
        List<RestaurantOverview> restaurantOverviewList = new ArrayList<>();

        for(RestaurantDto restaurantDto : restaurantDtoList){
            restaurantOverviewList.add(RestaurantDto.of(restaurantDto));
        }

        return restaurantOverviewList;
    }

    @Override
    public LatLongPosition findLocation(String name) throws NotFoundException {
        Optional<LatLongPosition> optionalLatLongPosition = Optional.ofNullable(registerKeyword.findLocationByName(name));

        return optionalLatLongPosition.orElseThrow(() -> new NotFoundException("keyword not found"));
    }
}
