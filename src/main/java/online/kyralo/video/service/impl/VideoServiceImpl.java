package online.kyralo.video.service.impl;

import online.kyralo.video.dao.VideoMapper;
import online.kyralo.video.domain.Video;
import online.kyralo.video.domain.VideoList;
import online.kyralo.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:41
 * \* Year: 2019
 * \
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper mapper;

    public VideoServiceImpl(VideoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> queryVideoById(String id) {
        if ("".equals(id)){
            return ResponseEntity.badRequest().build();
        }else {
            Video video = mapper.queryVideoById(id);
            if (video != null){
                return ResponseEntity.ok(video);
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @Override
    public ResponseEntity<?> listVideosByKind(Integer videoKindId) {
        if (videoKindId == null){
            return ResponseEntity.badRequest().build();
        }else {
            List<Video> videos = mapper.listVideosByKind(videoKindId);

            if (!videos.isEmpty()){
                return ResponseEntity.ok(videos);
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @Override
    public ResponseEntity<?> listVideos() {
        List<VideoList> videos = mapper.listVideos();
        if (videos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(videos);
        }
    }

    @Override
    public ResponseEntity<?> insert(Video video) {
        if ("".equals(video.getTitle()) ||
                "".equals(video.getVideoUrl()) ||
                "".equals(video.getCoverUrl()) ||
                "".equals(video.getAuthorId())){
            return ResponseEntity.badRequest().body("必要内容不能为空!");
        }else {
            if (mapper.insert(video) == 1){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @Override
    public ResponseEntity<?> update(Video video) {
        if ("".equals(video.getAuthorId()) || "".equals(video.getId())){
            return ResponseEntity.badRequest().body("必要内容不能为空!");
        }else {
            if (mapper.update(video) == 1){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
        }
    }
}
