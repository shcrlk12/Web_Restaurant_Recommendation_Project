package com.kjwon.foodchoice.users;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
//    void update(User user);
//
//    Optional<User> findById(long id);
//
    void registerUser(String userId, String password, String username);
    Optional<User> findByEmail(String userId);
}
