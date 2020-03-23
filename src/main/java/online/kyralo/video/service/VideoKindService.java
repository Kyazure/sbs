package online.kyralo.video.service;

import org.springframework.http.ResponseEntity;

/**
 * @author yeqi
 */
public interface VideoKindService {

    /**
     * 查询视频类型信息 通过视频类型id
     * @param id 视频类型id
     * @return 视频类型信息
     */
    ResponseEntity<?> queryVideoKindById(Integer id);

    /**
     * 查询所有视频类型
     * @return 视频类型列表
     */
    ResponseEntity<?> listVideoKinds();
}
