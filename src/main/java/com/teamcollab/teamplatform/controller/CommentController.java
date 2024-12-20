package com.teamcollab.teamplatform.controller;

import com.teamcollab.teamplatform.model.Comment;
import com.teamcollab.teamplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestParam("text") String text,
                                              @RequestParam("userId") Long userId,
                                              @RequestParam("taskId") Long taskId) {
        Comment comment = commentService.saveComment(text, userId, taskId);
        return ResponseEntity.ok(comment);
    }
}
