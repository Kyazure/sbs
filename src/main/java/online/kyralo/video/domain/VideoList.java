package online.kyralo.video.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2020-01-02
 * \* Time: 11:49
 * \* Year: 2020
 * \
 */

@Data
public class VideoList {
    @ApiModelProperty(value = "视频id", name = "id")
    private String id;

    @ApiModelProperty(value = "作者id", name = "authorId")
    private String authorId;

    private String authorAvatar;

    private String authorName;

    @ApiModelProperty(value = "视频类型", name = "kindId")
    private Integer kindId;

    @ApiModelProperty(value = "视频标题", name = "title")
    private String title;

    @ApiModelProperty(value = "视频介绍", name = "introduction")
    private String introduction;

    @ApiModelProperty(value = "视频封面地址", name = "coverUrl")
    private String coverUrl;

    @ApiModelProperty(value = "视频地址", name = "videoUrl")
    private String videoUrl;

    @ApiModelProperty(value = "视频状态", name = "fettle")
    private String fettle;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date createTime;
}
