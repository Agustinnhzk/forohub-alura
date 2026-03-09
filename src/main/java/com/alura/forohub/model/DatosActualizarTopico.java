package com.alura.forohub.model;

// Acordate que al actualizar, tal vez el usuario solo quiera cambiar el mensaje y no el título,
// por eso acá no le ponemos @NotBlank, para que no sea obligatorio mandar todo.
public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}