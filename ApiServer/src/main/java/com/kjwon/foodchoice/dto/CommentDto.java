package com.kjwon.foodchoice.dto;

import com.kjwon.foodchoice.vo.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    long id;
    String content;
    int likesNumber;
    LocalDateTime registerDate;
    boolean isDisable;
    String userId;
    long restaurantId;
    long commentId;

    public static CommentDto of(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .likesNumber(comment.getLikesNumber())
                .registerDate(comment.getRegisterDate())
                .isDisable(comment.isDisable())
                .userId(comment.getUserId())
                .restaurantId(comment.getRestaurantId())
                .commentId(comment.getCommentId())
                .build();
    }

    public static List<CommentDto> of(List<Comment> commentList){
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            commentDtoList.add(CommentDto.of(comment));
        }
        return commentDtoList;
    }
}
