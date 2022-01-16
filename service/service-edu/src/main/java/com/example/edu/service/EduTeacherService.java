package com.example.edu.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.edu.entity.EduTeacher;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getAwesomeTeacher();

    Map<String, Object> getFrontTeacherPage(Page<EduTeacher> page);
}
