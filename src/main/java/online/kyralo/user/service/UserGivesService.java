package online.kyralo.user.service;

import online.kyralo.user.domain.UserGives;
import org.springframework.http.ResponseEntity;

/**
 * @author yeqi
 */
public interface UserGivesService {
    /**
     * 统计点赞数
     * @param userGives targetId, type
     * @return 点赞数
     */
    ResponseEntity<?> countGives(UserGives userGives);

    /**
     * 获取用户点赞过的评论和视频
     * @param userId 用户id
     * @return 用户点赞过的评论和视频
     */
    ResponseEntity<?> listsUserGives(String userId);

    /**
     * 新增点赞记录
     * @param userGives 点赞记录
     * @return 新增的状态
     */
    ResponseEntity<?> insert(UserGives userGives);

    /**
     * 用户取消点赞
     * @param userGives 点赞信息(userId, targetId, type)
     * @return 删除的状态
     */
    ResponseEntity<?> delete(UserGives userGives);
}
