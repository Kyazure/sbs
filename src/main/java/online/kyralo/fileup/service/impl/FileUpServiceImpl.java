package online.kyralo.fileup.service.impl;

import online.kyralo.common.util.FileUpUtil;
import online.kyralo.fileup.service.FileUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * \* Created with IntelliJ IDEA.
 * \* @author: yeqi
 * \* Date: 19-7-13
 * \* Time: 上午9:39
 * \
 */

@Service
public class FileUpServiceImpl implements FileUpService {

    private final Environment environment;

    @Autowired
    public FileUpServiceImpl(Environment environment) {
        this.environment = environment;
    }

    private ResponseEntity<?> imageUp(MultipartFile file, String person, String usepath, String userId){

        String maxFileSize = environment.getProperty("spring.servlet.multipart.max-file-size");

        if (maxFileSize != null){
//            if (file.getSize() > Long.parseLong(maxFileSize)){
//
//
//                file = (MultipartFile)ImgUtil
//                        .scale((Image) file, (Long.parseLong(maxFileSize)) / (float) file.getSize()) ;
//
//            }


            String path = "upload/" + person + userId + "/images/" + usepath;
            if (FileUpUtil.singleFileUp(file,path)){
                return ResponseEntity.ok("upload/" + person + userId + "/images/" + usepath + file.getOriginalFilename());
            }else {
                return ResponseEntity.ok("上传失败!");
            }
        }else {
            return ResponseEntity.ok("文件最大限制");
        }
    }

    public ResponseEntity<?> videoStore(MultipartFile file, String path, String userId){

//        String maxFileSize = environment.getProperty("spring.servlet.multipart.max-file-size");

            // todo 视频格式转换成.flv格式

            String usePath = "upload/" + userId + path;
            if (FileUpUtil.singleFileUp(file,usePath)){
                return ResponseEntity.ok("upload/" + userId + path + file.getOriginalFilename());
            }else {
                return ResponseEntity.ok("上传失败!");
            }
    }

    @Override
    public ResponseEntity<?> avatarUpForUser(MultipartFile file, String userId) {
        return imageUp(file,"user/","avatar/img/", userId);
    }

    @Override
    public ResponseEntity<?> imageUpForVideo(MultipartFile file, String userId) {
        return imageUp(file,"videos/","cover/", userId);
    }

    @Override
    public ResponseEntity<?> videoUp(MultipartFile file, String userId) {
        return videoStore(file, "videos/", userId);
    }
}
