package io.tinyverse.blogapiv1.controller;

import io.tinyverse.blogapiv1.payload.CommentDto;
import io.tinyverse.blogapiv1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity <CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto created = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId, @PathVariable("id") Long commentId){
        CommentDto commentDto = commentService.getCommentsById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId,@PathVariable("id") Long commentId,@RequestBody CommentDto commentDto){
        CommentDto updateComment = commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId, @PathVariable("id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully.", HttpStatus.OK);
    }
}
