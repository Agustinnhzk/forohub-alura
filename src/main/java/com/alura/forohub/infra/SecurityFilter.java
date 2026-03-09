package com.alura.forohub.infra;

import com.alura.forohub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        // --- EL TESTER (Para ver en la consola de IntelliJ) ---
        System.out.println("1. HEADER RECIBIDO EN EL ESCÁNER: " + authHeader);

        // Le agregamos el startsWith por seguridad
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");

            System.out.println("2. TOKEN LIMPIO A REVISAR: " + token);

            try {
                var nombreUsuario = tokenService.getSubject(token);
                System.out.println("3. USUARIO DETECTADO: " + nombreUsuario);

                if (nombreUsuario != null) {
                    var usuario = usuarioRepository.findByLogin(nombreUsuario);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.out.println("❌ ERROR AL VALIDAR EL TOKEN: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}