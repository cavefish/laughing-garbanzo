package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.posts.Post;
import dev.cavefish.minipost.domain.posts.PostCreateRequest;
import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;
import dev.cavefish.minipost.entities.PostEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostsService {

    @PersistenceContext
    private EntityManager em;

    public List<Post> searchPosts(String createdBy, String tags, LocalDateTime fromDate, LocalDateTime toDate) {
        List<PostEntity> dbPosts = em.createQuery("select post from PostEntity post", PostEntity.class).getResultList();
        ArrayList<Post> posts = new ArrayList<>();

        for (PostEntity dbPost : dbPosts) {
            Post post = new Post(
                    dbPost.getId(),
                    new User("user-alias", "user-email", "user-firstname", "user-lastname"),
                    dbPost.getTitle(),
                    dbPost.getContent(),
                    dbPost.getCreatedAt(),
                    List.of(
                            new Tag(1, "tag-1"),
                            new Tag(2, "tag-2"),
                            new Tag(3, "tag-3")
                    )
            );
            posts.add(post);
        }

        return posts;
    }

    @Transactional
    public boolean create(PostCreateRequest postCreateRequest) {
        try {
            PostEntity postToCreate = PostEntity.builder()
                    .id(UUID.randomUUID())
                    .title(postCreateRequest.title())
                    .content(postCreateRequest.content())
                    .createdAt(LocalDateTime.now())
                    .build();
            em.persist(postToCreate);
        } catch (EntityExistsException e) {
            return false;
        }
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
