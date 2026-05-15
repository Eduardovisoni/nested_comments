package com.team.trees.persistence.nosql.jpa;

import com.team.trees.persistence.nosql.entity.Neo4jCommentEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.List;

public interface Neo4jCommentSpringRepository extends Neo4jRepository<Neo4jCommentEntity, Long> {

    List<Neo4jCommentEntity> findByParentId(Long parentId);
}