package com.kjwon.foodchoice.util;


import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.clazz.TmPosition;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilTest {
    /*
     * expect x : 183,369.21868530782
     * expect y : 237,803.18524851395700133558433822
     * */
    @Test
    @DisplayName("두 지점간 거리 계산(m)")
    public void distanceBetweenTwoPoint(){

        TmPosition startPoint = FunctionUtil.convertTmCoordinate(new LatLongPosition(37.626453, 127.026119));
        TmPosition endPoint = FunctionUtil.convertTmCoordinate(new LatLongPosition(37.5818196,127.0008454));

        double distance = FunctionUtil.distance(startPoint, endPoint);

        assertThat(distance)
                .isGreaterThanOrEqualTo(5393)
                .isLessThanOrEqualTo(5394);

    }

    @Test
    @DisplayName("위도 대비 경도 1도 거리 계산 테스트")
    public void convertTmCoordinate(){
        TmPosition tmPosition = FunctionUtil.convertTmCoordinate(new LatLongPosition(36.15, 127.65));

        //+- 100 m 까지 허용
        assertThat(tmPosition.getX())
                .isGreaterThanOrEqualTo(183269.218)
                .isLessThanOrEqualTo(183469.218);

        System.out.println("tmPosition X Test Complete!, expect : 183,369.21868530782" +
                ", real : " + tmPosition.getX());

        assertThat(tmPosition.getY())
                .isGreaterThanOrEqualTo(237703.185)
                .isLessThanOrEqualTo(237903.185);

        System.out.println("tmPosition Y Test Complete!, expect : 237,803.18524851395" +
                ", real : " + tmPosition.getY());
    }

    @Test
    @DisplayName("자음 모음 분리 테스트")
    public void getConsonantVowel1(){
        String consonantVowel =  FunctionUtil.getConsonantVowelInChar("뷁");
        
        assertThat(consonantVowel)
                .isEqualTo("ㅂㅞㄺ");
    }

    @Test
    @DisplayName("문자열 자음 모음 분리 테스트")
    public void getConsonantVowel2(){
        String consonantVowel =  FunctionUtil.getConsonantVowel("압구정역");

        assertThat(consonantVowel)
                .isEqualTo("ㅇㅏㅂㄱㅜㅈㅓㅇㅇㅕㄱ");
    }
    
//    @Test
//    @DisplayName("위도 대비 경도 2도 거리 계산 테스트")
//    public void longitudeDistanceTest2(){
//        double distance = FunctionUtil.longitudeDistance(new RestaurantOverview(36, 125.1515),
//                new RestaurantOverview(36, 127.1515));
//
//        //+- 100 m 까지 허용
//        assertThat(distance)
//                .isGreaterThanOrEqualTo(2*89808.372)
//                .isLessThanOrEqualTo(2*89008.372);
//    }
//
//    @Test
//    @DisplayName("위도 대비 경도 3도 거리 계산 테스트")
//    public void longitudeDistanceTest3(){
//        double distance = FunctionUtil.longitudeDistance(new RestaurantOverview(36, 124.1515),
//                new RestaurantOverview(36, 127.1515));
//
//        //+- 100 m 까지 허용
//        assertThat(distance)
//                .isGreaterThanOrEqualTo(3*89808.372)
//                .isLessThanOrEqualTo(3*89008.372);
//    }

//    /*
//     * 2 * pi * 지구의 반지름(6,367,445.157) = 지구의 둘레
//     * 지구의 둘레 * (위도 - 서부 원점 위도(34.5) / 360)
//     * expect : 111,132.86003408178194444444444444 m
//     * */
//    @Test
//    @DisplayName("위도 1도 거리 계산 테스트")
//    public void latitudeDistanceTest1(){
//        double distance = FunctionUtil.latitudeDistance(new RestaurantOverview(36.1515, 0),
//                new RestaurantOverview(37.1515, 0));
//
//        //+- 100 m 까지 허용
//        assertThat(distance)
//                .isGreaterThanOrEqualTo(111032.860)
//                .isLessThanOrEqualTo(111232.860);
//    }
//
//    @Test
//    @DisplayName("위도 2도 거리 계산 테스트")
//    public void latitudeDistanceTest2(){
//        double distance = FunctionUtil.latitudeDistance(new RestaurantOverview(35.1515, 0),
//                new RestaurantOverview(37.1515, 0));
//
//        //+- 100 m 까지 허용
//        assertThat(distance)
//                .isGreaterThanOrEqualTo(2*111032.860)
//                .isLessThanOrEqualTo(2*111232.860);
//    }
//
//    @Test
//    @DisplayName("위도 3도 거리 계산 테스트")
//    public void latitudeDistanceTest3(){
//        double distance = FunctionUtil.latitudeDistance(new RestaurantOverview(34.1515, 0),
//                new RestaurantOverview(37.1515, 0));
//
//        //+- 100 m 까지 허용
//        assertThat(distance)
//                .isGreaterThanOrEqualTo(3*111032.860)
//                .isLessThanOrEqualTo(3*111232.860);
//    }
}
