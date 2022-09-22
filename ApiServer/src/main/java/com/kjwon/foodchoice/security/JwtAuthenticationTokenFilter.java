package com.kjwon.foodchoice.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class JwtAuthenticationTokenFilter extends GenericFilterBean {

//    private final Jwt jwt;

    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);

//    public JwtAuthenticationTokenFilter(Jwt jwt) {
//        this.jwt = jwt;
//    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        String token = request.getHeader("X-PRGRMS-AUTH");
//
        System.out.println("filter!");
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        }
//        Jwt.Claims claims = jwt.verify(token);
//        if (SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//            User user = new User("tester@gmail.com", "1234", grantedAuthorities);
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
//            auth.setDetails(user);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }

        String adminId = null;
        String jwtToken = null;

//        // Bearer token인 경우 JWT 토큰 유효성 검사 진행
//        if (token != null && token.startsWith("Bearer ")) {
//            jwtToken = token.substring(7);
//            try {
//                adminId = jwtTokenProvider.getUsernameFromToken(jwtToken);
//            } catch (SignatureException e) {
//                log.error("Invalid JWT signature: {}", e.getMessage());
//            } catch (MalformedJwtException e) {
//                log.error("Invalid JWT token: {}", e.getMessage());
//            } catch (ExpiredJwtException e) {
//                log.error("JWT token is expired: {}", e.getMessage());
//            } catch (UnsupportedJwtException e) {
//                log.error("JWT token is unsupported: {}", e.getMessage());
//            } catch (IllegalArgumentException e) {
//                log.error("JWT claims string is empty: {}", e.getMessage());
//            }
//        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
//        }

        chain.doFilter(request, response);
    }

}