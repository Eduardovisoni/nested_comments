package com.team.trees.engine.custom.internal;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private final Long id;
    private final String value;
    private final List<TreeNode> children = new ArrayList<>();

    public TreeNode(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() { return id; }
    public String getValue() { return value; }
    public List<TreeNode> getChildren() { return children; }
    public void addChild(TreeNode child) { children.add(child); }
}
