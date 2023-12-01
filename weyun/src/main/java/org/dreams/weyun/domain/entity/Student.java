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
@TableName("base_student")
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    /**
     * 学生名称
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 学生性别
     */
    @TableField("student_sex")
    private String studentSex;

    /**
     * 出生日期
     */
    @TableField("student_birthdate")
    private LocalDateTime studentBirthdate;

    /**
     * 手机号码
     */
    @TableField("student_phone")
    private String studentPhone;

    /**
     * 身份证号码
     */
    @TableField("standing_phone")
    private String standingPhone;

    /**
     * 学科ID
     */
    @TableField("subject_id")
    private Integer subjectId;

    /**
     * 政治面貌【团员、群众、党
员】
     */
    @TableField("student_type")
    private String studentType;

    /**
     * 班级ID
     */
    @TableField("class_id")
    private Integer classId;

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
