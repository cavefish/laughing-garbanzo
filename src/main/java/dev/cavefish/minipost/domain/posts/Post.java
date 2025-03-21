package dev.cavefish.minipost.domain.posts;

import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;

import java.time.LocalDateTime;
import java.util.List;

public record Post(User createdBy, String title, String content, LocalDateTime createdAt, List<Tag> tags) {
}
