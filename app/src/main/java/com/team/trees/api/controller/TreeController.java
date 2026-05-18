package com.team.trees.api.controller;

import com.team.trees.api.dto.TraversalType;
import com.team.trees.api.dto.TreeResponse;
import com.team.trees.api.generated.TreeApi;
import com.team.trees.service.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreeController implements TreeApi {

    private final TreeService service;

    public TreeController(TreeService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<TreeResponse> getTree() {
        return ResponseEntity.ok(service.getTree());
    }

    @Override
    public ResponseEntity<List<Long>> traversal(TraversalType type) {
        List<Long> result = type == TraversalType.BFS ? service.traversalBfs() : service.traversalDfs();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Integer> getHeight() {
        return ResponseEntity.ok(service.getHeight());
    }

    @Override
    public ResponseEntity<Boolean> validate() {
        return ResponseEntity.ok(service.validate());
    }

    @Override
    public ResponseEntity<TreeResponse> getSubTree(Long commentId) {
        return ResponseEntity.ok(service.getSubTree(commentId));
    }
}
