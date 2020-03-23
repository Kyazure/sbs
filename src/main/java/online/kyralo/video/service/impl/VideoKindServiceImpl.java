package online.kyralo.video.service.impl;

import online.kyralo.video.dao.VideoKindMapper;
import online.kyralo.video.domain.VideoKind;
import online.kyralo.video.service.VideoKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:55
 * \* Year: 2019
 * \
 */

@Service
public class VideoKindServiceImpl implements VideoKindService {

    @Autowired
    private VideoKindMapper mapper;

    public VideoKindServiceImpl(VideoKindMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> queryVideoKindById(Integer id) {
        if (id == null){
            return ResponseEntity.badRequest().build();
        }else {
            VideoKind videoKind = mapper.queryVideoKindById(id);
            if (videoKind != null){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @Override
    public ResponseEntity<?> listVideoKinds() {
        List<VideoKind> videoKinds = mapper.listVideoKinds();
        if (videoKinds.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(videoKinds);
        }
    }
}
