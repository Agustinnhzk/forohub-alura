package com.alura.forohub.repository;

import com.alura.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Esta es la antena direccional de seguridad: busca al usuario por su login
    UserDetails findByLogin(String login);
}