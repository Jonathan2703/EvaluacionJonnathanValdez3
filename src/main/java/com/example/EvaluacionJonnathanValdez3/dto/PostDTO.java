package com.example.EvaluacionJonnathanValdez3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {
    @Positive(message = "El ID debe ser un número mayor que 0")
    private Long id;

    @NotBlank(message = "El título no puede estar en blanco")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String title;
}
