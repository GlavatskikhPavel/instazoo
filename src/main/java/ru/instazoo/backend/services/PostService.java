package ru.instazoo.backend.services;

import ru.instazoo.backend.dto.PostDTO;
import ru.instazoo.backend.exceptions.PostNotFoundException;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();

    PostDTO getById(Long id) throws PostNotFoundException;

    PostDTO saveOrUpdate(PostDTO post);

    void delete(Long id) throws PostNotFoundException;
}
