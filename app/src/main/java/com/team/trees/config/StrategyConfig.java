package com.team.trees.config;

import com.team.trees.engine.TreeAlgorithmStrategy;
import com.team.trees.engine.collections.CollectionsTreeAlgorithm;
import com.team.trees.engine.custom.CustomTreeAlgorithm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {

    @Bean
    @ConditionalOnProperty(name = "app.tree-strategy", havingValue = "collections")
    public TreeAlgorithmStrategy collectionsStrategy() {
        return new CollectionsTreeAlgorithm();
    }

    @Bean
    @ConditionalOnProperty(name = "app.tree-strategy", havingValue = "custom", matchIfMissing = true)
    public TreeAlgorithmStrategy customStrategy() {
        return new CustomTreeAlgorithm();
    }
}
