package com.example.luoanforum.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.luoanforum.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/28 10:20
 * @description：
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.timeout}")
    private Long time;

    public String getToken(String id, String password) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + time * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(id).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(password));
        return token;
    }

}
