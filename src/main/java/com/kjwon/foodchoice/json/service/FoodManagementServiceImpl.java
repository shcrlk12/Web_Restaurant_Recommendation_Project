package com.kjwon.foodchoice.json.service;

import com.kjwon.foodchoice.json.dto.RestaurantDto;
import com.kjwon.foodchoice.json.exception.NotFoundKeywordException;
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
    public List<RestaurantOverview> findNearRestaurant(String location, int offset, int number) throws NotFoundKeywordException {
        LatLongPosition latLongPosition = this.findLocation(location);
        List<RestaurantDto> restaurantDtoList = foodMapper.findNearLocation(latLongPosition.getLatitude(), latLongPosition.getLongitude(), offset, number);

        List<RestaurantOverview> restaurantOverviewList = new ArrayList<>();
        TmPosition tmPosition = FunctionUtil.convertTmCoordinate(latLongPosition);

        for(RestaurantDto restaurantDto : restaurantDtoList){
            double distance = FunctionUtil.distance(tmPosition, new TmPosition(restaurantDto.getLatitude(), restaurantDto.getLongitude()));
            restaurantOverviewList.add(RestaurantDto.of(restaurantDto, String.valueOf(distance), location));
        }
        return restaurantOverviewList;
    }

    @Override
    public List<RestaurantOverview> findPopularRestaurant(String location, int offset, int number) throws NotFoundKeywordException {
        return null;
    }

    @Override
    public List<RestaurantOverview> findMostPopularRestaurant(int offset, int number) {
        return null;
    }

    @Override
    public LatLongPosition findLocation(String name) throws NotFoundKeywordException {
        Optional<LatLongPosition> optionalLatLongPosition = Optional.ofNullable(registerKeyword.findLocationByName(name));

        return optionalLatLongPosition.orElseThrow(NotFoundKeywordException::new);
    }
}
