package com.luoanforum.authorization.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false) // callSuper = false表示不调用父类的属性
@TableName(value = "user_data", autoResultMap = true) // autoResultMap = true表示自动映射
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData implements Serializable {
    @Serial // @Serial表示序列化
    private static final long serialVersionUID = 1L;
    /**
     * id 主键 自增
     */
    @TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    /**
     * openid 开放平台唯一标识
     */
    @TableField("openid")
    private String openid;
    /**
     * nickname 昵称
     */
    @TableField("nickname")
    private String nickname;
    /**
     * username 用户名
     */
    @TableField("username")
    private String username;
    /**
     * password 密码
     */
    @TableField("password")
    private String password;
    /**
     * avatar 头像
     */
    @TableField("avatar")
    private String avatar;
    /**
     * 绑定的手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 绑定的邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 第三方登录拓展 json
     */
    @TableField(value = "thirdPartyExtra",
            typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private ThirdPartyExtra thirdPartyExtra;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
