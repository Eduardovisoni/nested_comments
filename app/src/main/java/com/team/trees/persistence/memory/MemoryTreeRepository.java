package com.team.trees.persistence.memory;

import com.team.trees.engine.model.PlainNode;
import com.team.trees.persistence.TreeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// Sin @Component — el wiring lo hace Abner en StorageConfig
public class MemoryTreeRepository implements TreeRepository {

    private final Map<Long, PlainNode> store = new HashMap<>();
    private long nextId = 1;

    @Override
    public PlainNode save(PlainNode node) {
        Long id = node.id() != null ? node.id() : nextId++;
        PlainNode toStore = new PlainNode(id, node.value(), node.parentId());
        store.put(id, toStore);
        return toStore;
    }

    @Override
    public Optional<PlainNode> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<PlainNode> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<PlainNode> findByParentId(Long parentId) {
        return store.values().stream()
                .filter(n -> Objects.equals(n.parentId(), parentId))
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
