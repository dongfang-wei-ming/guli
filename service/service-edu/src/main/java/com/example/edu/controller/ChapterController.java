package com.example.edu.controller;


import com.example.edu.entity.Chapter;
import com.example.edu.entity.chapter.ChapterSelect;
import com.example.edu.service.ChapterService;
import com.example.edu.service.VideoService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@RestController
@RequestMapping("/eduservice/chapter")

public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;

    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterSelect> list= chapterService.getChapterVideo(courseId);
        return R.success().data("allChapterVideo",list);
    }
    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.success();
    }
    @GetMapping("getChapter/{chapterId}")
    public R getChapter(@PathVariable String chapterId){
        Chapter byId = chapterService.getById(chapterId);
        return R.success().data("chapter",byId);
    }

    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return R.success();
    }
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        chapterService.deleteChapter(chapterId);
        return R.success();
    }
}

