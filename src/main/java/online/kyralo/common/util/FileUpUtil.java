package online.kyralo.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: WangChen
 * \* Date: 19-7-25
 * \* Time: 下午8:45
 * \
 */
@Component
public class FileUpUtil {

    /**
     *  单文件上传到本地 */
    public static Boolean singleFileUp(MultipartFile file,String path){

        File dest = new File(path);

        //保存文件
        //父目录不存在就创建一个
        boolean isDirectoryCreated = dest.exists() || dest.mkdirs();

        if (isDirectoryCreated){

            File newFile = new File(dest.getAbsolutePath(), Objects.requireNonNull(file.getOriginalFilename()));
            try {
                file.transferTo(newFile);
                return true;
            } catch (IOException e) {
                return false;
            }
        }else {
            return false;
        }

    }

}
