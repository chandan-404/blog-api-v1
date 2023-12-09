package io.tinyverse.blogapiv1.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty
    @Size(min = 3, message = "Name should be mandatory")
    private String name;
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String body;
}
