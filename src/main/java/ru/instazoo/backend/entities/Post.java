package ru.instazoo.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.instazoo.backend.dto.CommentDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@ToString(exclude = "comments")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    private void init() {
        creationTime = LocalDateTime.now();
    }

}
