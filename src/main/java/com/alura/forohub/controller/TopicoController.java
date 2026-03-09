package com.alura.forohub.controller;

import com.alura.forohub.model.DatosActualizarTopico;
import com.alura.forohub.model.DatosRegistroTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import jakarta.transaction.Transactional;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Método para CREAR un tópico (POST)
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        // 1. Chequeamos si ya existe
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            // Si existe, tiramos un error 400 personalizado
            return ResponseEntity.badRequest().body("Error: Ya existe un tópico con el mismo título y mensaje.");
        }

        // 2. Si no existe, lo guardamos
        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        // Devolvemos un 200 OK
        return ResponseEntity.ok("¡Tópico guardado exitosamente!");
    }
    @GetMapping("/{id}")
    public ResponseEntity retornarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get()); // Devuelve 200 OK con los datos
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }
    }

    // 4. ELIMINAR un tópico por su ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content (Borrado exitoso)
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 si alguien intenta borrar algo que no existe
        }
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizar) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarDatos(datosActualizar);

            // Devolvemos los datos actualizados para que el usuario vea cómo quedó
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}