package com.juyss.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: JwtUtils
 * @Desc: JWT工具类
 * @package com.juyss.util
 * @project atguigu-Advanced
 * @date 2020/12/6 15:29
 */
public class JwtUtils {
    // 用于JWT进行签名加密的秘钥
    private static final String SECRET = "code-duck-*%#@*!&";

    /**
     * 根据传入信息获取token
     * @param map 传入的payload
     * @return 生成的token
     */
    public static String generateToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        // 将map内的信息传入JWT的payload中
        map.forEach(builder::withClaim);

        // 设置JWT令牌的过期时间为60
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60);
        builder.withExpiresAt(instance.getTime());

        // 设置签名并返回token
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 传入需要验证的token,如果验证不通过,会抛出异常
     * @param token 需要验证的token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 获取token信息
     * @param token 请求头携带的token
     * @return DecodedJWT 解码后的token
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}
