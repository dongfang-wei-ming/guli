package com.example.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.edu.entity.Chapter;
import com.example.edu.entity.Video;
import com.example.edu.entity.chapter.ChapterSelect;
import com.example.edu.entity.chapter.VideoSelect;
import com.example.edu.mapper.ChapterMapper;
import com.example.edu.mapper.VideoMapper;
import com.example.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.oss.config.exception.GuliException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public List<ChapterSelect> getChapterVideo(String courseId) {
        //章节集合
        QueryWrapper<Chapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        wrapper1.orderByAsc("sort");
        List<Chapter> chapters = baseMapper.selectList(wrapper1);
        //小结集合
        QueryWrapper<Video> wrapper2 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<Video> videos = videoMapper.selectList(wrapper2);

        List<ChapterSelect> ans = new ArrayList<>();
        for(Chapter c : chapters){
            ChapterSelect chapterSelect = new ChapterSelect();
            BeanUtils.copyProperties(c,chapterSelect);
            ans.add(chapterSelect);
            //章节接收对应小结
            for(Video v:videos){
                if( !v.getChapterId().equals(c.getId())) continue;
                VideoSelect videoSelect = new VideoSelect(v.getId(),v.getTitle(),v.getVideoSourceId());
                chapterSelect.getChildren().add(videoSelect);
            }
        }
        return ans;
    }

    @SneakyThrows
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        Integer count = videoMapper.selectCount(wrapper);
        if(count != 0) throw  new GuliException(20001,"不能删除");
        UpdateWrapper<Chapter> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("id",chapterId);
        int delete = baseMapper.delete(wrapper1);
        return delete > 0;
    }

    @Override
    public void deleteByCourseId(String id) {
        UpdateWrapper<Chapter> wrapper = new UpdateWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
