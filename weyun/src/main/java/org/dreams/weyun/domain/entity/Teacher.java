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
@TableName("base_teacher")
public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 教师ID
     */
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 教师名称
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 教师性别
     */
    @TableField("teacher_sex")
    private String teacherSex;

    /**
     * 出生日期
     */
    @TableField("teacher_birthdate")
    private LocalDateTime teacherBirthdate;

    /**
     * 手机号码
     */
    @TableField("teacher_phone")
    private String teacherPhone;

    /**
     * 身份证号码
     */
    @TableField("standing_phone")
    private String standingPhone;

    /**
     * 政治面貌【1.团员、2.群众、3.党员】
     */
    @TableField("teacher_type")
    private String teacherType;

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
