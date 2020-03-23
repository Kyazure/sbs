package online.kyralo.user.web;

import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.user.service.LoginService;
import online.kyralo.user.service.MailService;
import online.kyralo.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-27
 * \* Time: 9:56
 * \* Year: 2019
 * \
 */

@RestController
@RequestMapping("/api/v2/user")
@Api("用户登录接口")
public class LoginController {


    @Autowired
    private MailService mailService;

    @Autowired
    private LoginService loginService;

    public LoginController(MailService mailService, LoginService loginService) {
        this.mailService = mailService;
        this.loginService = loginService;
    }

    @PostMapping("/code/get")
    @ApiOperation(value = "邮箱获取验证码")
    public ResponseEntity<?> getCheckCode(@RequestParam String mail, HttpSession httpSession) {
        // 生成验证码
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为：" + checkCode;

        String mad5 = SecureUtil.hmacMd5(checkCode).toString();

        httpSession.setAttribute(mail, mad5);
        return mailService.sendSimpleMail(mail, "注册验证码", message);
    }

    @PostMapping("/code/verify")
    @ApiOperation(value = "邮箱验证码登入")
    public ResponseEntity<?> verifyCodeToLogin(@RequestParam String code, @RequestParam String email, HttpSession httpSession){
        return loginService.verifyCodeToLogin(code, email, httpSession);
    }

    @PostMapping("/login")
    @ApiOperation(value = "密码登入")
    public ResponseEntity<?> login(@RequestParam String mail, @RequestParam String password){
        return loginService.login(mail, password);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ResponseEntity<?> register(@RequestBody User user){
        return loginService.register(user);
    }
}
