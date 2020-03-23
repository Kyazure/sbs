package online.kyralo.fileup.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.common.util.JwtUtil;
import online.kyralo.fileup.service.FileUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: yeqi
 * \* Date: 19-7-13
 * \* Time: 上午9:39
 * \
 */
@RestController
@RequestMapping("/api/v2/file_up")
@Api("文件上传接口")
public class FileUpController {

    private final FileUpService service;

    @Autowired
    public FileUpController(FileUpService service) {
        this.service = service;
    }


    @PostMapping("/user/images")
    @ApiOperation(value = "用户头像上传")
    @Secured("ROLE_USER")
    public ResponseEntity<?> avatarUpForUser(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        return service.avatarUpForUser(file, userId);
    }

    @PostMapping("/video/image")
    @ApiOperation(value = "视频图片上传")
    @Secured("ROLE_USER")
    public ResponseEntity<?> imageUpForVideo(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        return service.imageUpForVideo(file, userId);
    }

    @PostMapping("/user/videos")
    @ApiOperation(value = "视频上传")
    @Secured("ROLE_USER")
    public ResponseEntity<?> videoUp(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        return service.videoUp(file, userId);
    }

}
