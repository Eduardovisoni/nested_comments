package com.team.trees.service;

import com.team.trees.api.dto.CommentResponse;
import com.team.trees.api.dto.TreeResponse;
import java.util.List;

public interface TreeService {

    CommentResponse createRoot(String author, String content);
    CommentResponse addReply(Long parentId, String author, String content);

    TreeResponse    getTree();
    TreeResponse    getSubTree(Long commentId);

    List<Long>      getPathToRoot(Long commentId);

    List<Long>      traversalDfs();
    List<Long>      traversalBfs();

    int             getHeight();
    int             getDepth(Long commentId);

    List<Long>      getAncestors(Long commentId);

    boolean         validate();
}
