package com.example.edu.controller;



import com.example.edu.entity.subject.levelOne;
import com.example.edu.service.EduSubjectService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return com.example.utils.R.success();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<levelOne> list= subjectService.getAllSubject();
        return R.success().data("list",list);
    }

}

