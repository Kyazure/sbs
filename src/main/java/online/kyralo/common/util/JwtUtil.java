package online.kyralo.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static online.kyralo.common.constants.SecurityConstants.*;


/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2018-12-09
 * \* Time: 下午9:52
 * \* Description:jwt工具类
 * \
 */
@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 解析token
     * @param token the JWT token to parse
     * @return token claims
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (JwtException e) {
            logger.warn("jwt验证失败" + e.getMessage());
            return null;
        }
    }

    /**
     * 管理员token授权
     * @param username 用户名
     * @return the JWT token
     */
    public static String generateTokenForAdmin(String username) {

        Claims claims = Jwts.claims()
                .setAudience(username).setSubject("ROLE_ADMIN");
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    /**
     * 用户获取token
     * @param id 微信用户为openId
     *           平台用户为用户Id
     * */
    public static String generateTokenById(String id,String userId) {
        Claims claims = Jwts.claims()
                .setAudience(id)
                .setSubject("ROLE_USER");

        //        新增值自定义字段
        Map<String,Object> map = new HashMap<>(1);
        map.put("userId",userId);

        return Jwts.builder()
                .setClaims(claims)
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    /**
     * 用户获取token
     * @param userId 平台用户为用户Id
     *
     * */
    public static String generateTokenByUserId(String userId) {
        Claims claims = Jwts.claims()
                .setAudience(userId)
                .setSubject("ROLE_USER");

        //        新增值自定义字段
//        Map<String,Object> map = new HashMap<>(1);
//        map.put("userId",userId);

        return Jwts.builder()
                .setClaims(claims)
//                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    public static Claims claimGet(HttpServletRequest request){
        Claims claims = null;
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            claims = JwtUtil.parseToken(token);
        }
        return claims;
    }
}
