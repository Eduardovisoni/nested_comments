package com.team.trees.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequest(@NotBlank String author, @NotBlank String content) {}
