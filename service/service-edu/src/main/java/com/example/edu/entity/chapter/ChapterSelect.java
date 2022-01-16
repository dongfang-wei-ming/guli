package com.example.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-04-16:41
 */
@Data
public class ChapterSelect {
    private String id;
    private String title;
    private List<VideoSelect> children = new ArrayList<>();

}
