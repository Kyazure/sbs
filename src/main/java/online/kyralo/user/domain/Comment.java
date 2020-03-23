package online.kyralo.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-27
 * \* Time: 0:32
 * \* Year: 2019
 * \
 */

@Data
public class Comment {
    @ApiModelProperty(value = "用户评论id", name = "id")
    private String id;

    @ApiModelProperty(value = "视频id", name = "videoId")
    private String videoId;

    @ApiModelProperty(value = "发送者id", name = "sendId")
    private String sendId;

    @ApiModelProperty(value = "发送者头像", name = "sendAvatarUrl")
    private String sendAvatarUrl;

    @ApiModelProperty(value = "发送者昵称", name = "sendName")
    private String sendName;

    @ApiModelProperty(value = "评论类型", name = "type")
    private Integer type;

    @ApiModelProperty(value = "评论内容", name = "commentContent")
    private String commentContent;

    @ApiModelProperty(value = "评论图片", name = "commentImageUrl")
    private String commentImageUrl;

    @ApiModelProperty(value = "点赞数", name = "countGives")
    private Integer countGives;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    @ApiModelProperty(value = "被回复的评论id", name = "answerId")
    private String answerId;

    @ApiModelProperty(value = "回复的评论内容", name = "answerCommentContent")
    private String answerCommentContent;

    @ApiModelProperty(value = "回复的评论图片", name = "answerCommentImageUrl")
    private String answerCommentImageUrl;

    @ApiModelProperty(value = "被回复者id", name = "answerSendId")
    private String answerSendId;

    @ApiModelProperty(value = "被回复者头像", name = "answerAvatarUrl")
    private String answerAvatarUrl;

    @ApiModelProperty(value = "被回复者昵称", name = "sendName")
    private String answerName;

}
