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
@TableName("base_knowledge")
public class Knowledge implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 知识点ID
     */
    @TableId(value = "knowledge_id", type = IdType.AUTO)
    private Integer knowledgeId;

    /**
     * 知识点编码
     */
    @TableField("knowledge_encoding")
    private String knowledgeEncoding;

    /**
     * 知识点名称
     */
    @TableField("knowledge_name")
    private String knowledgeName;

    /**
     * 知识点父ID
     */
    @TableField("knowledge_father_id")
    private Integer knowledgeFatherId;

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
