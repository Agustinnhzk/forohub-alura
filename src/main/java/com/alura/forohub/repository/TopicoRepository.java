package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Nueva antena: Busca si ya existe esta combinación exacta
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}