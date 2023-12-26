package ru.instazoo.backend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.instazoo.backend.dto.PostDTO;
import ru.instazoo.backend.entities.Post;
import ru.instazoo.backend.exceptions.PostNotFoundException;
import ru.instazoo.backend.mapper.PostMapper;
import ru.instazoo.backend.repositories.PostRepository;
import ru.instazoo.backend.services.PostService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDTO> getAll() {
        log.info("Get all Posts");
        return postRepository.findAll()
                .stream()
                .map(postMapper::toPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(Long id) throws PostNotFoundException {
        Post post = postRepository
                .findById(id)
                .orElse(null);
        if (post == null) throw new PostNotFoundException("Not found Post with id: " + id);
        log.info("Get Post: {}", post);
        return postMapper.toPostDTO(post);
    }

    @Override
    public PostDTO saveOrUpdate(PostDTO postDTO) {
        Post post = postMapper.toPost(postDTO);
        Long postId = post.getId();
        Post postFromDb;
        if (postId == null) {
            postFromDb = postRepository.save(post);
            log.info("Save new Post: {}", post);
        } else {
            postFromDb = postRepository.save(post);
            log.info("Update Post: {}", post);
        }
        return postMapper.toPostDTO(postFromDb);
    }

    @Override
    public void delete(Long id) throws PostNotFoundException {
        Post post = getPostEntityById(id);
        log.info("Delete Post: {}", post);
        postRepository.delete(post);
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
