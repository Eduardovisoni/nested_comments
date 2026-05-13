package com.team.trees.api.controller;

import com.team.trees.api.dto.TreeResponse;
import com.team.trees.service.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private final TreeService service;

    public TreeController(TreeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<TreeResponse> getTree() {
        return ResponseEntity.ok(service.getTree());
    }

    // /tree/traversal, /tree/height y /tree/validate son más específicos que /{commentId}
    // Spring MVC los resuelve antes del path variable — no hay conflicto
    @GetMapping("/traversal")
    public ResponseEntity<List<Long>> traversal(@RequestParam String type) {
        List<Long> result = "BFS".equalsIgnoreCase(type) ? service.traversalBfs() : service.traversalDfs();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/height")
    public ResponseEntity<Integer> height() {
        return ResponseEntity.ok(service.getHeight());
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate() {
        return ResponseEntity.ok(service.validate());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<TreeResponse> getSubTree(@PathVariable Long commentId) {
        return ResponseEntity.ok(service.getSubTree(commentId));
    }
}
