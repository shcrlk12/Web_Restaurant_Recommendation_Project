package com.kjwon.foodchoice.security;

import com.kjwon.foodchoice.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.ClassUtils.isAssignable;

@AllArgsConstructor
public class KakaoLoginProvider  implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //kakao token 검증
        String kakaoAccessToken = (String)authentication.getCredentials();

        try {
            URL url = new URL("https://kapi.kakao.com/v2/user/me");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + kakaoAccessToken);
            connection.setDoOutput(true);
            //send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.close();


            //get response
            InputStream is = connection.getInputStream();
            //요청 결과 (response)를 BufferedReader로 받습니다.
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            // 자바 5 이상은 StringBuffer 를 이용해서 결과 값을 읽습니다.
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String jsonResponse = response.toString();

            String principal = (String)authentication.getPrincipal();
            String[] splitPrincipal = principal.split("-");

            if(jsonResponse.contains(splitPrincipal[1]) && jsonResponse.contains(splitPrincipal[0])){ //토큰 검증 완료료
                //1. DB에서 조회해서 맞는놈이 있으면 로그인
                //2. 맞는놈이 없으면 DB에 저장하고 로그인
                User user = (User)userService.kakaoLogin((String)authentication.getPrincipal());

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
                auth.setDetails(user);

                return auth;
            }
            return null;

       }catch (Exception e){
            return null;
        }


//        User user = (User)userService.findByUserId((String)authentication.getPrincipal());
//
//        UsernamePasswordAuthenticationToken auth;
//
//        if(user.getPassword().equals(String.valueOf(authentication.getCredentials()))) {
//            auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
//            auth.setDetails(user);
//        }
//        else{
//            throw new BadCredentialsException("not matched!");
//        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isAssignable(UsernamePasswordAuthenticationToken.class, authentication);
    }
}
