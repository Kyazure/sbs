package online.kyralo.user.dao;

import com.github.pagehelper.Page;
import online.kyralo.user.domain.Comment;
import online.kyralo.user.domain.User;
import online.kyralo.user.domain.UserComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeqi
 */
@Repository
public interface UserCommentMapper{

    /**
     * 查询评论者回复评论者的信息
     * @param sendId 评论者id
     * @param answerSendId 回复者id
     * @return 评论者回复评论者的信息
     */
    List<User> commentUserInfo(String sendId, String answerSendId);

    /**
     * 获取所有评论 通过评论id 评论类型
     * @param videoId 评论id
     * @param type 评论类型
     * @return 所有评论
     */
    Page<Comment> listUserComments(String videoId, Integer type);

    /**
     * 新增评论
     * @param userComment 用户评论
     * @return 是否新增成功
     */
    int insert(UserComment userComment);

    /**
     * 更新评论点赞数 通过评论id
     * @param id 评论id
     * @param like 是否点赞
     * @return 是否更新成功
     */
    int update(String id, boolean like);
}