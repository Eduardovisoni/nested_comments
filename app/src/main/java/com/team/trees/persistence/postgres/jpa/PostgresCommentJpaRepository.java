package com.team.trees.persistence.postgres.jpa;

import com.team.trees.persistence.postgres.entity.PostgresCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostgresCommentJpaRepository extends JpaRepository<PostgresCommentEntity, Long> {

    List<PostgresCommentEntity> findByParentId(Long parentId);
}