package com.example.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-07-16:24
 */

public interface VodService {
    String uploadVideo(MultipartFile file);

    void deleteVideo(String id);

    void removeMoreVideo(List videoIdList);
}
