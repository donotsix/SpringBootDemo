package com.futurecode.springbootcors2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.io.File;

@RestController
@RequestMapping("file")
public class FileController {

    @RequestMapping("upload")
    public String upload(@RequestParam("futurecode_file") MultipartFile file) {

        if (file.isEmpty()) {
            return "文件为空";
        }

        String originalFilename = file.getOriginalFilename();
        System.out.println("上传文件名为:" + originalFilename);

        // String[] split = originalFilename.split(".");
        // String suffixName = split[split.length-1];
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 文件上传路径
        String filePath = "d:/undo/upload";

        File fileUpload = new File(filePath + originalFilename);
        if (!fileUpload.getParentFile().exists()) {
            fileUpload.getParentFile().mkdirs();
        }

        try {
            file.transferTo(fileUpload);
            return "上传成功";
        } catch  (Exception e) {
            e.printStackTrace();
        }

        return "上传失败";
    }

}
