package com.kjwon.foodchoice.security;

import com.kjwon.foodchoice.errors.NotFoundException;
//import com.kjwon.foodchoice.users.Email;
//import com.kjwon.foodchoice.users.Role;
//import com.kjwon.foodchoice.users.User;
import com.kjwon.foodchoice.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User)userService.loadUserByUsername("tester@gmail.com");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

        auth.setDetails(user);
        return auth;

//        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
//        return processUserAuthentication(
//                Email.of(String.valueOf(authenticationToken.getPrincipal())),
//                String.valueOf(authenticationToken.getCredentials())
//        );
    }

//    private Authentication processUserAuthentication(Email email, String password) {
//        try {
//            UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken("tester@gmail.com", "1234", Collections.emptyList());
//            authenticated.setDetails(new org.springframework.security.core.userdetails.User("tester@gmail.com", "1234", Collections.emptyList()));
//            //            User user = userService.login(email, password);
////            JwtAuthenticationToken authenticated =
////                    new JwtAuthenticationToken(
////                            new JwtAuthentication(14L, "tester@gmail.com"),
////                            null,
////                            createAuthorityList(Role.USER.value())
////                    );
////            authenticated.setDetails(new org.springframework.security.core.userdetails.User("tester@gmail.com", "1234", null));
//            return authenticated;
//        } catch (NotFoundException e) {
//            throw new UsernameNotFoundException(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            throw new BadCredentialsException(e.getMessage());
//        } catch (DataAccessException e) {
//            throw new AuthenticationServiceException(e.getMessage(), e);
//        }
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isAssignable(UsernamePasswordAuthenticationToken.class, authentication);
    }

}