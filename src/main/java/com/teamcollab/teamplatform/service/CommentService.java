package com.teamcollab.teamplatform.service;

import com.teamcollab.teamplatform.model.Comment;
import com.teamcollab.teamplatform.model.Task;
import com.teamcollab.teamplatform.model.User;
import com.teamcollab.teamplatform.repository.CommentRepository;
import com.teamcollab.teamplatform.repository.TaskRepository;
import com.teamcollab.teamplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Comment saveComment(String text, Long userId, Long taskId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Comment comment = new Comment();
        comment.setText(text);
        comment.setTimestamp(LocalDateTime.now());
        comment.setUser(user);
        comment.setTask(task);

        return commentRepository.save(comment);
    }
}
