package ru.instazoo.backend.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.instazoo.backend.dto.PostDTO;
import ru.instazoo.backend.payload.response.Info;
import ru.instazoo.backend.services.PostService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll() {
        List<PostDTO> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
        PostDTO postFromDb = postService.saveOrUpdate(postDTO);
        return new ResponseEntity<>(postFromDb, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("id") Long id) {
        PostDTO post = postService.getById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDTO) {
        PostDTO postFromDb = postService.saveOrUpdate(postDTO);
        return new ResponseEntity<>(postFromDb, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Info> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>(new Info("Delete Post with id: " + id, true), HttpStatus.OK);
    }
}
