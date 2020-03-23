package online.kyralo.video.service;

import online.kyralo.video.domain.Video;
import org.springframework.http.ResponseEntity;


/**
 * @author yeqi
 */
public interface VideoService {
    /**
     * 查询视频信息 通过视频id
     * @param id 视频id
     * @return 视频信息
     */
    ResponseEntity<?> queryVideoById(String id);

    /**
     * 获取视频 通过视频类型id
     * @return 视频
     */
    ResponseEntity<?> listVideos();

    /**
     * 获取视频 通过视频类型id
     * @param videoKindId 视频类型id
     * @return 视频
     */
    ResponseEntity<?>  listVideosByKind(Integer videoKindId);


    /**
     * 新增视频
     * @param video 视频信息
     * @return 新增的状态
     */
    ResponseEntity<?> insert(Video video);

    /**
     * 更新视频信息
     * @param video 视频信息
     * @return 更新的状态
     */
    ResponseEntity<?> update(Video video);
}
