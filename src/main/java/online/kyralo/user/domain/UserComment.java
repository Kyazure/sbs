package online.kyralo.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

/**
 * @author yeqi
 */

@Data
@Table(name = "user_comment")
@ApiModel(value = "userComment", description = "用户评论")
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户评论id", name = "id")
    private String id;

    @Column(name = "video_id")
    @ApiModelProperty(value = "视频id", name = "videoId")
    private String videoId;

    @Column(name = "send_id")
    @ApiModelProperty(value = "发送者id", name = "sendId")
    private String sendId;

    @Column(name = "answer_id")
    @ApiModelProperty(value = "要回复的评论id", name = "answerId")
    private String answerId;

    @ApiModelProperty(value = "评论类型", name = "answerId")
    private Integer type;

    @Column(name = "comment_content")
    @ApiModelProperty(value = "评论内容", name = "commentContent")
    private String commentContent;

    @Column(name = "comment_image_url")
    @ApiModelProperty(value = "评论图片", name = "commentImageUrl")
    private String commentImageUrl;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "update_time")
    private Date updateTime;
}