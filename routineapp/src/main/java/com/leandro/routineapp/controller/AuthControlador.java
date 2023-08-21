package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.AuthResponse;
import com.leandro.routineapp.dto.EditarUsuarioDto;
import com.leandro.routineapp.dto.LoginDto;
import com.leandro.routineapp.dto.RegistroDto;
import com.leandro.routineapp.entity.Rol;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.RoutineAppException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;


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
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto){

        try{
            if (StringUtils.isEmpty(loginDto.getUsernameOrEmail()) || StringUtils.isEmpty(loginDto.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: los campos 'usernameOrEmail' y 'password' son obligatorios");
            }
            Optional<Usuario> aux = usuarioRepositorio.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail() );
            if (!aux.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: el usuario o email introducido no existe");
            }
            if (!passwordEncoder.matches(loginDto.getPassword(), aux.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Ha habido un error al intentar iniciar sesión. Compruebe de nuevo los datos");
            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            //obtenemos el token del jwtTokenProvider
            String token = jwtTokenProvider.generarToken(authentication);


            String usernameoremail = jwtTokenProvider.obtenerUsernameDelJWT(token);

            Optional<Usuario> usuario= usuarioRepositorio.findByUsernameOrEmail(usernameoremail, usernameoremail);

            AuthResponse authResponse = new AuthResponse(token, usuario);

            return ResponseEntity.ok(authResponse);

        }catch (Exception e){
            String errorMessage = "Error interno del servidor: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
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
        usuario.setImagen("");

        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    @PutMapping("/actualizar/userId/{userId}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long userId, @RequestBody EditarUsuarioDto editarUsuarioDto){
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(userId);

        if(usuarioOptional.isEmpty()){
            return new ResponseEntity<>("Usuario con id:"+userId+" no encontrado",HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNombre(editarUsuarioDto.getNombre());
        usuario.setApellidos(editarUsuarioDto.getApellidos());
        usuario.setTelefono(editarUsuarioDto.getTelefono());
        usuario.setAltura(editarUsuarioDto.getAltura());
        usuario.setPeso(editarUsuarioDto.getPeso());
        usuario.setEdad(editarUsuarioDto.getEdad());

        Usuario usuarioresp= usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(usuarioresp,HttpStatus.OK);
    }

    @PutMapping("/actualizarPass/userId/{userId}")
    public ResponseEntity<?> changePass (@PathVariable Long userId, @RequestBody String password){
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(userId);
        if(usuarioOptional.isEmpty()){
            return new ResponseEntity<>("Usuario con id:"+userId+" no encontrado",HttpStatus.NOT_FOUND);
        }
        Usuario usuario=usuarioOptional.get();
        usuario.setPassword(passwordEncoder.encode(password));

        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Contraseña cambiada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/validarToken")
    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String tokenHeader) {
        try {
            String token = tokenHeader.substring(7); // Remover la palabra "Bearer " del header de autorización
            Boolean valido=jwtTokenProvider.validarToken(token); // Validar el token
            System.out.println("VALIDO: "+valido);
            String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
            Optional<Usuario> aux= usuarioRepositorio.findByUsernameOrEmail(username,username);
            return ResponseEntity.ok(new AuthResponse(token,aux));
        } catch (RoutineAppException ex) {
            return ResponseEntity.status(ex.getEstado()).body(ex.getMensaje());
        }
    }

    @PostMapping("/agregar-rol")
    public ResponseEntity<?> agregarRolAUsuario(@RequestParam Long usuarioId, @RequestParam String nuevoRol) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(usuarioId);

        if (optionalUsuario.isEmpty()) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optionalUsuario.get();

        Rol rol = rolRepositorio.findByNombre(nuevoRol).orElse(null);

        if (rol == null) {
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }

        if (usuario.getRoles().contains(rol)) {
            return new ResponseEntity<>("El usuario ya tiene este rol", HttpStatus.BAD_REQUEST);
        }

        usuario.getRoles().add(rol);
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>("Rol agregado exitosamente", HttpStatus.OK);
    }



}
