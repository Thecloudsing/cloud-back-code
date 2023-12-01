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
@TableName("base_school")
public class School implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 学校ID
     */
    @TableId(value = "school_id", type = IdType.AUTO)
    private Integer schoolId;

    /**
     * 学校名称
     */
    @TableField("school_name")
    private String schoolName;

    /**
     * 区域ID
     */
    @TableField("region_id")
    private Integer regionId;

    /**
     * 学校地址
     */
    @TableField("school_address")
    private String schoolAddress;

    /**
     * 学校电话
     */
    @TableField("school_phone")
    private String schoolPhone;

    /**
     * 学校邮箱
     */
    @TableField("school_mailbox")
    private String schoolMailbox;

    /**
     * 学校类型ID
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 学校性质【0：公办 1：民办】
     */
    @TableField("school_type")
    private String schoolType;

    /**
     * 学校简介
     */
    @TableField("school_brief")
    private String schoolBrief;

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
