package com.team.trees.domain;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private Long parentId;

    public Comment(Long id, String author, String content, LocalDateTime createdAt, Long parentId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.parentId = parentId;
    }

    public Long getId() { return id; }
    public String getAuthor() { return author; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getParentId() { return parentId; }

    public void setId(Long id) { this.id = id; }
    public void setAuthor(String author) { this.author = author; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
}
