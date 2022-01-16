package com.example.edu.mapper;

import com.example.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.edu.entity.frontVo.CourseWebVo;
import com.example.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
public interface CourseMapper extends BaseMapper<Course> {

    public CoursePublishVo getPublishCourseInfo(String courseId);


    CourseWebVo getBaseCourseInfo(String id);
}
