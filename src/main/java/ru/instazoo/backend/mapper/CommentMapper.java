package ru.instazoo.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.instazoo.backend.dto.CommentDTO;
import ru.instazoo.backend.entities.Comment;
import ru.instazoo.backend.entities.Post;
import ru.instazoo.backend.exceptions.PostNotFoundException;
import ru.instazoo.backend.repositories.PostRepository;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final PostRepository postRepository;

    public CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .creationTime(comment.getCreationTime())
                .postId(comment.getOwner().getId())
                .build();
    }

    public Comment toComment(CommentDTO commentDTO) throws PostNotFoundException {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setMessage(commentDTO.getMessage());
        comment.setCreationTime(commentDTO.getCreationTime());
        comment.setOwner(getPostEntityById(commentDTO.getPostId()));
        return comment;
    }

    private Post getPostEntityById(Long id) throws PostNotFoundException {
        Post post = postRepository
                .findById(id)
                .orElse(null);
        if (post == null) throw new PostNotFoundException("Not found Post with id: " + id);
        return post;
    }
}
