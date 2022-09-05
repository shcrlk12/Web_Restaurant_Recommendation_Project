package com.kjwon.foodchoice.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    long id;
    String content;
    int likesNumber;
    LocalDateTime registerDate;
    boolean isDisable;
    String userId;
    long restaurantId;
    long commentId;
}
