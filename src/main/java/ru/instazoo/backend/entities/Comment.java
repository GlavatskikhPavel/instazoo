package ru.instazoo.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@ToString(exclude = {"owner"})
public class Comment {
    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTime;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post owner;

    @PrePersist
    private void init() {
        creationTime = LocalDateTime.now();
    }
}
