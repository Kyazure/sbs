package online.kyralo.video.dao;

import online.kyralo.video.domain.VideoKind;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeqi
 */
@Repository
public interface VideoKindMapper{

    /**
     * 查询视频类型信息 通过视频类型id
     * @param id 视频类型id
     * @return 视频类型信息
     */
    VideoKind queryVideoKindById(Integer id);

    /**
     * 查询所有视频类型
     * @return 视频类型列表
     */
    List<VideoKind> listVideoKinds();

    /**
     * 新增视频类型 todo 后台管理员使用
     * @param videoKind 视频类型
     * @return 是否新增成功
     */
    int insert(VideoKind videoKind);
}