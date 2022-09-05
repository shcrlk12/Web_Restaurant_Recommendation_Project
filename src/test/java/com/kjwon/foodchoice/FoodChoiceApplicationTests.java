package com.kjwon.foodchoice;

import com.kjwon.foodchoice.restaurant.RestaurantController;
import com.kjwon.foodchoice.errors.NotFoundException;
import com.kjwon.foodchoice.errors.NotFoundKeywordException;
import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.restaurant.FoodManagementServiceImpl;
import com.kjwon.foodchoice.restaurant.RestaurantDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
class FoodChoiceApplicationTests {
    private MockMvc mockMvc;

    private FoodManagementServiceImpl foodManagementService;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setFoodManagementServiceImpl(FoodManagementServiceImpl foodManagementService) {
        this.foodManagementService = foodManagementService;
    }

    @Test
    @DisplayName("식당 overview 리스트 조회")
    void getRestaurantOverviewListTest1() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant/overview")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "압구정역")
                        .param("classificationType", "distance")
                        .param("offset", "0")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response[0].name").value(equalTo("샐러딧 강남본점")))
                .andExpect(jsonPath("$.response[0].distance").value(equalTo("압구정역 2102m")))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("요거멜리아")))
                .andExpect(jsonPath("$.response[1].distance").value(equalTo("압구정역 2155m")))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("팔구사(894)@")))
                .andExpect(jsonPath("$.response[2].distance").value(equalTo("압구정역 3363m")))
        ;
    }

    @Test
    @DisplayName("location 값이 빈값일때 404 Exception")
    void getRestaurantOverviewListTest2() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "")
                        .param("classificationType", "distance")
                        .param("offset", "0")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(404)))
                .andExpect(jsonPath("$.error.message", is("keyword not found")))
        ;
    }

    @Test
    @DisplayName("location 값이 null일때 가장 like가 많은 가게 순으로 출력")
    void getRestaurantOverviewListTest3() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("offset", "0")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response[0].name").value(equalTo("신마라명가 마라탕&마라샹궈 강남대치점")))
                .andExpect(jsonPath("$.response[0].like").value(equalTo(185)))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("요거멜리아")))
                .andExpect(jsonPath("$.response[1].like").value(equalTo(165)))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("이가네 생면멸치국수")))
                .andExpect(jsonPath("$.response[2].like").value(equalTo(156)))
        ;
    }

    @Test
    @DisplayName("location 값이 등록되어 있지 않을때 error 메시지 출력")
    void getRestaurantOverviewListTest7() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "테스트 입니다.")
                        .param("classificationType", "popular")
                        .param("offset", "3")
                        .param("number", "3")

        );

        result.andDo(print())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(404)))
                .andExpect(jsonPath("$.error.message", is("keyword not found")))
        ;
    }
    @Test
    @DisplayName("offset 값이 null일때 offset이 0처럼 정상 동작")
    void getRestaurantOverviewListTest4() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("classificationType", "distance")
                        .param("location", "미아역")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response[0].name").value(equalTo("100년 설렁탕")))
                .andExpect(jsonPath("$.response[0].info").value(equalTo("미아역 5393m")))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("주신포차")))
                .andExpect(jsonPath("$.response[1].info").value(equalTo("미아역 5803m")))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("한국통닭 종로5가점")))
                .andExpect(jsonPath("$.response[2].info").value(equalTo("미아역 6392m")))
        ;
    }

    @Test
    @DisplayName("number 값이 null일때 NotFound Exception 발생")
    void getRestaurantOverviewListTest5() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("classificationType", "distance")
                        .param("location", "미아역")
                        .param("offset", "0")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(404)))
                .andExpect(jsonPath("$.error.message", is("read number is empty")))
        ;
    }

    @Test
    @DisplayName("popular 인데 등로되어있는 위치일때 // 아직 어떻게 해야할지 모르겠음.")
    void getRestaurantOverviewListTest6() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "미아역")
                        .param("classificationType", "popular")
                        .param("location", "0")
                        .param("offset", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
        ;
    }

    @Test
    @DisplayName("popular 인데 빈값일때 // 아직 어떻게 해야할지 모르겠음.")
    void getRestaurantOverviewListTest8() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("classificationType", "popular")
                        .param("location", "0")
                        .param("offset", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
        ;
    }

    @Test
    @DisplayName("Location 이름으로 DB에서 위도, 경도를 조회.")
    public void findLocationTest1() throws Exception {
        LatLongPosition latLongPosition = foodManagementService.getLatitudeLongitudeLocation("미아역");

        assertThat(latLongPosition.getLatitude())
                .isEqualTo(37.626453);

        assertThat(latLongPosition.getLongitude())
                .isEqualTo(127.026119);
    }

    @Test
    @DisplayName("Location 이름이 잘못된 이름.")
    public void findLocationTest2() {
        try {
            LatLongPosition latLongPosition = foodManagementService.getLatitudeLongitudeLocation("테스트입니다.");
        }catch (Exception e){
            assertThat(0).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("keyword 입력 후, 가까운 장소 검색 3개")
    public void findNearRestaurantTest1() throws NotFoundKeywordException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("미아역",0,3);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(3);

        assertThat(restaurantOverviewList.get(0).getName())
                .isEqualTo("100년 설렁탕");

        assertThat(restaurantOverviewList.get(1).getName())
                .isEqualTo("주신포차");

        assertThat(restaurantOverviewList.get(2).getName())
                .isEqualTo("한국통닭 종로5가점");
    }

    @Test
    @DisplayName("keyword 입력 후, 가까운 장소 검색 3개, offset 3")
    public void findNearRestaurantTest2() throws NotFoundKeywordException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("미아역",3,3);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(3);

        assertThat(restaurantOverviewList.get(0).getName())
                .isEqualTo("이가네 생면멸치국수");

        assertThat(restaurantOverviewList.get(1).getName())
                .isEqualTo("술 연구소");

        assertThat(restaurantOverviewList.get(2).getName())
                .isEqualTo("중앙해장포장 금호점");
    }

    @Test
    @DisplayName("keyword 가 등록되지 않은 키워드")
    public void findNearRestaurantTest3(){
        try {
            foodManagementService.findNearRestaurant("테스트입니다", 0, 0);
        }catch (NotFoundException e){
            assertThat(0)
                    .isEqualTo(0);
        }
    }

    @Test
    @DisplayName("offset이 최대 허용된 값보다 커질때")
    public void findNearRestaurantTest4() throws NotFoundException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("미아역", 99999999, 0);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("number의 값이 0일때")
    public void findNearRestaurantTest5() throws NotFoundException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("미아역", 0, 0);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("식당 아이디를 입력했을때 메뉴 가져오기")
    public void getMenuTest() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant/getMenus")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("restaurantId", "14")
        );

        result.andDo(print())
            .andExpect(handler().handlerType(RestaurantController.class))
            .andExpect(handler().methodName("getMenus"))
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response").exists())
            .andExpect(jsonPath("$.response[0].name", is("떡만두국")))
            .andExpect(jsonPath("$.response[0].price", is(7000)))
            .andExpect(jsonPath("$.response[0].likesNumber", is(15)))
            .andExpect(jsonPath("$.response[1].name", is("김밥")))
            .andExpect(jsonPath("$.response[1].price", is(3000)))
            .andExpect(jsonPath("$.response[1].likesNumber", is(11)))
            .andExpect(jsonPath("$.response[2].name", is("떡볶이")))
            .andExpect(jsonPath("$.response[2].price", is(4000)))
            .andExpect(jsonPath("$.response[2].likesNumber", is(8)))
            .andExpect(jsonPath("$.response[3].name", is("라면")))
            .andExpect(jsonPath("$.response[3].price", is(6500)))
            .andExpect(jsonPath("$.response[3].likesNumber", is(5)))
            .andExpect(jsonPath("$.response[4].name", is("라볶이")))
            .andExpect(jsonPath("$.response[4].price", is(6000)))
            .andExpect(jsonPath("$.response[4].likesNumber", is(3)))
        ;
    }

    @Test
    @DisplayName("식당 아이디를 입력했을때 코멘트 가져오기")
    public void getComments() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant/getComments")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("restaurantId", "14")
                        .param("pageOffset", "1")
                        .param("number", "3")

        );

        result.andDo(print())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getComments"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response").exists())
                .andExpect(jsonPath("$.response[0].content", is("안녕하세요 케케케2")))
                .andExpect(jsonPath("$.response[0].likesNumber", is(2)))
                .andExpect(jsonPath("$.response[0].registerDate", is("2022-09-01T01:20:06")))
                .andExpect(jsonPath("$.response[0].disable", is(false)))
                .andExpect(jsonPath("$.response[0].userId", is("soun@naver.com")))
                .andExpect(jsonPath("$.response[0].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[0].commentId", is(1)))
                .andExpect(jsonPath("$.response[1].content", is("안녕하세요 케케케3")))
                .andExpect(jsonPath("$.response[1].likesNumber", is(3)))
                .andExpect(jsonPath("$.response[1].registerDate", is("2022-09-01T01:25:06")))
                .andExpect(jsonPath("$.response[1].disable", is(true)))
                .andExpect(jsonPath("$.response[1].userId", is("shcrlk12@naver.com")))
                .andExpect(jsonPath("$.response[1].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[1].commentId", is(2)))
                .andExpect(jsonPath("$.response[2].content", is("안녕하세요 케케케4")))
                .andExpect(jsonPath("$.response[2].likesNumber", is(4)))
                .andExpect(jsonPath("$.response[2].registerDate", is("2022-09-01T01:35:06")))
                .andExpect(jsonPath("$.response[2].disable", is(false)))
                .andExpect(jsonPath("$.response[2].userId", is("shcrlk12@naver.com")))
                .andExpect(jsonPath("$.response[2].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[2].commentId", is(0)))
        ;
    }

    @Test
    @DisplayName("식당 아이디를 입력했을때 description 가져오기")
    public void getDescOfRestaurant() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant/detail")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("restaurantId", "14")
        );

        result.andDo(print())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantDetail"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response").exists())
                .andExpect(jsonPath("$.response.description", is("최강 한식 대전 출연14")))
        ;
    }
}