package ru.instazoo.backend.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.instazoo.backend.dto.CommentDTO;
import ru.instazoo.backend.payload.response.Info;
import ru.instazoo.backend.services.CommentService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getAllComment(@PathVariable("id") Long id) {
        List<CommentDTO> comment = commentService.getAllByPost(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO commentDTO) {
        CommentDTO comment = commentService.saveOrUpdate(commentDTO);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO commentFormDb = commentService.saveOrUpdate(commentDTO);
        return new ResponseEntity<>(commentFormDb, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Info> deleteComment(@PathVariable("id") Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(new Info("Delete Comment with id: " + id,
                true), HttpStatus.OK);
    }
}
