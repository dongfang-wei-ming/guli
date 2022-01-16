package com.example.edu.controller.front;

import com.example.edu.entity.Course;
import com.example.edu.entity.EduTeacher;
import com.example.edu.service.CourseService;
import com.example.edu.service.EduTeacherService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-09-15:21
 */
@RestController
@RequestMapping("eduservice/indexfront")

public class IndexFrontController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("index")
    public R getIndex(){
        List<Course> courseList = courseService.getHotCourse();
        List<EduTeacher> teacherList= teacherService.getAwesomeTeacher();
        return R.success().data("eduList",courseList).data("teacherList",teacherList);
    }

}
