package ru.instazoo.backend.services;

import ru.instazoo.backend.dto.CommentDTO;
import ru.instazoo.backend.exceptions.PostNotFoundException;
import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllByPost(Long id);

    CommentDTO saveOrUpdate(CommentDTO commentDTO);

    void delete(Long id) throws PostNotFoundException;
}
