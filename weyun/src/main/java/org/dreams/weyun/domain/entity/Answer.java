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
@TableName("base_answer")
public class Answer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 回答ID
     */
    @TableId(value = "answer_id", type = IdType.AUTO)
    private Integer answerId;

    /**
     * 回答内容
     */
    @TableField("content")
    private String content;

    /**
     * 悬赏金额
     */
    @TableField("amount")
    private String amount;

    /**
     * 查看数
     */
    @TableField("view_number")
    private Integer viewNumber;

    /**
     * 评论数
     */
    @TableField("comment_number")
    private Integer commentNumber;

    /**
     * 收藏数
     */
    @TableField("collect_number")
    private Integer collectNumber;

    /**
     * 状态类型【已答/未答】
     */
    @TableField("status_type")
    private String statusType;

    /**
     * 提问ID
     */
    @TableField("question_id")
    private Integer questionId;

    /**
     * 年级ID
     */
    @TableField("grade_id")
    private Integer gradeId;

    /**
     * 班级ID
     */
    @TableField("class_id")
    private Integer classId;

    /**
     * 学生ID
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 教师ID
     */
    @TableField("teacher_id")
    private Integer teacherId;

    /**
     * 学科ID
     */
    @TableField("subject_id")
    private Integer subjectId;

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
