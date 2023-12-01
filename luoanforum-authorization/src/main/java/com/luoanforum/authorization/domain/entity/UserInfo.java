package com.luoanforum.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false) // callSuper = false表示不调用父类的属性
@TableName(value = "sys_user", autoResultMap = true) // autoResultMap = true表示自动映射
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements UserDetails, Serializable {
    @Serial // @Serial表示序列化
    private static final long serialVersionUID = 1L;
    /**
     * id 主键 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * nickname 昵称
     */
    @TableField("name")
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
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 创建用户
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 更新用户
     */
    @TableField("update_user")
    private String updateUser;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
