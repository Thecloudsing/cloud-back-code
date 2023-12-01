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
@TableName("base_region")
public class Region implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableId(value = "region_id", type = IdType.AUTO)
    private Integer regionId;

    /**
     * 区域编码
     */
    @TableField("region_encoding")
    private String regionEncoding;

    /**
     * 区域名称
     */
    @TableField("region_name")
    private String regionName;

    /**
     * 区域父ID
     */
    @TableField("region_father_id")
    private Integer regionFatherId;

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
