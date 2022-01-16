package com.example.edu.entity.vo;

import lombok.Data;

/**
 * @author 叶刚诚
 * @create 2022-01-04-22:02
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
