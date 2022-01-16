package com.example.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-02-20:02
 */
@Data
public class levelOne {
    private String id;

    private String title;

    private List<levelTwo> children = new ArrayList<>();
}
