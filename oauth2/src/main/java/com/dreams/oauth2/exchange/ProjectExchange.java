package com.dreams.oauth2.exchange;

import com.dreams.oauth2.domain.Result;
import com.dreams.oauth2.domain.response.CaptchaResult;
import org.springframework.stereotype.Component;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Description: 为back4app部署准备的接口，调用当前服务
 *
 * @author luoan
 * @since 2023/10/25
 */
@Component
@HttpExchange
public interface ProjectExchange {
    /**
     * 调用当前项目的获取验证码方法
     *
     * @return 统一响应类
     */
    @GetExchange("/getCaptcha")
    // @GetExchange
    Result<CaptchaResult> getCaptcha();
}
