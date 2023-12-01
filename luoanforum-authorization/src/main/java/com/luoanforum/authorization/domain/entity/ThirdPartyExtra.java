package com.luoanforum.authorization.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) // callSuper = false表示不调用父类的属性
@JsonIgnoreProperties(ignoreUnknown = true) // ignoreUnknown = true表示忽略未知属性
public class ThirdPartyExtra implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 绑定的qq
     */
    private String qq;
    /**
     * 绑定的微信
     */
    private String wechat;
    /**
     * 绑定的微博
     */
    private String weibo;
    /**
     * 绑定的github
     */
    private String github;
    /**
     * 绑定的gitee
     */
    private String gitee;
}
