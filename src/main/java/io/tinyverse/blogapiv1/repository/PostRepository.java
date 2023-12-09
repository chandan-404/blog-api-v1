package io.tinyverse.blogapiv1.repository;

import io.tinyverse.blogapiv1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
