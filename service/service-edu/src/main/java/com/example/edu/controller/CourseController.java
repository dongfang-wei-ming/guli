package com.example.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.entity.Course;
import com.example.edu.entity.vo.CourseInfoVo;
import com.example.edu.entity.vo.CoursePublishVo;
import com.example.edu.entity.vo.CourseQuery;
import com.example.edu.service.CourseService;
import com.example.oss.config.exception.GuliException;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@RestController
@RequestMapping("/eduservice/course")

public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("{page}/{limit}")
    public R course(@RequestBody CourseQuery courseQuery,
                    @PathVariable Long page,
                    @PathVariable Long limit){
        Page<Course> page1 = new Page<Course>(page,limit);

        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(courseQuery.getTitle() != null) wrapper.like("title",courseQuery.getTitle());
        if(courseQuery.getStatus() != null) wrapper.eq("status",courseQuery.getStatus());
        IPage<Course> page2 = courseService.page(page1, wrapper);
        return R.success().data("list",page2.getRecords())
                .data("total",page2.getTotal());
    }

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) throws GuliException {
        String id= courseService.addCourseInfo(courseInfoVo);
        return R.success().data("courseId",id);
    }

    @GetMapping("/getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id){
        CourseInfoVo course = courseService.findCourseById(id);
        return R.success().data("courseInfoVo",course);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) throws GuliException {
        courseService.updateCourseInfo(courseInfoVo);
        return R.success();
    }
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo courseConfirmInfo =courseService.getPublishCourseInfo(courseId);
        return R.success().data("info",courseConfirmInfo);
    }
    @GetMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){
        Course course = new Course();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.success();
    }
    @DeleteMapping("{id}")
    public  R deleteCourse(@PathVariable String id){
        courseService.removeCourse(id);
        return R.success();
    }

}

