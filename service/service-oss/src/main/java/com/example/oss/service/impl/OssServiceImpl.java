package com.example.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.oss.utils.ConstantProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 叶刚诚
 * @create 2022-01-01-13:32
 */
@Service
public class OssServiceImpl {
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantProperties.END_POINT;
        String accessKeyId = ConstantProperties.ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.BUCKET_NAME;

        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            //唯一文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String filename=uuid + file.getOriginalFilename();

            String date = new DateTime().toString("yyyy/MM/");
            filename = date + filename;
            ossClient.putObject(bucketName,
                    filename,//文件名
                    inputStream);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." +endpoint + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
    }
}
