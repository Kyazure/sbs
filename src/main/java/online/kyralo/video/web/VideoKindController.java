package online.kyralo.video.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.video.domain.VideoKind;
import online.kyralo.video.service.VideoKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:58
 * \* Year: 2019
 * \
 */

@RestController
@RequestMapping("/api/v2/video_kind")
@Api("视频类型接口")
public class VideoKindController {

    @Autowired
    private VideoKindService service;

    public VideoKindController(VideoKindService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询视频类型 通过视频id", response = VideoKind.class)
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return service.queryVideoKindById(id);
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询所有视频类型", response = VideoKind.class)
    public ResponseEntity<?> getAll(){
        return service.listVideoKinds();
    }
}
