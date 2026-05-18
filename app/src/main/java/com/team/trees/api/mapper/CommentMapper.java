package com.team.trees.api.mapper;

import com.team.trees.api.dto.CommentResponse;
import com.team.trees.api.dto.TreeResponse;
import com.team.trees.domain.Comment;
import com.team.trees.engine.TreeView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse()
                .id(comment.getId())
                .author(comment.getAuthor())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .parentId(comment.getParentId());
    }

    public TreeResponse toTreeResponse(TreeView view) {
        if (view == null) return null;
        List<TreeResponse> children = new ArrayList<>();
        for (TreeView child : view.children()) {
            children.add(toTreeResponse(child));
        }
        // author y createdAt no están en PlainNode; se enriquecen solo al crear (ver TreeServiceImpl)
        return new TreeResponse()
                .id(view.id())
                .author(null)
                .content(view.value())
                .createdAt(null)
                .children(children);
    }
}
