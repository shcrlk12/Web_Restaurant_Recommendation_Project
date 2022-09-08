package com.kjwon.foodchoice.users;

import com.kjwon.foodchoice.errors.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService implements UserDetailsService {


//    private final UserMapper userMapper;
//
//
//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, "1234", grantedAuthorities);
    }

//    @Transactional
//    public User login(Email email, String password) {
//        checkNotNull(password, "password must be provided");
////        User user = findByEmail(email)
////                .orElseThrow(() -> new NotFoundException("Could not found user for " + email));
////        user.login(passwordEncoder, password);
//        User user= new User("tester@gmail.com", new Email("tester@gmail.com"), "1234");
//        user.afterLoginSuccess();
////        userMapper.update(user);
//        return user;
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<User> findById(Long userId) {
//        checkNotNull(userId, "userId must be provided");
//
//        return userMapper.findById(userId);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<User> findByEmail(Email email) {
//        checkNotNull(email, "email must be provided");
//
//        return userMapper.findByEmail(email);
//    }

}