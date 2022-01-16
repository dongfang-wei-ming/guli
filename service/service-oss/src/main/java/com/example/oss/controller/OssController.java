package com.example.oss.controller;

import com.example.oss.service.OssService;
import com.example.oss.service.impl.OssServiceImpl;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 叶刚诚
 * @create 2022-01-01-13:32
 */
@RestController
@RequestMapping("/eduoss/fileoss")

public class OssController {
    @Autowired
    private OssServiceImpl ossService;

    @PostMapping("/upload")
    public R uploadFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);

        return R.success().data("url",url);
    }
}
