package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.posts.Post;
import dev.cavefish.minipost.domain.posts.PostCreateRequest;
import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;
import dev.cavefish.minipost.entities.PostEntity;
import dev.cavefish.minipost.entities.TagEntity;
import dev.cavefish.minipost.entities.mappers.TagEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class PostsService {

    @PersistenceContext
    private EntityManager em;

    private TagEntityMapper tagEntityMapper;

    private TagsService tagsService;

    @Transactional
    public List<Post> searchPosts(String createdBy, String tags, LocalDateTime fromDate, LocalDateTime toDate) {
        List<PostEntity> dbPosts = searchPostEntities(createdBy, tags, fromDate, toDate);
        ArrayList<Post> posts = new ArrayList<>();

        for (PostEntity dbPost : dbPosts) {
            User user = new User("user-alias", "user-email", "user-firstname", "user-lastname");
            List<Tag> mappedTags = new ArrayList<>();
            for (TagEntity tagEntity : dbPost.getRelatedTags()) {
                mappedTags.add(tagEntityMapper.fromEntity(tagEntity));
            }
            Post post = new Post(
                    dbPost.getId(),
                    user, // TODO replace with a new column representing a many-to-one relationship
                    dbPost.getTitle(),
                    dbPost.getContent(),
                    dbPost.getCreatedAt(),
                    mappedTags
            );
            posts.add(post);
        }

        return posts;
    }

    private List<PostEntity> searchPostEntities(String createdBy, String tags, LocalDateTime fromDate, LocalDateTime toDate) {
        if (createdBy == null && tags == null && fromDate == null && toDate == null) {
            return em.createQuery("select post from PostEntity post", PostEntity.class).getResultList();
        }
        // TODO implement the rest of query parameters
        List<Object[]> queryParameters = new ArrayList<>();
        if (createdBy != null) {
            // TODO implement
        }
        if (tags != null) {
            queryParameters.add(new Object[] {"tag.name in :tagvalues", "tagvalues", Arrays.stream(tags.split(" ")).toList()});
        }
        if (fromDate != null) {
            queryParameters.add(new Object[]{"post.createdAt >= :fromdate", "fromdate", fromDate});
        }
        if (toDate != null) {
            // TODO implement
        }
        StringBuilder baseQuery = getQuery(queryParameters);
        TypedQuery<PostEntity> query = em.createQuery(baseQuery.toString(), PostEntity.class);
        for (Object[] queryParameter : queryParameters) {
            String paramName = (String) queryParameter[1];
            Object paramValue = queryParameter[2];
            query.setParameter(paramName, paramValue);
        }
        return query.getResultList();
    }

    private static StringBuilder getQuery(List<Object[]> queryParameters) {
        StringBuilder baseQuery = new StringBuilder("select post from PostEntity post join post.relatedTags tag");
        int addedParameters = 0;
        for (Object[] queryParameter : queryParameters) {
            String queryPart = (String) queryParameter[0];
            if (addedParameters == 0) {
                baseQuery.append(" WHERE ");
            } else {
                baseQuery.append(" AND ");
            }
            baseQuery.append(queryPart);
            addedParameters++;
        }
        return baseQuery;
    }

    @Transactional
    public boolean create(PostCreateRequest postCreateRequest) {
        String[] tagNames = postCreateRequest.tags().split(" ");
        Set<TagEntity> tags = new HashSet<>();
        for (String tagName : tagNames) {
            TagEntity tagEntity = tagsService.upsertTagEntity(tagName);
            if (tagEntity != null) {
                tags.add(tagEntity);
            }
        }
        PostEntity postToCreate = PostEntity.builder()
                .title(postCreateRequest.title())
                .content(postCreateRequest.content())
                .createdAt(LocalDateTime.now())
                .build();
        PostEntity createdPost = em.merge(postToCreate);
        createdPost.getRelatedTags().addAll(tags);
        em.merge(createdPost);
        return true;
    }

    @Transactional
    public boolean deletePost(UUID id) {
        PostEntity postEntity = em.find(PostEntity.class, id);
        if (postEntity == null) return false;
        em.remove(postEntity);
        return true;
    }
}
