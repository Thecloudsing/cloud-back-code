package org.dreams.weyun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Builder;
import lombok.ToString;
import java.io.Serial;
/**
 * Description: 
 *
 * @author luoan
 * @since 2023/11/29
 */
@Data
@Builder
@ToString
@TableName("base_role_permissions")
public class RolePermissions implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色权限ID
     */
    @TableId(value = "role_permissions_id", type = IdType.AUTO)
    private Integer rolePermissionsId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 权限ID
     */
    @TableField("permissions_id")
    private Integer permissionsId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标志
     */
    @TableField("delete_flag")
    private String deleteFlag;
}
