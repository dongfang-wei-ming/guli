package com.example.edu.service.impl;

import com.alibaba.excel.EasyExcel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.edu.entity.EduSubject;
import com.example.edu.entity.excel.SubjectData;
import com.example.edu.entity.subject.levelOne;
import com.example.edu.entity.subject.levelTwo;
import com.example.edu.listener.SubjectExcelListener;
import com.example.edu.mapper.EduSubjectMapper;
import com.example.edu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<levelOne> getAllSubject() {
        //一级列表
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id",0);
        List<EduSubject> one = baseMapper.selectList(wrapper1);
        //二级列表
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id",0);
        List<EduSubject> two = baseMapper.selectList(wrapper2);
        //封装一级分类
        List<levelOne> finalList = new ArrayList<>();
        for(EduSubject s : one){
            levelOne res = new levelOne();
            BeanUtils.copyProperties(s,res);
            finalList.add(res);
            //一级分类中加入二级分类
            for(EduSubject edu : two){
                if(edu.getParentId().equals(res.getId())){
                    levelTwo l2 = new levelTwo();
                    BeanUtils.copyProperties(edu,l2);
                    res.getChildren().add(l2);
                }
            }

        }

        return finalList;
    }
}
