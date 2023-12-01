package org.dreams.weyun.common.exception.enums;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import org.dreams.weyun.common.domain.vo.response.ApiResult;
import org.dreams.weyun.common.exception.ErrorEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
@AllArgsConstructor
public enum HttpErrorEnum implements ErrorEnum {

    /**
     * http状态异常
     */
    ACCESS_DENIED(401, "登录失效，请重新登录"),
        ;

    private final Integer errorCode;
    private final String errorMsg;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(this.getErrorCode());
        ApiResult<Void> responseData = ApiResult.fail(this);
        response.setContentType(ContentType.JSON.toString(StandardCharsets.UTF_8));
        response.getWriter().write(JSONUtil.toJsonStr(responseData));
    }
}
