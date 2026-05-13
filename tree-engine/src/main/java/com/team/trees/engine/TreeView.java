package com.team.trees.engine;

import java.util.List;

public record TreeView(Long id, String value, List<TreeView> children) {}
