package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.tags.TagCreateRequest;
import dev.cavefish.minipost.domain.tags.TagNameNormalizer;
import dev.cavefish.minipost.entities.PostEntity;
import dev.cavefish.minipost.entities.TagEntity;
import dev.cavefish.minipost.entities.mappers.TagEntityMapper;
import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TagsService {

    @PersistenceContext
    private EntityManager em;

    private TagEntityMapper tagEntityMapper;

    private TagNameNormalizer tagNameNormalizer;

    public List<Tag> getTags() {
        return em.createQuery("select t from TagEntity t", TagEntity.class)
                .getResultStream()
                .map(tagEntityMapper::fromEntity)
                .toList();
    }

    @Transactional
    public boolean deleteTag(int id) {
        TagEntity tagEntity = em.find(TagEntity.class, id);
        if (tagEntity == null) {
            return false;
        }
        em.remove(tagEntity);
        // Manually propagate deletion for many2many
        for (PostEntity relatedPost : tagEntity.getRelatedPosts()) {
            relatedPost.getRelatedTags().remove(tagEntity);
        }
        return true;
    }

    @Transactional
    public boolean createTag(TagCreateRequest request) {
        TagEntity entity = tagEntityMapper.toEntity(request);
        em.persist(entity);
        return true;
    }

    // upsert = update & insert = try to find, else create if missing
    TagEntity upsertTagEntity(String tagName) {
        String normalizedTagName = tagNameNormalizer.normalize(tagName);
        if (normalizedTagName == null) return null;
        if (normalizedTagName.isBlank()) return null;
        List<TagEntity> results = em.createQuery("select t from TagEntity t where t.name = :name", TagEntity.class)
                .setParameter("name", normalizedTagName)
                .setMaxResults(1)
                .getResultList();
        if (!results.isEmpty()) return results.get(0);
        TagEntity tagToCreate = TagEntity.builder().name(normalizedTagName).build();
        return em.merge(tagToCreate);
    }
}
