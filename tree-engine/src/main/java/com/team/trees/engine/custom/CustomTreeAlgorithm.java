package com.team.trees.engine.custom;

import com.team.trees.engine.TreeAlgorithmStrategy;
import com.team.trees.engine.TreeView;
import com.team.trees.engine.custom.internal.NodeChildren;
import com.team.trees.engine.custom.internal.TreeNode;
import com.team.trees.engine.model.PlainNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Sin @Component — el wiring lo hace Abner en StrategyConfig
public class CustomTreeAlgorithm implements TreeAlgorithmStrategy {

    @Override
    public TreeView buildTree(List<PlainNode> nodes) {
        if (nodes.isEmpty()) return null;

        Map<Long, TreeNode> nodeMap = new HashMap<>();
        for (PlainNode p : nodes) {
            nodeMap.put(p.id(), new TreeNode(p.id(), p.value()));
        }

        NodeChildren nodeChildren = new NodeChildren();
        List<TreeNode> roots = new ArrayList<>();

        for (PlainNode p : nodes) {
            if (p.parentId() == null) {
                roots.add(nodeMap.get(p.id()));
            } else {
                nodeChildren.addChild(p.parentId(), nodeMap.get(p.id()));
            }
        }

        for (TreeNode root : roots) {
            attachChildren(root, nodeChildren);
        }

        return roots.isEmpty() ? null : toTreeView(roots.get(0));
    }

    @Override
    public TreeView buildSubTree(List<PlainNode> nodes, Long rootId) {
        TreeView fullTree = buildTree(nodes);
        if (fullTree == null) return null;
        return findInTree(fullTree, rootId);
    }

    @Override
    public List<Long> pathToRoot(List<PlainNode> nodes, Long nodeId) {
        Map<Long, Long> parentMap = buildParentMap(nodes);
        List<Long> path = new ArrayList<>();
        Long current = nodeId;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        return path;
    }

    @Override
    public List<Long> dfs(List<PlainNode> nodes) {
        TreeView tree = buildTree(nodes);
        if (tree == null) return List.of();
        List<Long> result = new ArrayList<>();
        dfsTraversal(tree, result);
        return result;
    }

    @Override
    public List<Long> bfs(List<PlainNode> nodes) {
        TreeView tree = buildTree(nodes);
        if (tree == null) return List.of();

        // Cola implementada con ArrayList + índice de frente (sin ArrayDeque)
        List<Long> result = new ArrayList<>();
        List<TreeView> queue = new ArrayList<>();
        queue.add(tree);
        int front = 0;
        while (front < queue.size()) {
            TreeView current = queue.get(front++);
            result.add(current.id());
            queue.addAll(current.children());
        }
        return result;
    }

    @Override
    public int height(List<PlainNode> nodes) {
        if (nodes.isEmpty()) return 0;
        TreeView tree = buildTree(nodes);
        if (tree == null) return 0;
        return computeHeight(tree);
    }

    @Override
    public int depth(List<PlainNode> nodes, Long nodeId) {
        Map<Long, Long> parentMap = buildParentMap(nodes);
        int depth = 0;
        Long current = nodeId;
        while (parentMap.containsKey(current)) {
            current = parentMap.get(current);
            depth++;
        }
        return depth;
    }

    @Override
    public List<Long> ancestors(List<PlainNode> nodes, Long nodeId) {
        Map<Long, Long> parentMap = buildParentMap(nodes);
        List<Long> ancestors = new ArrayList<>();
        Long current = parentMap.get(nodeId);
        while (current != null) {
            ancestors.add(current);
            current = parentMap.get(current);
        }
        return ancestors;
    }

    @Override
    public boolean hasNoCycles(List<PlainNode> nodes) {
        Map<Long, Long> parentMap = buildParentMap(nodes);
        for (PlainNode p : nodes) {
            Set<Long> visited = new HashSet<>();
            Long current = p.id();
            while (current != null) {
                if (!visited.add(current)) return false;
                current = parentMap.get(current);
            }
        }
        return true;
    }

    // --- helpers privados ---

    private void attachChildren(TreeNode node, NodeChildren nodeChildren) {
        for (TreeNode child : nodeChildren.getChildren(node.getId())) {
            node.addChild(child);
            attachChildren(child, nodeChildren);
        }
    }

    private TreeView toTreeView(TreeNode node) {
        List<TreeView> children = new ArrayList<>();
        for (TreeNode child : node.getChildren()) {
            children.add(toTreeView(child));
        }
        return new TreeView(node.getId(), node.getValue(), children);
    }

    private TreeView findInTree(TreeView node, Long targetId) {
        if (node.id().equals(targetId)) return node;
        for (TreeView child : node.children()) {
            TreeView found = findInTree(child, targetId);
            if (found != null) return found;
        }
        return null;
    }

    private void dfsTraversal(TreeView node, List<Long> result) {
        result.add(node.id());
        for (TreeView child : node.children()) {
            dfsTraversal(child, result);
        }
    }

    private int computeHeight(TreeView node) {
        if (node.children().isEmpty()) return 1;
        int maxChild = 0;
        for (TreeView child : node.children()) {
            int h = computeHeight(child);
            if (h > maxChild) maxChild = h;
        }
        return 1 + maxChild;
    }

    private Map<Long, Long> buildParentMap(List<PlainNode> nodes) {
        Map<Long, Long> parentMap = new HashMap<>();
        for (PlainNode p : nodes) {
            if (p.parentId() != null) {
                parentMap.put(p.id(), p.parentId());
            }
        }
        return parentMap;
    }
}
