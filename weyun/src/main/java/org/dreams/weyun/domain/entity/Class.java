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
@TableName("base_class")
public class Class implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 班级ID
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    /**
     * 班级编码
     */
    @TableField("class_encoding")
    private String classEncoding;

    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;

    /**
     * 年级ID
     */
    @TableField("grade_id")
    private Integer gradeId;

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
