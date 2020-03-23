package online.kyralo.user.service;

import org.springframework.http.ResponseEntity;
import online.kyralo.user.domain.User;

import javax.servlet.http.HttpSession;

/**
 * @author yeqi
 */
public interface LoginService {

    /**
     * 验证码登入
     * @param code 验证码
     * @param email 邮箱
     * @param httpSession httpSession
     * @return 是否登录成功
     */
    ResponseEntity<?> verifyCodeToLogin(String code, String email, HttpSession httpSession);

    /**
     * 密码登入
     * @param mail 邮箱
     * @param password 密码
     * @return 是否登录成功
     */
    ResponseEntity<?> login(String mail, String password);

    /**
     * 注册
     * @param user 用户信息
     */
    ResponseEntity<?> register(User user);
}
