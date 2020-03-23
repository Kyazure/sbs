package online.kyralo.user.service;

import online.kyralo.user.domain.UserComment;
import org.springframework.http.ResponseEntity;

/**
 * @author yeqi
 */
public interface UserCommentService {


    /**
     * 获取所有评论 通过评论id 评论类型
     * @param videoId 评论id
     * @param type 评论类型
     * @param pageNum 第几页
     * @param pageSize 每页显示数量
     * @return 所有评论
     */
    ResponseEntity<?> listUserComments(String videoId, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 新增评论
     * @param userComment 用户评论
     * @return 是否新增成功
     */
    ResponseEntity<?> insert(UserComment userComment);

//    /**
//     * 更新评论点赞数 通过评论id
//     * @param id 评论id
//     * @param like 是否点赞
//     * @return 是否更新成功
//     */
//    ResponseEntity<?> update(String id, boolean like);

}
