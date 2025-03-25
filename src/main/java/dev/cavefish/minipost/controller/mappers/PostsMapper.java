package dev.cavefish.minipost.controller.mappers;

import dev.cavefish.minipost.domain.posts.Post;
import dev.cavefish.minipost.domain.posts.PostCreateRequest;
import dev.cavefish.minipost.dto.PostCreateDto;
import dev.cavefish.minipost.dto.PostDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostsMapper {
    private UsersMapper usersMapper;
    private TagsMapper tagsMapper;
    private DateMapper dateMapper;

    public PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.id().toString())
                .createdBy(usersMapper.toDto(post.createdBy()))
                .title(post.title())
                .content(post.content())
                .tags(post.tags().stream().map(tagsMapper::toDto).toList())
                .createdAt(dateMapper.toDto(post.createdAt()))
                .build();
    }

    public PostCreateRequest fromDto(PostCreateDto postCreateDto) {
        return PostCreateRequest.builder()
                .createdByAlias(postCreateDto.getCreatedByAlias())
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .tags(postCreateDto.getTags())
                .build();
    }
}
