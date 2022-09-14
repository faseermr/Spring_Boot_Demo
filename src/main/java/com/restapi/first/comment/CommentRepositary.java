package com.restapi.first.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositary extends JpaRepository<Comment, Long> {
    List<Comment> findAll();

    List<Comment> findByTutorialId(Long postId);
}
