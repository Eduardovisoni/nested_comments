package com.team.trees.api.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String author,
        String content,
        LocalDateTime createdAt,
        Long parentId
) {}
