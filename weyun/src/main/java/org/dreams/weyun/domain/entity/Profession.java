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
@TableName("base_profession")
public class Profession implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 专业ID
     */
    @TableId(value = "profession_id", type = IdType.AUTO)
    private Integer professionId;

    /**
     * 专业编码
     */
    @TableField("profession_encoding")
    private String professionEncoding;

    /**
     * 专业名称
     */
    @TableField("profession_name")
    private String professionName;

    /**
     * 学校ID
     */
    @TableField("school_id")
    private Integer schoolId;

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
