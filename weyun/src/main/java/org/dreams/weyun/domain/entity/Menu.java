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
@TableName("base_menu")
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(value = "permissions_id", type = IdType.AUTO)
    private Integer permissionsId;

    /**
     * 权限编码
     */
    @TableField("permissions_encoding")
    private String permissionsEncoding;

    /**
     * 权限名称
     */
    @TableField("permissions_name")
    private String permissionsName;

    /**
     * 权限图标
     */
    @TableField("permissions_icon")
    private String permissionsIcon;

    /**
     * 跳转链接
     */
    @TableField("permissions_path")
    private String permissionsPath;

    /**
     * 权限文件地址
     */
    @TableField("permissions_address")
    private String permissionsAddress;

    /**
     * 权限类型【父菜单、子菜单、按钮】
     */
    @TableField("permissions_type")
    private String permissionsType;

    /**
     * 权限排序
     */
    @TableField("permissions_sort")
    private String permissionsSort;

    /**
     * 权限父ID
     */
    @TableField("permissions_father_id")
    private Integer permissionsFatherId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标志
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;
}
