package online.kyralo.user.dao;

import online.kyralo.user.domain.GivesForComments;
import online.kyralo.user.domain.GivesForVideo;
import online.kyralo.user.domain.UserGives;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeqi
 */

@Repository
public interface UserGivesMapper{

    /**
     * 统计点赞数
     * @param userGives targetId, type
     * @return 点赞数
     */
    int countGives(UserGives userGives);

    /**
     * 获取用户点赞过的评论
     * @param userId 用户id
     * @return 用户点赞过的评论
     */
    List<GivesForComments> listsUserGivesForComments(String userId);

    /**
     * 获取用户点赞过的视频
     * @param userId 用户id
     * @return 用户点赞过的视频
     */
    List<GivesForVideo> listsUserGivesForVideo(String userId);

    /**
     * 新增点赞记录
     * @param userGives 点赞记录
     * @return 是否新增成功
     */
    int insert(UserGives userGives);

    /**
     * 用户取消点赞
     * @param userGives 点赞信息(userId, targetId, type)
     * @return 是否删除成功
     */
    int delete(UserGives userGives);
}