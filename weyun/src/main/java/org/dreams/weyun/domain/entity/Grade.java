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
@TableName("base_grade")
public class Grade implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 年级ID
     */
    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

    /**
     * 年级编码
     */
    @TableField("grade_encoding")
    private String gradeEncoding;

    /**
     * 年级名称
     */
    @TableField("grade_name")
    private String gradeName;

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
