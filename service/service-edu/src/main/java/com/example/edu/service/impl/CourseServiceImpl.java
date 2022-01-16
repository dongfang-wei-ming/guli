package com.example.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.entity.Course;
import com.example.edu.entity.CourseDescription;
import com.example.edu.entity.frontVo.CourseFrontVo;
import com.example.edu.entity.frontVo.CourseWebVo;
import com.example.edu.entity.vo.CourseInfoVo;
import com.example.edu.entity.vo.CoursePublishVo;
import com.example.edu.mapper.CourseMapper;
import com.example.edu.service.ChapterService;
import com.example.edu.service.CourseDescriptionService;
import com.example.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.edu.service.VideoService;
import com.example.oss.config.exception.GuliException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) throws GuliException {
        Course course =new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int i = baseMapper.insert(course);
        if(i <= 0) throw new GuliException(20001,"课程添加失败");

        String id = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setId(id);
        courseDescriptionService.save(courseDescription);
        return id;
    }

    @Override
    public CourseInfoVo findCourseById(String id) {
        CourseInfoVo courseInfoVo =new CourseInfoVo();

        Course course = baseMapper.selectById(id);
        BeanUtils.copyProperties(course,courseInfoVo);

        CourseDescription one = courseDescriptionService.getById(id);
        BeanUtils.copyProperties(one,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) throws GuliException {
        Course course =new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int i = baseMapper.updateById(course);

        if(i <= 0) throw new GuliException(20001,"修改课程失败");

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @SneakyThrows
    @Override
    public void removeCourse(String id) {
        //小节
        videoService.deleteByCourseId(id);
        //章节
        chapterService.deleteByCourseId(id);
        //简介
        courseDescriptionService.removeById(id);
        //课程
        int res = baseMapper.deleteById(id);
        if(res == 0 ) throw new GuliException(20001,"删除失败");
    }

    @Override
    public List<Course> getHotCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Map<String, Object> getCourseList(Page<Course> pageParam, CourseFrontVo courseFrontVo) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        String buyCountSort = courseFrontVo.getBuyCountSort();
        String priceSort = courseFrontVo.getPriceSort();
        String gmtCreateSort = courseFrontVo.getGmtCreateSort();
        String subjectId = courseFrontVo.getSubjectId();
        //String title = courseFrontVo.getTitle();
        String subjectParentId = courseFrontVo.getSubjectParentId();
        //String teacherId = courseFrontVo.getTeacherId();
        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId))wrapper.eq("subject_id",subjectId);
        if(!StringUtils.isEmpty(buyCountSort))wrapper.orderByDesc("buy_count");
        if(!StringUtils.isEmpty(priceSort))wrapper.orderByDesc("price");
        if(!StringUtils.isEmpty(gmtCreateSort))wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageParam, wrapper);

        List<Course> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;


    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
         CourseWebVo courseWebVo=baseMapper.getBaseCourseInfo(id);
        return courseWebVo;
    }


}
