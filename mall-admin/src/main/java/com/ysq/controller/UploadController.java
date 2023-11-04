package com.ysq.controller;

import com.ysq.domain.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req) {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filePath = "D:\\桌面\\ShoeMallImages\\" + multipartFile.getOriginalFilename();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url =  req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort() +"/image/"+ multipartFile.getOriginalFilename();

        // replaceAll 用来替换windows中的\\ 为 /
        return ResponseResult.okResult(url);
    }
}
