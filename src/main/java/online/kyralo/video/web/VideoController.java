package online.kyralo.video.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.common.util.JwtUtil;
import online.kyralo.video.domain.Video;
import online.kyralo.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:57
 * \* Year: 2019
 * \
 */

@RestController
@RequestMapping("/api/v2/video")
@Api("视频信息接口")
public class VideoController {

    @Autowired
    private VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @GetMapping("/id/{id}")
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询视频信息 通过视频id", response = Video.class)
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return service.queryVideoById(id);
    }

    @GetMapping("/kind/{kind_id}")
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询视频信息 通过视频类型id", response = Video.class)
    public ResponseEntity<?> listVideosByKind(@PathVariable("kind_id") Integer videoKindId) {
       return service.listVideosByKind(videoKindId);
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询视频信息", response = Video.class)
    public ResponseEntity<?> listVideos() {
        return service.listVideos();
    }

    @PostMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "新增视频", response = Video.class)
    public ResponseEntity<?> insert(@RequestBody Video video, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        video.setAuthorId(userId);
        return service.insert(video);
    }

    @PutMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "更新视频信息 通过视频id", response = Video.class)
    public ResponseEntity<?> update(@RequestBody Video video, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        video.setAuthorId(userId);
        return service.insert(video);
    }
}
