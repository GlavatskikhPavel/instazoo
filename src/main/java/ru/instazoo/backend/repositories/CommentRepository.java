package ru.instazoo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.instazoo.backend.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
