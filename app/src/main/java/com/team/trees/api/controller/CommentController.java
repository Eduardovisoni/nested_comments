package com.team.trees.api.controller;

import com.team.trees.api.dto.CommentRequest;
import com.team.trees.api.dto.CommentResponse;
import com.team.trees.api.generated.CommentsApi;
import com.team.trees.service.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController implements CommentsApi {

    private final TreeService service;

    public CommentController(TreeService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CommentResponse> createRoot(CommentRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createRoot(commentRequest.getAuthor(), commentRequest.getContent()));
    }

    @Override
    public ResponseEntity<CommentResponse> addReply(Long parentId, CommentRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addReply(parentId, commentRequest.getAuthor(), commentRequest.getContent()));
    }

    @Override
    public ResponseEntity<List<Long>> getPathToRoot(Long commentId) {
        return ResponseEntity.ok(service.getPathToRoot(commentId));
    }

    @Override
    public ResponseEntity<Integer> getDepth(Long commentId) {
        return ResponseEntity.ok(service.getDepth(commentId));
    }

    @Override
    public ResponseEntity<List<Long>> getAncestors(Long commentId) {
        return ResponseEntity.ok(service.getAncestors(commentId));
    }
}
