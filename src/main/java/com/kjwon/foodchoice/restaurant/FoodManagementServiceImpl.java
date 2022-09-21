package com.kjwon.foodchoice.restaurant;

import com.kjwon.foodchoice.dto.CommentDto;
import com.kjwon.foodchoice.dto.MenuDto;
import com.kjwon.foodchoice.errors.NotFoundException;
import com.kjwon.foodchoice.mapper.CommentMapper;
import com.kjwon.foodchoice.mapper.RegisterKeywordMapper;
import com.kjwon.foodchoice.mapper.RestaurantMapper;
import com.kjwon.foodchoice.mapper.MenuMapper;
import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.clazz.TmPosition;
import com.kjwon.foodchoice.util.FunctionUtil;
import com.kjwon.foodchoice.vo.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodManagementServiceImpl implements RestaurantInfo, RestaurantSearch {
    private final RestaurantMapper restaurantMapper;
    private final RegisterKeywordMapper registerKeyword;

    private final MenuMapper menuMapper;

    private final CommentMapper commentMapper;
    @Override
    public List<RestaurantDto> findNearRestaurant(String location, int offset, int number) throws NotFoundException {
        LatLongPosition latLongPosition = this.getLatitudeLongitudeLocation(location);
        List<Restaurant> restaurantList = restaurantMapper.findNearLocation(
                latLongPosition.getLatitude(),
                latLongPosition.getLongitude(),
                offset,
                number);

        List<RestaurantDto> restaurantOverviewDtoList = new ArrayList<>();
        TmPosition tmPosition = FunctionUtil.convertTmCoordinate(latLongPosition);
        for(Restaurant restaurant : restaurantList){

            double distance = FunctionUtil.distance(tmPosition,
                    FunctionUtil.convertTmCoordinate(new LatLongPosition(restaurant.getLatitude(), restaurant.getLongitude())));

            restaurantOverviewDtoList.add(RestaurantDto.of(restaurant, String.valueOf((int)distance), location));
        }
        return restaurantOverviewDtoList;
    }

    @Override
    public List<RestaurantDto> findPopularRestaurant(String location, int offset, int number) throws NotFoundException {
        return null;
    }

    @Override
    public List<RestaurantDto> findMostPopularRestaurant(int offset, int number) {
        List<Restaurant> restaurantList = restaurantMapper.findMostLike(offset, number);
        List<RestaurantDto> restaurantOverviewList = new ArrayList<>();

        for(Restaurant restaurant : restaurantList){
            restaurantOverviewList.add(RestaurantDto.of(restaurant));
        }

        return restaurantOverviewList;
    }

    @Override
    public LatLongPosition getLatitudeLongitudeLocation(String name) throws NotFoundException {
        Optional<LatLongPosition> optionalLatLongPosition = Optional.ofNullable(registerKeyword.findLocationByName(name));

        return optionalLatLongPosition.orElseThrow(() -> new NotFoundException("keyword not found"));
    }

    @Override
    public List<MenuDto> getMenus(int restaurantId) {
        List<MenuDto> menuDtoList = menuMapper.findMenu(restaurantId);
        return MenuDto.of(menuDtoList);
    }

    @Override
    public List<CommentDto> getComments(int restaurantId, int offset, int number) {

        return CommentDto.of(commentMapper.findCommentsByRestaurantId(restaurantId, offset, number));
    }

    @Override
    public RestaurantDto getRestaurantDetailInfo(int restaurantId) {

        return RestaurantDto.of(restaurantMapper.findById(restaurantId));
    }
}
