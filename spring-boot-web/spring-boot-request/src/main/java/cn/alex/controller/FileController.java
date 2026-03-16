package cn.alex.controller;

import cn.alex.bean.UserInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by WCY on 2021/7/30
 */
@RestController
public class FileController {

    @PostMapping("fileupload")
    public String fileUpload(UserInfo userInfo) throws IOException {
        MultipartFile multipartFile = userInfo.getImage();
        // 模拟上传文件
        if (multipartFile != null) {
            multipartFile.transferTo(new File("d://back.jpg"));
        }
        System.out.println("userInfo = " + userInfo);
        return "success";
    }

    @PostMapping("fileDownload")
    public String fileDownload() {
        ClassPathResource classPathResource = new ClassPathResource("static/1.png");
        try (InputStream inputStream = classPathResource.getInputStream();
             OutputStream outputStream = new FileOutputStream("d://1.png")) {
            int len;
            byte[] bs = new byte[1024];
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
