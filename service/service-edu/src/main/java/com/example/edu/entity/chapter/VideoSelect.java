package com.example.edu.entity.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 叶刚诚
 * @create 2022-01-04-16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoSelect {
    private String id;
    private String title;
    String videoSourceId;
}
