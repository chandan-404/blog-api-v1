package io.tinyverse.blogapiv1.mapper;

import io.tinyverse.blogapiv1.entity.Post;
import io.tinyverse.blogapiv1.payload.PostDto;
import org.modelmapper.ModelMapper;

public class PostMapper {
    private final ModelMapper mapper;

    public PostMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    // convert DTO to Entity
    private Post mapToEntity(PostDto postDto){
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return mapper.map(postDto, Post.class);
    }

    // convert Entity to DTO
    private PostDto mapToDto(Post post){
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return mapper.map(post, PostDto.class);
    }
}

