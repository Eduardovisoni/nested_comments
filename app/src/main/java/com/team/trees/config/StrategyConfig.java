// StrategyConfig.java
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