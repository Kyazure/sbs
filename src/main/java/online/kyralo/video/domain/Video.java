package online.kyralo.video.domain;

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
@Table(name = "video")
@ApiModel(value = "video", description = "视频")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "视频id", name = "id")
    private String id;

    @Column(name = "author_id")
    @ApiModelProperty(value = "作者id", name = "authorId")
    private String authorId;

    @Column(name = "kind_id")
    @ApiModelProperty(value = "视频类型", name = "kindId")
    private Integer kindId;

    @ApiModelProperty(value = "视频标题", name = "title")
    private String title;

    @ApiModelProperty(value = "视频介绍", name = "introduction")
    private String introduction;

    @Column(name = "cover_url")
    @ApiModelProperty(value = "视频封面地址", name = "coverUrl")
    private String coverUrl;

    @Column(name = "video_url")
    @ApiModelProperty(value = "视频地址", name = "videoUrl")
    private String videoUrl;

    @ApiModelProperty(value = "视频状态", name = "fettle")
    private String fettle;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "update_time")
    private Date updateTime;
}