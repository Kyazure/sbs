package online.kyralo.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import online.kyralo.user.dao.UserCommentMapper;
import online.kyralo.user.dao.UserMapper;
import online.kyralo.user.domain.Comment;
import online.kyralo.user.domain.User;
import online.kyralo.user.domain.UserComment;
import online.kyralo.user.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:54
 * \* Year: 2019
 * \
 */

@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserCommentMapper mapper;

    @Autowired
    private UserMapper userMapper;

    public UserCommentServiceImpl(UserCommentMapper mapper, UserMapper userMapper) {
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<?> listUserComments(String videoId, Integer id, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            if ("".equals(videoId) || id == null) {
                return ResponseEntity.badRequest().build();
            } else {
                PageHelper.startPage(pageNum, pageSize);
                Page<Comment> comments = mapper.listUserComments(videoId, id);
                if (comments.isEmpty()) {
                    return ResponseEntity.notFound().build();
                } else {
                    comments.forEach(comment -> {

                        if ("".equals(comment.getAnswerId())) {

                            User user = userMapper.queryById(comment.getSendId());

                            if ("0".equals(user.getFettle()) && "1".equals(user.getFettle())) {
                                comment.setSendName(user.getName());
                                comment.setSendAvatarUrl(user.getAvatarUrl());
                            } else {
                                comment.setSendName("已注销");
                            }

                        } else {

                            List<User> users = mapper.commentUserInfo(comment.getSendId(), comment.getAnswerSendId());

                            User user1 = users.get(0);
                            User user2 = users.get(1);

                            if ("0".equals(user1.getFettle()) && "1".equals(user1.getFettle())) {
                                comment.setSendName(user1.getName());
                                comment.setSendAvatarUrl(user1.getAvatarUrl());
                            } else {
                                comment.setSendName("已注销");
                            }

                            if ("0".equals(user2.getFettle()) && "1".equals(user2.getFettle())) {
                                comment.setAnswerName(user2.getName());
                                comment.setAnswerAvatarUrl(user2.getAvatarUrl());
                            } else {
                                comment.setAnswerName(user2.getName());
                            }
                        }

                    });

                    int pages = comments.getPages();
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("pages", pages);
                    map.put("comments",comments);
                    return ResponseEntity.ok(map);
                }
            }
        }else {
            return ResponseEntity.badRequest().body("pageNum或pageSize为空");
        }
    }

    @Override
    public ResponseEntity<?> insert(UserComment userComment) {
        if ("".equals(userComment.getSendId()) && "".equals(userComment.getVideoId())) {
            return ResponseEntity.badRequest().body("信息不完整!");
        } else {
            if ("".equals(userComment.getCommentContent()) || "".equals(userComment.getCommentImageUrl())) {
                return ResponseEntity.badRequest().body("评论内容不能为空!");
            } else {
                userComment.setId(UUID.randomUUID().toString().replace("-", ""));
                if (mapper.insert(userComment) == 1) {
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }
    }

//    @Override
//    public ResponseEntity<?> update(String id, boolean like) {
//        if ("".equals(id)) {
//            return ResponseEntity.badRequest().body("请求参数错误!");
//        } else {
//            if (mapper.update(id, like) == 1) {
//                return ResponseEntity.status(HttpStatus.CREATED).build();
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//            }
//        }
//    }
}
