package com.example.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.edu.client.MemberClient;
import com.example.edu.entity.Comment;
import com.example.edu.service.CommentService;
import com.example.oss.config.exception.GuliException;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/eduservice/comment")

public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberClient memberClient;

    @GetMapping("{pageno}/{pagesize}")
    public R getComment(@PathVariable long pageno,
                        @PathVariable long pagesize,
                        @RequestParam String courseId){
        Page<Comment> page = new Page<>(pageno,pagesize);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        IPage<Comment> page1 = commentService.page(page, wrapper);
        return R.success().data("total",page.getTotal())
                .data("pages",page.getPages())
                .data("current",page.getCurrent())
                .data("hasPrevious",page.hasPrevious())
                .data("hasNext",page.hasNext())
                .data("items",page.getRecords());
    }

    @SneakyThrows
    @PostMapping("publishComment")
    public R publishComment(@RequestBody Comment comment,
                            HttpServletRequest request){
        boolean b = JwtUtils.checkToken(request);
        if(!b) throw  new GuliException(20001,"未登录不能评论");
        String id = JwtUtils.getMemberIdByJwtToken(request);
        R userInfo = memberClient.getUserInfo(id);
        String username = (String)userInfo.getData().get("username");
        String userAvatar = (String)userInfo.getData().get("userAvatar");
        comment.setAvatar(userAvatar);
        comment.setMemberId(id);
        comment.setNickname(username);
        commentService.save(comment);
        return R.success().data("id",comment.getId());
    }
}

