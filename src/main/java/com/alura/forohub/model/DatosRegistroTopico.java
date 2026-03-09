package com.alura.forohub.model;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotBlank(message = "El nombre del autor es obligatorio")
        String autor,

        @NotBlank(message = "El nombre del curso es obligatorio")
        String curso
) {
}