package com.kjwon.foodchoice.mapper;

import com.kjwon.foodchoice.vo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findCommentsByRestaurantId(int restaurantId, int offset, int number);
    void addComment(int restaurantId, String content);
}
