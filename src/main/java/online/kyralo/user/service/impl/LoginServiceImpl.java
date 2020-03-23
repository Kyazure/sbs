package online.kyralo.user.service.impl;

import online.kyralo.common.util.JwtUtil;
import online.kyralo.user.dao.UserMapper;
import online.kyralo.user.domain.User;
import cn.hutool.crypto.SecureUtil;
import online.kyralo.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-27
 * \* Time: 11:57
 * \* Year: 2019
 * \
 */

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginServiceImpl(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public ResponseEntity<?> verifyCodeToLogin(String code, String email, HttpSession httpSession) {
        if ("".equals(code) || "".equals(email)){
            return ResponseEntity.badRequest().build();
        }else {
            String checkCode = (String)httpSession.getAttribute(email);
            if (SecureUtil.hmacMd5(code).toString().equals(checkCode)){
                httpSession.removeAttribute(email);
                User user = userMapper.queryByMail(email);
                if (user != null){
                    user.setPassword("");
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setBearerAuth(JwtUtil.generateTokenByUserId(user.getId()));
                    return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
                }else {
                    return ResponseEntity.status(406).body("账号已注销或被封禁");
                }

            }else {
                return ResponseEntity.status(406).body("验证码验证失败");
            }

        }
    }

    @Override
    public ResponseEntity<?> login(String mail, String password) {
        if ("".equals(mail) || "".equals(password)){
            return ResponseEntity.badRequest().body("邮箱或密码为空");
        }else {
            User user = userMapper.queryByMail(mail);
            if (user != null){
                if (bCryptPasswordEncoder.matches(password, user.getPassword())){
                    user.setPassword("");
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setBearerAuth(JwtUtil.generateTokenByUserId(user.getId()));
                    return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
                }else {

                    //todo 登入失败处理
                    return ResponseEntity.notFound().build();
                }
            }else {
                return ResponseEntity.status(406).body("账号已注销或被封禁");
            }
        }
    }

    @Override
    public ResponseEntity<?> register(User user) {

        if ("".equals(user.getMail()) || "".equals(user.getPassword())){
            return ResponseEntity.badRequest().build();
        }else {
            user.setId(UUID.randomUUID().toString().replace("-",""));

            // 密码加密
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userMapper.insert(user) == 1){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
