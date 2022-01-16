package com.example.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.entity.Course;
import com.example.edu.entity.EduTeacher;
import com.example.edu.service.CourseService;
import com.example.edu.service.EduTeacherService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 叶刚诚
 * @create 2022-01-12-12:35
 */
@RestController
@RequestMapping("/eduservice/front/teacher/")

public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private CourseService courseService;

    @GetMapping("page/{pageno}/{pagesize}")
    public R pageTeacher(@PathVariable Long pageno,
                         @PathVariable Long pagesize){
        Page<EduTeacher> page = new Page<>(pageno,pagesize);

        Map<String,Object> map = teacherService.getFrontTeacherPage(page);
        return R.success().data(map);
    }
    @GetMapping("info/{id}")
    public R getTeacherInfo(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        QueryWrapper<Course> wrapper =new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<Course> courseList = courseService.list(wrapper);
        return R.success().data("teacher",teacher).data("courseList",courseList);
    }
}
