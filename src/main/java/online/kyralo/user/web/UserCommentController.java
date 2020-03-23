package online.kyralo.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.user.domain.UserComment;
import online.kyralo.user.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:57
 * \* Year: 2019
 * \
 */

@RestController
@RequestMapping("/api/v2/user_comment")
@Api("用户评论接口")
public class UserCommentController {

    @Autowired
    private UserCommentService service;

    public UserCommentController(UserCommentService service) {
        this.service = service;
    }

    @GetMapping("/{videoId}")
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询所有用户评论 通过视频id 评论类型type", response = UserComment.class)
    public ResponseEntity<?> getAllByVideoId(@PathVariable("videoId") String videoId, @RequestParam  Integer pageNum, @RequestParam Integer pageSize){
        return service.listUserComments(videoId, 0, pageNum, pageSize);
    }

    @PostMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "新增用户评论", response = UserComment.class)
    public ResponseEntity<?> insert(@RequestBody UserComment userComment){
        return service.insert(userComment);
    }

//    @PutMapping
//    @Secured("ROLE_USER")
//    @ApiOperation(value = "更新用户评论点赞, 通过用户id,点赞标识 like", response = UserComment.class)
//    public ResponseEntity<?> update(@RequestParam("id") String id, @RequestParam("like") boolean like){
//        return service.update(id, like);
//    }
}
