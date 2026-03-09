package com.alura.forohub.infra;

import com.alura.forohub.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            // El algoritmo HMAC256 es el candado que sella el token con tu llave secreta
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub") // Quién emite el token
                    .withSubject(usuario.getLogin()) // A quién le pertenece el token (el usuario)
                    .withClaim("id", usuario.getId()) // Guardamos el ID del usuario adentro del token
                    .withExpiresAt(generarFechaExpiracion()) // Le ponemos fecha de vencimiento
                    .sign(algorithm); // Lo firmamos
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        // Le damos 2 horas de validez al token.
        // El offset "-03:00" es clave para que tome el horario local correcto de Argentina.
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token es nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Usamos la misma llave secreta
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token); // Acá explota si el token es falso o expiró
            return verifier.getSubject(); // Devuelve el "admin"
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado");
        }
    }
}