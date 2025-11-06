package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

public class TodoDtos {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReq {
        @NotBlank @Size(max = 120)
        private String title;
        @Size(max = 1000)
        private String description;
        private LocalDateTime dueAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReq {
        @Size(max = 120)
        private String title;
        @Size(max = 1000)
        private String description;
        private Boolean completed;
        private LocalDateTime dueAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private String title;
        private String description;
        private boolean completed;
        private LocalDateTime dueAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
