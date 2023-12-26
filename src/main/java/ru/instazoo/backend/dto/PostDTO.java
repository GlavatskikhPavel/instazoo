package ru.instazoo.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.instazoo.backend.entities.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;

    private String title;

    private String description;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime creationTime;

    private List<CommentDTO> comments = new ArrayList<>();
}
