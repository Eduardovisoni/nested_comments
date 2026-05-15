// StorageConfig.java
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