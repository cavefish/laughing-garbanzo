package dev.cavefish.minipost.controller.mappers;

import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.tags.TagCreateRequest;
import dev.cavefish.minipost.dto.TagCreateDto;
import dev.cavefish.minipost.dto.TagDto;
import org.springframework.stereotype.Service;

@Service
public class TagsMapper {

    public TagDto toDto(Tag tag) {
        return new TagDto(tag.id(), tag.name());
    }

    public TagCreateRequest fromDto(TagCreateDto tagCreateDto) {
        return new TagCreateRequest(tagCreateDto.getName());
    }
}
