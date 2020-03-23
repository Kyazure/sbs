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
@Table(name = "user_collection")
@ApiModel(value = "userCollection", description = "用户收藏")
public class UserCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户收藏id", name = "id")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id", name = "userId")
    private String userId;

    @Column(name = "video_id")
    @ApiModelProperty(value = "视频id", name = "videoId")
    private String videoId;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    @Column(name = "update_time")
    private Date updateTime;

}