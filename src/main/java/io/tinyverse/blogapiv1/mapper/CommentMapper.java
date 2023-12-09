package io.tinyverse.blogapiv1.mapper;

import io.tinyverse.blogapiv1.entity.Comment;
import io.tinyverse.blogapiv1.payload.CommentDto;
import org.modelmapper.ModelMapper;

public class CommentMapper {
    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    private CommentDto maToDto(Comment comment) {
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        return mapper.map(comment, CommentDto.class);
    }
    //    Here we can convert mapToComment to inject mapper class to create dto object
    private Comment mapToComment(CommentDto commentDto){
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return mapper.map(commentDto, Comment.class);
    }
}
