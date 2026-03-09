package com.alura.forohub.controller;

import com.alura.forohub.infra.TokenService;
import com.alura.forohub.model.DatosAutenticacionUsuario;
import com.alura.forohub.model.DatosJWTToken;
import com.alura.forohub.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave()
        );

        // 1. Validamos las credenciales contra la base de datos
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // 2. Extraemos el usuario y mandamos a imprimir el token
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // 3. Devolvemos el token empaquetado en el Record que creamos recién
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}