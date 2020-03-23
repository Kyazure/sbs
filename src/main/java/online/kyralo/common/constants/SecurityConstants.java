package online.kyralo.common.constants;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2018-12-11
 * \* Time: 下午6:46
 * \* Description:安全相关的一些常量
 * \
 */
public class SecurityConstants {
    /**
     *  60*60*1000 1小时
     */
    public static final long EXPIRATION_TIME = 3600_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
