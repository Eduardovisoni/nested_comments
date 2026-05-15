package com.team.trees.persistence.nosql;

import com.team.trees.engine.model.PlainNode;
import com.team.trees.persistence.TreeRepository;
import com.team.trees.persistence.nosql.entity.Neo4jCommentEntity;
import com.team.trees.persistence.nosql.jpa.Neo4jCommentSpringRepository;
import java.util.*;

public class Neo4jTreeRepository implements TreeRepository {

    private final Neo4jCommentSpringRepository repo;

    public Neo4jTreeRepository(Neo4jCommentSpringRepository repo) {
        this.repo = repo;
    }

    @Override
    public PlainNode save(PlainNode node) {
        Neo4jCommentEntity entity = new Neo4jCommentEntity();
        entity.setContent(node.value());
        if (node.parentId() != null) {
            repo.findById(node.parentId()).ifPresent(entity::setParent);
        }
        Neo4jCommentEntity saved = repo.save(entity);
        return toPlainNode(saved);
    }

    @Override
    public Optional<PlainNode> findById(Long id) {
        return repo.findById(id).map(this::toPlainNode);
    }

    @Override
    public List<PlainNode> findAll() {
        List<PlainNode> result = new ArrayList<>();
        repo.findAll().forEach(e -> result.add(toPlainNode(e)));
        return result;
    }

    @Override
    public List<PlainNode> findByParentId(Long parentId) {
        return findAll().stream()
            .filter(n -> Objects.equals(n.parentId(), parentId))
            .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    private PlainNode toPlainNode(Neo4jCommentEntity e) {
        Long parentId = e.getParent() != null ? e.getParent().getId() : null;
        return new PlainNode(e.getId(), e.getContent(), parentId);
    }
}