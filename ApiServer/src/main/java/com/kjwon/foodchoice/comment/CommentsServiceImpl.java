package com.kjwon.foodchoice.comment;

import com.kjwon.foodchoice.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentsServiceImpl {
    private final CommentMapper commentMapper;

    public boolean addComment(String username, int restaurantId, String content)
    {
        commentMapper.addComment(username, restaurantId, content);

        return true;
    }
}
