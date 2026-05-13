package com.team.trees.persistence;

import com.team.trees.engine.model.PlainNode;
import java.util.List;
import java.util.Optional;

public interface TreeRepository {

    PlainNode           save(PlainNode node);

    Optional<PlainNode> findById(Long id);
    List<PlainNode>     findAll();
    List<PlainNode>     findByParentId(Long parentId);

    boolean             existsById(Long id);
    void                deleteById(Long id);
}
