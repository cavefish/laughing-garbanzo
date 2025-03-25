package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.posts.Post;
import dev.cavefish.minipost.domain.posts.PostCreateRequest;
import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;
import dev.cavefish.minipost.entities.PostEntity;
import dev.cavefish.minipost.entities.TagEntity;
import dev.cavefish.minipost.entities.mappers.TagEntityMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        List<PostEntity> dbPosts = em.createQuery("select post from PostEntity post", PostEntity.class).getResultList();
        ArrayList<Post> posts = new ArrayList<>();

        for (PostEntity dbPost : dbPosts) {
            User user = new User("user-alias", "user-email", "user-firstname", "user-lastname");
            List<Tag> mappedTags = new ArrayList<>();
            for (TagEntity tagEntity : dbPost.getRelatedTags()) {
                mappedTags.add(tagEntityMapper.fromEntity(tagEntity));
            }
            Post post = new Post(
                    dbPost.getId(),
                    user, // TODO replace with a new column representing a many-to-many relationship
                    dbPost.getTitle(),
                    dbPost.getContent(),
                    dbPost.getCreatedAt(),
                    mappedTags
            );
            posts.add(post);
        }

        return posts;
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
