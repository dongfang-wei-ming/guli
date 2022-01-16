package com.example.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.client.OrderClient;
import com.example.edu.entity.Course;
import com.example.edu.entity.chapter.ChapterSelect;
import com.example.edu.entity.frontVo.CourseFrontVo;
import com.example.edu.entity.frontVo.CourseWebVo;
import com.example.edu.service.ChapterService;
import com.example.edu.service.CourseService;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import com.example.utils.orderVo.CourseWebVoOrder;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 叶刚诚
 * @create 2022-01-12-14:50
 */
@RestController
@RequestMapping("/eduservice/front/course/")

public class CourseFrontController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    @PostMapping("queryCourseListByLimit/{page}/{limit}")
    public R queryCourse(@RequestBody(required = false) CourseFrontVo courseFrontVo,
                         @PathVariable long page,
                         @PathVariable long limit){
        Page<Course> pageQuery = new Page<>(page,limit);
        Map<String,Object> map =courseService.getCourseList(pageQuery,courseFrontVo);
        return R.success().data(map);
    }

    @SneakyThrows
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id,
                           HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        CourseWebVo courseWebVo =courseService.getBaseCourseInfo(id);
        List<ChapterSelect> chapterVideo = chapterService.getChapterVideo(id);
        if(memberId == null) return R.success().data("course",courseWebVo).data("chapterVideo",chapterVideo)
                .data("isBuy",false);
        return R.success().data("course",courseWebVo).data("chapterVideo",chapterVideo)
                .data("isBuy",orderClient.isBuy(id,memberId));
    }
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseWebVo =courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
