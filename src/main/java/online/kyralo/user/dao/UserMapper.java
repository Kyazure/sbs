package online.kyralo.user.dao;

import online.kyralo.user.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author yeqi
 */
@Repository
public interface UserMapper{

    /**
     * 查询用户信息 通过mail
     * @param mail 邮箱
     * @return 用户信息
     */
    User queryByMail(String mail);

    /**
     * 查询用户信息 通过id
     * @param id 用户id
     * @return 用户信息
     */
    User queryById(String id);

    /**
     * 新增用户
     * @param user 用户信息
     * @return 是否新增成功
     */
    int insert(User user);

    /**
     * 更新用户信息 通过用户id
     * @param user 用户信息
     * @return 是否更新成功
     */
    int update(User user);

}