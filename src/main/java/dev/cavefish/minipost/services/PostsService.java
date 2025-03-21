package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.posts.Post;
import dev.cavefish.minipost.domain.posts.PostCreateRequest;
import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostsService {
    public List<Post> searchPosts(String createdBy, String tags, LocalDateTime fromDate, LocalDateTime toDate) {
        return List.of(
                new Post(
                        new User("user-alias", "user-email", "user-firstname", "user-lastname"),
                        "post-title",
                        "post-content",
                        LocalDateTime.now(),
                        List.of(
                                new Tag(1, "tag-1"),
                                new Tag(2, "tag-2"),
                                new Tag(3, "tag-3")
                        )
                )
        );
    }

    public boolean create(PostCreateRequest postCreateRequest) {
        return false;
    }

    public boolean deletePost(int id) {
        return false;
    }
}
