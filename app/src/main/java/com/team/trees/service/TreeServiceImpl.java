package com.team.trees.service;

import com.team.trees.api.dto.CommentResponse;
import com.team.trees.api.dto.TreeResponse;
import com.team.trees.api.mapper.CommentMapper;
import com.team.trees.domain.Comment;
import com.team.trees.engine.TreeAlgorithmStrategy;
import com.team.trees.engine.TreeView;
import com.team.trees.engine.model.PlainNode;
import com.team.trees.persistence.TreeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    private final TreeRepository repo;
    private final TreeAlgorithmStrategy strategy;
    private final CommentMapper mapper;

    public TreeServiceImpl(TreeRepository repo, TreeAlgorithmStrategy strategy, CommentMapper mapper) {
        this.repo = repo;
        this.strategy = strategy;
        this.mapper = mapper;
    }

    @Override
    public CommentResponse createRoot(String author, String content) {
        PlainNode saved = repo.save(new PlainNode(null, content, null));
        Comment comment = new Comment(saved.id(), author, saved.value(), LocalDateTime.now(), null);
        return mapper.toCommentResponse(comment);
    }

    @Override
    public CommentResponse addReply(Long parentId, String author, String content) {
        if (!repo.existsById(parentId)) throw new IllegalArgumentException("Parent not found: " + parentId);
        PlainNode saved = repo.save(new PlainNode(null, content, parentId));
        Comment comment = new Comment(saved.id(), author, saved.value(), LocalDateTime.now(), parentId);
        return mapper.toCommentResponse(comment);
    }

    @Override
    public TreeResponse getTree() {
        List<PlainNode> nodes = repo.findAll();
        TreeView tree = strategy.buildTree(nodes);
        return mapper.toTreeResponse(tree);
    }

    @Override
    public TreeResponse getSubTree(Long commentId) {
        List<PlainNode> nodes = repo.findAll();
        TreeView subtree = strategy.buildSubTree(nodes, commentId);
        return mapper.toTreeResponse(subtree);
    }

    @Override
    public List<Long> getPathToRoot(Long commentId) {
        return strategy.pathToRoot(repo.findAll(), commentId);
    }

    @Override
    public List<Long> traversalDfs() {
        return strategy.dfs(repo.findAll());
    }

    @Override
    public List<Long> traversalBfs() {
        return strategy.bfs(repo.findAll());
    }

    @Override
    public int getHeight() {
        return strategy.height(repo.findAll());
    }

    @Override
    public int getDepth(Long commentId) {
        return strategy.depth(repo.findAll(), commentId);
    }

    @Override
    public List<Long> getAncestors(Long commentId) {
        return strategy.ancestors(repo.findAll(), commentId);
    }

    @Override
    public boolean validate() {
        return strategy.hasNoCycles(repo.findAll());
    }
}
