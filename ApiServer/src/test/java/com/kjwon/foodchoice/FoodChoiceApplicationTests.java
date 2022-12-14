package com.kjwon.foodchoice;

import com.kjwon.foodchoice.keyword.KeywordController;
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
    @DisplayName("?????? overview ????????? ??????")
    void getRestaurantOverviewListTest1() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant/overview")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "????????????")
                        .param("classificationType", "distance")
                        .param("offset", "0")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response[0].name").value(equalTo("????????? ????????????")))
                .andExpect(jsonPath("$.response[0].distance").value(equalTo("???????????? 2102m")))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("???????????????")))
                .andExpect(jsonPath("$.response[1].distance").value(equalTo("???????????? 2155m")))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("?????????(894)@")))
                .andExpect(jsonPath("$.response[2].distance").value(equalTo("???????????? 3363m")))
        ;
    }

    @Test
    @DisplayName("location ?????? ???????????? 404 Exception")
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
    @DisplayName("location ?????? null?????? ?????? like??? ?????? ?????? ????????? ??????")
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
                .andExpect(jsonPath("$.response[0].name").value(equalTo("??????????????? ?????????&???????????? ???????????????")))
                .andExpect(jsonPath("$.response[0].like").value(equalTo(185)))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("???????????????")))
                .andExpect(jsonPath("$.response[1].like").value(equalTo(165)))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("????????? ??????????????????")))
                .andExpect(jsonPath("$.response[2].like").value(equalTo(156)))
        ;
    }

    @Test
    @DisplayName("location ?????? ???????????? ?????? ????????? error ????????? ??????")
    void getRestaurantOverviewListTest7() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "????????? ?????????.")
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
    @DisplayName("offset ?????? null?????? offset??? 0?????? ?????? ??????")
    void getRestaurantOverviewListTest4() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("classificationType", "distance")
                        .param("location", "?????????")
                        .param("number", "3")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurantOverviewList"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response[0].name").value(equalTo("100??? ?????????")))
                .andExpect(jsonPath("$.response[0].info").value(equalTo("????????? 5393m")))
                .andExpect(jsonPath("$.response[1].name").value(equalTo("????????????")))
                .andExpect(jsonPath("$.response[1].info").value(equalTo("????????? 5803m")))
                .andExpect(jsonPath("$.response[2].name").value(equalTo("???????????? ??????5??????")))
                .andExpect(jsonPath("$.response[2].info").value(equalTo("????????? 6392m")))
        ;
    }

    @Test
    @DisplayName("number ?????? null?????? NotFound Exception ??????")
    void getRestaurantOverviewListTest5() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("classificationType", "distance")
                        .param("location", "?????????")
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
    @DisplayName("popular ?????? ?????????????????? ???????????? // ?????? ????????? ???????????? ????????????.")
    void getRestaurantOverviewListTest6() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/restaurant")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("location", "?????????")
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
    @DisplayName("popular ?????? ???????????? // ?????? ????????? ???????????? ????????????.")
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
    @DisplayName("Location ???????????? DB?????? ??????, ????????? ??????.")
    public void findLocationTest1() throws Exception {
        LatLongPosition latLongPosition = foodManagementService.getLatitudeLongitudeLocation("?????????");

        assertThat(latLongPosition.getLatitude())
                .isEqualTo(37.626453);

        assertThat(latLongPosition.getLongitude())
                .isEqualTo(127.026119);
    }

    @Test
    @DisplayName("Location ????????? ????????? ??????.")
    public void findLocationTest2() {
        try {
            LatLongPosition latLongPosition = foodManagementService.getLatitudeLongitudeLocation("??????????????????.");
        }catch (Exception e){
            assertThat(0).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("keyword ?????? ???, ????????? ?????? ?????? 3???")
    public void findNearRestaurantTest1() throws NotFoundKeywordException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("?????????",0,3);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(3);

        assertThat(restaurantOverviewList.get(0).getName())
                .isEqualTo("100??? ?????????");

        assertThat(restaurantOverviewList.get(1).getName())
                .isEqualTo("????????????");

        assertThat(restaurantOverviewList.get(2).getName())
                .isEqualTo("???????????? ??????5??????");
    }

    @Test
    @DisplayName("keyword ?????? ???, ????????? ?????? ?????? 3???, offset 3")
    public void findNearRestaurantTest2() throws NotFoundKeywordException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("?????????",3,3);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(3);

        assertThat(restaurantOverviewList.get(0).getName())
                .isEqualTo("????????? ??????????????????");

        assertThat(restaurantOverviewList.get(1).getName())
                .isEqualTo("??? ?????????");

        assertThat(restaurantOverviewList.get(2).getName())
                .isEqualTo("?????????????????? ?????????");
    }

    @Test
    @DisplayName("keyword ??? ???????????? ?????? ?????????")
    public void findNearRestaurantTest3(){
        try {
            foodManagementService.findNearRestaurant("??????????????????", 0, 0);
        }catch (NotFoundException e){
            assertThat(0)
                    .isEqualTo(0);
        }
    }

    @Test
    @DisplayName("offset??? ?????? ????????? ????????? ?????????")
    public void findNearRestaurantTest4() throws NotFoundException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("?????????", 99999999, 0);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("number??? ?????? 0??????")
    public void findNearRestaurantTest5() throws NotFoundException {
        List<RestaurantDto> restaurantOverviewList = foodManagementService.findNearRestaurant("?????????", 0, 0);

        assertThat(restaurantOverviewList.size())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("?????? ???????????? ??????????????? ?????? ????????????")
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
            .andExpect(jsonPath("$.response[0].name", is("????????????")))
            .andExpect(jsonPath("$.response[0].price", is(7000)))
            .andExpect(jsonPath("$.response[0].likesNumber", is(15)))
            .andExpect(jsonPath("$.response[1].name", is("??????")))
            .andExpect(jsonPath("$.response[1].price", is(3000)))
            .andExpect(jsonPath("$.response[1].likesNumber", is(11)))
            .andExpect(jsonPath("$.response[2].name", is("?????????")))
            .andExpect(jsonPath("$.response[2].price", is(4000)))
            .andExpect(jsonPath("$.response[2].likesNumber", is(8)))
            .andExpect(jsonPath("$.response[3].name", is("??????")))
            .andExpect(jsonPath("$.response[3].price", is(6500)))
            .andExpect(jsonPath("$.response[3].likesNumber", is(5)))
            .andExpect(jsonPath("$.response[4].name", is("?????????")))
            .andExpect(jsonPath("$.response[4].price", is(6000)))
            .andExpect(jsonPath("$.response[4].likesNumber", is(3)))
        ;
    }

    @Test
    @DisplayName("?????? ???????????? ??????????????? ????????? ????????????")
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
                .andExpect(jsonPath("$.response[0].content", is("??????????????? ?????????2")))
                .andExpect(jsonPath("$.response[0].likesNumber", is(2)))
                .andExpect(jsonPath("$.response[0].registerDate", is("2022-09-01T01:20:06")))
                .andExpect(jsonPath("$.response[0].disable", is(false)))
                .andExpect(jsonPath("$.response[0].userId", is("soun@naver.com")))
                .andExpect(jsonPath("$.response[0].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[0].commentId", is(1)))
                .andExpect(jsonPath("$.response[1].content", is("??????????????? ?????????3")))
                .andExpect(jsonPath("$.response[1].likesNumber", is(3)))
                .andExpect(jsonPath("$.response[1].registerDate", is("2022-09-01T01:25:06")))
                .andExpect(jsonPath("$.response[1].disable", is(true)))
                .andExpect(jsonPath("$.response[1].userId", is("shcrlk12@naver.com")))
                .andExpect(jsonPath("$.response[1].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[1].commentId", is(2)))
                .andExpect(jsonPath("$.response[2].content", is("??????????????? ?????????4")))
                .andExpect(jsonPath("$.response[2].likesNumber", is(4)))
                .andExpect(jsonPath("$.response[2].registerDate", is("2022-09-01T01:35:06")))
                .andExpect(jsonPath("$.response[2].disable", is(false)))
                .andExpect(jsonPath("$.response[2].userId", is("shcrlk12@naver.com")))
                .andExpect(jsonPath("$.response[2].restaurantId", is(14)))
                .andExpect(jsonPath("$.response[2].commentId", is(0)))
        ;
    }

    @Test
    @DisplayName("?????? ???????????? ??????????????? description ????????????")
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
                .andExpect(jsonPath("$.response.description", is("?????? ?????? ?????? ??????14")))
        ;
    }

    @Test
    @DisplayName("keyword ??????")
    void getSimilarKeywordTest() throws Exception{
        ResultActions result = mockMvc.perform(
                get("/api/JSON/keyword")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("keyword", "???")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(KeywordController.class))
                .andExpect(handler().methodName("getSimilarKeyword"))
                .andExpect(jsonPath("$.success", is(true)));
    }
}