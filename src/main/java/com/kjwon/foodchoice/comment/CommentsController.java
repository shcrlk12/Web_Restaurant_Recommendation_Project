package com.kjwon.foodchoice.comment;

import com.kjwon.foodchoice.dto.CommentDto;
import com.kjwon.foodchoice.util.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import  com.kjwon.foodchoice.util.ApiUtils.*;

import static com.kjwon.foodchoice.util.ApiUtils.success;

@RestController()
@RequestMapping("api/JSON/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentsServiceImpl commentsService;
    @PostMapping("addComment")
    public ApiResult<String> getComments(Optional<Integer> restaurantId, Optional<String> content, Principal principal){
        commentsService.addComment(restaurantId.get(), content.get());

        return success("success!");
    }


}
