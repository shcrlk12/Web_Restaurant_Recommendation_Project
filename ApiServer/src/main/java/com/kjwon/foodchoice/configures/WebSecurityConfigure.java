package com.kjwon.foodchoice.configures;
import com.kjwon.foodchoice.security.*;
//import com.kjwon.foodchoice.users.Role;
import com.kjwon.foodchoice.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

//    private final Jwt jwt;
//
//    private final JwtTokenConfigure jwtTokenConfigure;
//
//    private final JwtAccessDeniedHandler accessDeniedHandler;
//
//    private final EntryPointUnauthorizedHandler unauthorizedHandler;

    private final UserService userService;

//    public WebSecurityConfigure(Jwt jwt, JwtTokenConfigure jwtTokenConfigure, JwtAccessDeniedHandler accessDeniedHandler, EntryPointUnauthorizedHandler unauthorizedHandler, UserService userService) {
//        this.jwt = jwt;
//        this.jwtTokenConfigure = jwtTokenConfigure;
//        this.accessDeniedHandler = accessDeniedHandler;
//        this.unauthorizedHandler = unauthorizedHandler;
//        this.userService = userService;
//    }

//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/webjars/**", "/static/**", "/templates/**", "/h2/**");
//    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(UserService userService) {
        return new JwtAuthenticationProvider(userService);
    }

    @Bean
    public KakaoLoginProvider kakaoLoginProvider(UserService userService) {
        return new KakaoLoginProvider(userService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(jwtAuthenticationProvider(userService));
        builder.authenticationProvider(kakaoLoginProvider(userService));
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.logout().logoutUrl("/logout");
        http.formLogin()
                .loginPage("/api/users/login")
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                ;

        http.authorizeRequests()
                .antMatchers("/api/JSON/restaurant/**").permitAll()
                .antMatchers("/api/JSON/keyword/**").permitAll()
//                .antMatchers("/api/JSON/comments/**").hasRole("USER")
        ;

        super.configure(http);

//            .exceptionHandling()
//            .accessDeniedHandler(accessDeniedHandler)
//            .authenticationEntryPoint(unauthorizedHandler)
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/api/users/login").permitAll()


//            .antMatchers("/api/**").hasRole(Role.USER.name())
//            .anyRequest().permitAll()

//            .and()
//            .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
    }

}