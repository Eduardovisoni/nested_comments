package com.team.trees.persistence.postgres;

import com.team.trees.engine.model.PlainNode;
import com.team.trees.persistence.TreeRepository;
import com.team.trees.persistence.postgres.entity.PostgresCommentEntity;
import com.team.trees.persistence.postgres.jpa.PostgresCommentJpaRepository;
import java.util.List;
import java.util.Optional;

public class PostgresTreeRepository implements TreeRepository {

    private final PostgresCommentJpaRepository jpa;

    public PostgresTreeRepository(PostgresCommentJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public PlainNode save(PlainNode node) {
        PostgresCommentEntity entity = toEntity(node);
        PostgresCommentEntity saved = jpa.save(entity);
        return toPlainNode(saved);
    }

    @Override
    public Optional<PlainNode> findById(Long id) {
        return jpa.findById(id).map(this::toPlainNode);
    }

    @Override
    public List<PlainNode> findAll() {
        return jpa.findAll().stream().map(this::toPlainNode).toList();
    }

    @Override
    public List<PlainNode> findByParentId(Long parentId) {
        return jpa.findByParentId(parentId).stream().map(this::toPlainNode).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    private PostgresCommentEntity toEntity(PlainNode node) {
        PostgresCommentEntity e = new PostgresCommentEntity();
        e.setId(node.id());
        e.setContent(node.value());
        e.setParentId(node.parentId());
        return e;
    }

    private PlainNode toPlainNode(PostgresCommentEntity e) {
        return new PlainNode(e.getId(), e.getContent(), e.getParentId());
    }
}