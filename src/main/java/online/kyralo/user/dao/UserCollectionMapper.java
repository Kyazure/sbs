package online.kyralo.user.dao;

import online.kyralo.user.domain.UserCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeqi
 */
@Repository
public interface UserCollectionMapper{

    /**
     * 查询所有用户收藏 通过用户id
     * @param userId 用户id
     * @return 所有用户收藏
     */
    List<UserCollection> listUserCollectionsById(String userId);

    /**
     * 新增用户收藏
     * @param userCollection 用户收藏
     * @return 是否新增成功
     */
    int insert(UserCollection userCollection);

    /**
     * 删除收藏 通过收藏id和收藏id
     * @param id 收藏id
     * @param userId 收藏id
     * @return 是否删除成功
     */
    int delete(Integer id, String userId);

}