package com.dreams.text.domain.entity;

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
 * Description: 新闻表
 *
 * @author luoan
 * @since 2023/10/19
 */
@Data
@Builder
@ToString
@TableName("t_news")
public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键新闻ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 频道ID
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 新闻标题
     */
    @TableField("title")
    private String title;

    /**
     * 新闻详情
     */
    @TableField("detail")
    private String detail;

    /**
     * 新闻类型(0=>头条, 1=>视频, 2=>活动)
     */
    @TableField("type_flag")
    private Byte typeFlag;

    /**
     * 新闻点赞数
     */
    @TableField("like_number")
    private Integer likeNumber;

    /**
     * 新闻评论数
     */
    @TableField("comment_number")
    private Integer commentNumber;

    /**
     * 是否置顶(0=>否, 1=>是)
     */
    @TableField("is_top")
    private Byte isTop;

    /**
     * 创建人ID
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标志(0=>正常, 1=>删除)
     */
    @TableField("delete_flag")
    private Byte deleteFlag;
}
