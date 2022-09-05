package com.kjwon.foodchoice.util;

import com.kjwon.foodchoice.clazz.LatLongPosition;
import com.kjwon.foodchoice.clazz.TmPosition;

import java.util.ArrayList;
import java.util.List;

public class FunctionUtil {
    static double westLongitude = 125;
    static double westLatitude = 34.5;
    static double earthRadius = 6367445.157;
    static double earthRound = 2 * Math.PI * earthRadius;

    public static double distance(TmPosition startPosition, TmPosition endPosition) {
        double x = startPosition.getX() - endPosition.getX();
        double y = startPosition.getY() - endPosition.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public static TmPosition convertTmCoordinate(LatLongPosition latLongPosition) {
        double latitude;
        double longitude;

        latitude = latLongPosition.getLatitude();
        longitude = latLongPosition.getLongitude();

        double radius = Math.cos(Math.toRadians(latitude)) * earthRadius;
        double round = 2 * Math.PI * radius;

        double y = round * (longitude - westLongitude) / 360;
        double x = earthRound * (latitude - westLatitude) / 360;

        return new TmPosition(x, y);
    }

    public static String getConsonantVowelInChar(String character) {
        final String[] f = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ",
                "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ",
                "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

        final String[] s = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ",
                "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ",
                "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
        final String[] t = {" ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ",
                "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ",
                "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ",
                "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
        final int ga = 44032;
        int uni = character.codePointAt(0);

        uni = uni - ga;

        int fn = uni / 588;
        int sn = (uni - (fn * 588)) / 28;
        int tn = uni % 28;

        return tn != 0 ? f[fn] + s[sn] + t[tn]
                : f[fn] + s[sn];
    }

    public static String getConsonantVowel(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            sb.append(getConsonantVowelInChar(String.valueOf(str.charAt(i))));
        }

        return sb.toString();

    }
}