package com.team.trees.config;

import com.team.trees.persistence.TreeRepository;
import com.team.trees.persistence.memory.MemoryTreeRepository;
import com.team.trees.persistence.nosql.Neo4jTreeRepository;
import com.team.trees.persistence.nosql.jpa.Neo4jCommentSpringRepository;
import com.team.trees.persistence.postgres.PostgresTreeRepository;
import com.team.trees.persistence.postgres.jpa.PostgresCommentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired(required = false)
    private Neo4jCommentSpringRepository neo4jRepo;

    @Autowired(required = false)
    private PostgresCommentJpaRepository postgresRepo;

    @Bean
    @ConditionalOnProperty(name = "app.storage", havingValue = "memory", matchIfMissing = true)
    public TreeRepository memoryRepository() {
        return new MemoryTreeRepository();
    }

    @Bean
    @ConditionalOnProperty(name = "app.storage", havingValue = "postgres")
    public TreeRepository postgresRepository() {
        return new PostgresTreeRepository(postgresRepo);
    }

    @Bean
    @ConditionalOnProperty(name = "app.storage", havingValue = "neo4j")
    public TreeRepository neo4jRepository() {
        return new Neo4jTreeRepository(neo4jRepo);
    }
}
