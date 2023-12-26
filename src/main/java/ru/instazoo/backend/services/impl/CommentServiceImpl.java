package ru.instazoo.backend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.instazoo.backend.dto.CommentDTO;
import ru.instazoo.backend.dto.PostDTO;
import ru.instazoo.backend.entities.Comment;
import ru.instazoo.backend.entities.Post;
import ru.instazoo.backend.exceptions.CommentNotFoundException;
import ru.instazoo.backend.exceptions.PostNotFoundException;
import ru.instazoo.backend.mapper.CommentMapper;
import ru.instazoo.backend.mapper.PostMapper;
import ru.instazoo.backend.repositories.CommentRepository;
import ru.instazoo.backend.repositories.PostRepository;
import ru.instazoo.backend.services.CommentService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentsRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getAllByPost(Long id) throws PostNotFoundException {
        Post post = getPostEntityById(id);
        log.info("Get all comments by {}", post.getId());
        return post.getComments()
                .stream()
                .map(commentMapper::toCommentDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO saveOrUpdate(CommentDTO commentDTO) throws PostNotFoundException {
        Comment comment = commentMapper.toComment(commentDTO);
        if (commentDTO.getId() == null) {
            log.info("Create new Comment to post with id: {}", commentDTO.getPostId());
            commentsRepository.save(comment);
        } else {
            log.info("Update Comment to Post with id: {}", commentDTO.getPostId());
            commentsRepository.save(comment);
        }
        return commentMapper.toCommentDTO(comment);
    }

    @Override
    public void delete(Long id) throws PostNotFoundException {
        log.info("Delete Comment with id: " + id);
        commentsRepository.deleteById(id);
    }

    private Post getPostEntityById(Long id) throws PostNotFoundException {
        Post post = postRepository
                .findById(id)
                .orElse(null);
        if (post == null) throw new PostNotFoundException("Not found Post with id: " + id);
        log.info("Get Post: {}", post);
        return post;
    }
}
