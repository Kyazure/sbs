package online.kyralo.common.util;

/**
 * \* Created with Intellij IDEA.
 * \* @author: WangChen
 * \* Date: 2019-12-27
 * \* Time: 2:21
 * \* Year: 2019
 * \* Description: TODO
 * \
 */

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FfmpegUtil {

//    executeCodecs("G:\\Program Files\\ffmpeg-20190702-231d0c8-win64-static\\bin\\ffmpeg.exe",
//    "C:\\images\\201907\\12346.mp4", "C:\\images\\201907\\1111.flv",
//    "C:\\images\\201907\\12346.jpg");


    /**
     * 视频转码
     * @param ffmpegPath    转码工具的存放路径
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath    格式转换后的的文件保存路径
     * @param mediaPicPath    截图保存路径
     */
    public static void executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
                                     String mediaPicPath) {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<>();
        // 添加转换工具路径
        convert.add(ffmpegPath);
        // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add("-i");
        // 添加要转换格式的视频文件的路径
        convert.add(upFilePath);
        //指定转换的质量
        convert.add("-qscale");
        convert.add("6");
        //设置音频码率
        convert.add("-ab");
        convert.add("64");
        //设置声道数
        convert.add("-ac");
        convert.add("2");
        //设置声音的采样频率
        convert.add("-ar");
        convert.add("22050");
        //设置帧频
        convert.add("-r");
        convert.add("24");
        // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add("-y");
        convert.add(codcFilePath);
        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add(upFilePath);
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("-ss");
        // 添加起始时间为第2秒
        cutpic.add("2");
        // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("-t");
        // 添加持续时间为1毫秒
        cutpic.add("0.001");
        // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("-s");
        // 添加截取的图片大小为350*240
        cutpic.add("800*280");
        // 添加截取的图片的保存路径
        cutpic.add(mediaPicPath);
        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();

            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            Process process = builder.start();
            InputStream is = process.getInputStream();
            InputStreamReader inst = new InputStreamReader(is, "GBK");

            //输入流缓冲区
            BufferedReader br = new BufferedReader(inst);
            String res;
            StringBuilder sb = new StringBuilder();

            //循环读取缓冲区中的数据
            while ((res = br.readLine()) != null) {
                sb.append(res).append("\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
