package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.LoginDto;
import com.leandro.routineapp.dto.RegistroDto;
import com.leandro.routineapp.entity.Rol;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.jwt.JWTAuthResponseDTO;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.RolRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }


    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDto registroDto){
        if(usuarioRepositorio.existsByUsername(registroDto.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        if(usuarioRepositorio.existsByEmail(registroDto.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setApellidos(registroDto.getApellidos());
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        Rol roles = rolRepositorio.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(roles));
        usuario.setTelefono(registroDto.getTelefono());
        usuario.setAltura(registroDto.getAltura());
        usuario.setPeso(registroDto.getPeso());
        usuario.setEdad(registroDto.getEdad());

        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
    }
}
