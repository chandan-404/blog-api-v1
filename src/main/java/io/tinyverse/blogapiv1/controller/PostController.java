package io.tinyverse.blogapiv1.controller;

import io.tinyverse.blogapiv1.payload.PostDto;
import io.tinyverse.blogapiv1.payload.PostResponse;
import io.tinyverse.blogapiv1.service.PostService;
import io.tinyverse.blogapiv1.utils.AppContents;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/posts")  // http://localhost:8080/api/v1/posts
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }
    /* CRUD operations
        -> create
        -> read
        -> update
        -> delete
     */
    @PostMapping  // create post -> http://localhost:8080/api/v1/posts
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> create(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        // this check exception to validate of data field.
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    @GetMapping  // view all post -> http://localhost:8080/api/v1/posts
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public PostResponse view(
            @RequestParam(value = "pageNo", defaultValue = AppContents.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppContents.DEFAULT_PAGE_NUMBER, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppContents.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppContents.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.view(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}") // view post by id -> http://localhost:8080/api/v1/posts/{id} e.g. id=1
    public ResponseEntity<PostDto> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping("/{id}")  // update post by id -> http://localhost:8080/api/v1/posts/{id} e.g. id=2
    public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){
        PostDto postResponse = postService.update(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")  // delete post by id -> http://localpost:8080/api/v1/posts/{id} e.g. id=3
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
        postService.delete(id);
        return new ResponseEntity<>("Id with "+id+" Post deleted successfully.", HttpStatus.OK);
    }

}
