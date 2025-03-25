package dev.cavefish.minipost.domain.posts;

import dev.cavefish.minipost.domain.tags.Tag;
import dev.cavefish.minipost.domain.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Post(UUID id, User createdBy, String title, String content, LocalDateTime createdAt, List<Tag> tags) {
}
