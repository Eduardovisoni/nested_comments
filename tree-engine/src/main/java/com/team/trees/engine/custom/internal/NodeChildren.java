package com.team.trees.engine.custom.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Índice de hijos por parentId — ayuda a construir el árbol sin recorrer la lista completa cada vez
public class NodeChildren {

    private final Map<Long, List<TreeNode>> map = new HashMap<>();

    public void addChild(Long parentId, TreeNode child) {
        map.computeIfAbsent(parentId, k -> new ArrayList<>()).add(child);
    }

    public List<TreeNode> getChildren(Long parentId) {
        return map.getOrDefault(parentId, Collections.emptyList());
    }
}
