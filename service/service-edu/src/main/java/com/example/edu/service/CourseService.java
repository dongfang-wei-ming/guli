package com.example.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.edu.entity.frontVo.CourseFrontVo;
import com.example.edu.entity.frontVo.CourseWebVo;
import com.example.edu.entity.vo.CourseInfoVo;
import com.example.edu.entity.vo.CoursePublishVo;
import com.example.oss.config.exception.GuliException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
public interface CourseService extends IService<Course> {

    String addCourseInfo(CourseInfoVo courseInfoVo) throws GuliException;

    CourseInfoVo findCourseById(String id);

    void updateCourseInfo(CourseInfoVo courseInfoVo) throws GuliException;


    CoursePublishVo getPublishCourseInfo(String courseId);


    void removeCourse(String id);

    List<Course> getHotCourse();

    Map<String, Object> getCourseList(Page<Course> pageQuery, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String id);
}
