package com.team.trees.api.controller;

import com.team.trees.api.dto.CommentRequest;
import com.team.trees.api.dto.CommentResponse;
import com.team.trees.service.TreeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final TreeService service;

    public CommentController(TreeService service) {
        this.service = service;
    }

    @PostMapping("/root")
    public ResponseEntity<CommentResponse> createRoot(@RequestBody @Valid CommentRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRoot(req.author(), req.content()));
    }

    @PostMapping("/{parentId}/replies")
    public ResponseEntity<CommentResponse> addReply(@PathVariable Long parentId,
                                                     @RequestBody @Valid CommentRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addReply(parentId, req.author(), req.content()));
    }

    @GetMapping("/{commentId}/path")
    public ResponseEntity<List<Long>> pathToRoot(@PathVariable Long commentId) {
        return ResponseEntity.ok(service.getPathToRoot(commentId));
    }

    @GetMapping("/{commentId}/depth")
    public ResponseEntity<Integer> depth(@PathVariable Long commentId) {
        return ResponseEntity.ok(service.getDepth(commentId));
    }

    @GetMapping("/{commentId}/ancestors")
    public ResponseEntity<List<Long>> ancestors(@PathVariable Long commentId) {
        return ResponseEntity.ok(service.getAncestors(commentId));
    }
}
