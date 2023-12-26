package ru.instazoo.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.instazoo.backend.dto.PostDTO;
import ru.instazoo.backend.entities.Post;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final CommentMapper commentMapper;

    public PostDTO toPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .creationTime(post.getCreationTime())
                .comments(post.getComments()
                        .stream()
                        .map(commentMapper::toCommentDTO).collect(Collectors.toList()))
                .build();
    }

    public Post toPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setComments(postDTO.getComments()
                .stream().map(commentMapper::toComment).collect(Collectors.toList()));
        return post;
    }
}
