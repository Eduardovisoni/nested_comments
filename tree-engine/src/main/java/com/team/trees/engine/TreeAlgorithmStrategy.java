package com.team.trees.engine;

import com.team.trees.engine.model.PlainNode;
import java.util.List;

public interface TreeAlgorithmStrategy {

    TreeView    buildTree(List<PlainNode> nodes);
    TreeView    buildSubTree(List<PlainNode> nodes, Long rootId);

    List<Long>  pathToRoot(List<PlainNode> nodes, Long nodeId);

    List<Long>  dfs(List<PlainNode> nodes);
    List<Long>  bfs(List<PlainNode> nodes);

    int         height(List<PlainNode> nodes);
    int         depth(List<PlainNode> nodes, Long nodeId);

    List<Long>  ancestors(List<PlainNode> nodes, Long nodeId);

    boolean     hasNoCycles(List<PlainNode> nodes);
}
