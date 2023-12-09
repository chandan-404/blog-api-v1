package io.tinyverse.blogapiv1.service;

import io.tinyverse.blogapiv1.payload.PostDto;
import io.tinyverse.blogapiv1.payload.PostResponse;

public interface PostService {
    PostDto create(PostDto postDto);

    PostResponse view(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getById(Long id);

    PostDto update(PostDto postDto, Long id);

    void delete(Long id);
}
