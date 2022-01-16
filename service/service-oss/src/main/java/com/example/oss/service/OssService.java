package com.example.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 叶刚诚
 * @create 2022-01-01-13:32
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
