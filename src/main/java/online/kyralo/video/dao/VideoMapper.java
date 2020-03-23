package online.kyralo.video.dao;

import online.kyralo.video.domain.Video;
import online.kyralo.video.domain.VideoList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王宸
 */
@Repository
public interface VideoMapper{

    /**
     * 查询视频信息 通过视频id
     * @param id 视频id
     * @return 视频信息
     */
    Video queryVideoById(String id);

    /**
     * 获取视频 通过视频类型id
     * @param videoKindId 视频类型id
     * @return 视频
     */
    List<Video> listVideosByKind(Integer videoKindId);

    /**
     * 获取视频 通过视频类型id
     * @return 视频
     */
    List<VideoList> listVideos();

    /**
     * 新增视频
     * @param video 视频信息
     * @return 是否新增成功
     */
    int insert(Video video);

    /**
     * 更新视频信息
     * @param video 视频信息
     * @return 是否更新成功
     */
    int update(Video video);
}