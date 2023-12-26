package ru.instazoo.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    private String message;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime creationTime;

    private Long postId;
}
