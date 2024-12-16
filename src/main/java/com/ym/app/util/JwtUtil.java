package com.ym.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "planning-and-focus-assistant";

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}


/*弃用
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "planning-and-focus-assistant-my-secret-key-here"; // 设置密钥（最好用随机生成器生成）
    private static final long EXPIRATION_TIME = 1000*60*60*12; // 12h

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());  // 创建 HMAC-SHA256 密钥

    // 生成 JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())  // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .signWith(key, SignatureAlgorithm.HS256) // 使用 HMAC-SHA256 签名
                .compact();
    }

    // 验证和解析 JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // 验证签名密钥
                .build()
                .parseClaimsJws(token)  // 解析 JWT
                .getBody()
                .getSubject();  // 提取用户名
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // JWT有效，放行
        } catch (JwtException e) {
            return false; // JWT无效，拦截
        }
    }
}*/
