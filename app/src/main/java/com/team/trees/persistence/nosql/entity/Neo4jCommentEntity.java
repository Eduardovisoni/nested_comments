package com.team.trees.persistence.nosql.entity;

import org.springframework.data.neo4j.core.schema.*;
import java.time.LocalDateTime;

@Node("Comment")
public class Neo4jCommentEntity {
	@Id
    @GeneratedValue
    private Long id;

    private String author;
    private String content;

    @Property("created_at")
    private LocalDateTime createdAt;

    @Property("parent_id")
    private Long parentId;

    @Relationship(type = "REPLY_TO", direction = Relationship.Direction.OUTGOING)
    private Neo4jCommentEntity parent;

    public Neo4jCommentEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }

    public Neo4jCommentEntity getParent() { return parent; }
    public void setParent(Neo4jCommentEntity parent) { this.parent = parent; }
}
