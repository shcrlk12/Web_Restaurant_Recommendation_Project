package com.kjwon.foodchoice.users;

//import com.kjwon.foodchoice.security.Jwt;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@Setter
public class User {

    private String userId;

    private String password;

    private String name;

    private String roadAddress;

    private String phoneNumber;

    private LocalDateTime registerDate;

    private LocalDateTime lastLoginTime;

    private int loginTimes;


}