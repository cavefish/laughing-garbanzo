package dev.cavefish.minipost.entities.mappers;

import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.tags.TagCreateRequest;
import dev.cavefish.minipost.domain.tags.TagNameNormalizer;
import dev.cavefish.minipost.entities.TagEntity;
import org.springframework.stereotype.Service;

@Service
public class TagEntityMapper {
    private TagNameNormalizer normalizer;

    public Tag fromEntity(TagEntity tagEntity) {
        return new Tag(tagEntity.getId(), tagEntity.getName());
    }

    public TagEntity toEntity(TagCreateRequest request) {
        return TagEntity.builder()
                .name(normalizer.normalize(request.name()))
                .build();
    }
}
