package dev.cavefish.minipost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PostDto {
    private int id;
    private UserDto createdBy;
    private String title;
    private String content;
    private List<TagDto> tags;
    private String createdAt;
}
