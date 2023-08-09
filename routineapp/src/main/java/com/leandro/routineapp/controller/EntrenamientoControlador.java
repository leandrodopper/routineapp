package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EntrenamientoDto;
import com.leandro.routineapp.dto.GuardarEntrenoDto;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import com.leandro.routineapp.service.EntrenamientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenos")
public class EntrenamientoControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EntrenamientoServicio entrenamientoServicio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EntrenamientoDto> guardarEntreno(@RequestBody GuardarEntrenoDto entrenamientoDto, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        return new ResponseEntity<>(entrenamientoServicio.guardarEntrenamiento(entrenamientoDto, usuario.get().getId() ), HttpStatus.CREATED);
    }
}
