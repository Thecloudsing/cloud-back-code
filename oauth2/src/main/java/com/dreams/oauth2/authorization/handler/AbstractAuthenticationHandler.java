package com.dreams.oauth2.authorization.handler;

import com.dreams.oauth2.domain.Result;
import com.dreams.oauth2.util.JsonUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Description: 验证返回抽象处理
 *
 * @author luoan
 * @since 2023/10/27
 */

public abstract class AbstractAuthenticationHandler {
    protected final String charset = StandardCharsets.UTF_8.name();
    protected final String mediaType = MediaType.APPLICATION_JSON_VALUE;

    protected <T> void responseResult(Result<T> result, HttpServletResponse resp) throws IOException {
        resp.setContentType(charset);
        resp.setContentType(mediaType);
        resp.getWriter().write(JsonUtils.objectCovertToJson(result));
        resp.getWriter().flush();
    }
}
