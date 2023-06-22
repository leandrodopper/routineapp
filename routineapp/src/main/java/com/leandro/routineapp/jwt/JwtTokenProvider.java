package com.leandro.routineapp.jwt;

import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.RoutineAppException;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generarToken(Authentication authentication) {
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
        //Date fechaExpiracion = new Date(fechaActual.getTime() + 1000);

        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }



    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new RoutineAppException(HttpStatus.BAD_REQUEST, "Token JWT malformado");
        } catch (ExpiredJwtException ex) {
            throw new RoutineAppException(HttpStatus.UNAUTHORIZED, "Token JWT expirado");
        } catch (UnsupportedJwtException ex) {
            throw new RoutineAppException(HttpStatus.UNAUTHORIZED, "Token JWT no soportado");
        } catch (IllegalArgumentException ex) {
            throw new RoutineAppException(HttpStatus.BAD_REQUEST, "Token JWT vacío o nulo");
        } catch (SignatureException ex) {
            throw new RoutineAppException(HttpStatus.UNAUTHORIZED, "Error de firma en el token JWT");
        }
    }

}
