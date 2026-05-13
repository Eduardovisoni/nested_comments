package com.team.trees.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TreeResponse(
        Long id,
        String author,
        String content,
        LocalDateTime createdAt,
        List<TreeResponse> children
) {}
