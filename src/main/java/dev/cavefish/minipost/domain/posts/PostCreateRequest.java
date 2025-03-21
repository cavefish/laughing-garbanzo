package dev.cavefish.minipost.domain.posts;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostCreateRequest(String createdByAlias, String title, String content, LocalDateTime createdAt, String tags) {
}
