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
@TableName("base_subject")
public class Subject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 学科ID
     */
    @TableId(value = "subject_id", type = IdType.AUTO)
    private Integer subjectId;

    /**
     * 学科名称
     */
    @TableField("subject_name")
    private String subjectName;

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
    private Boolean deleteFlag;
}
