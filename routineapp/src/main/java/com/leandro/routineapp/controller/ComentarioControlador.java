package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.*;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ForbiddenException;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.ComentarioRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import com.leandro.routineapp.service.ComentarioServicio;
import com.leandro.routineapp.utility.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {
    @Autowired
    ComentarioServicio comentarioServicio;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/{id_rutina}")
    public ResponseEntity<ComentarioDto> publicarComentario(@RequestBody ComentarioDto comentarioDto,
                                                           @PathVariable(name = "id_rutina") long id_rutina,
                                                           HttpServletRequest request){

        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        comentarioDto.setUsuario(usuario.get().getUsername());
        Long comentario_padre_id = comentarioDto.getComentarioPadre_id();

        return new ResponseEntity<>(comentarioServicio.publicarComentario(comentarioDto,id_rutina, comentario_padre_id), HttpStatus.CREATED);
    }

    @GetMapping("/comentariosRutina/{id_rutina}")
    public ComentarioRespuesta listarComentariosRutina(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
                                                @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false)int tamanoPagina,
                                                @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false)String ordenarPor,
                                                @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false)String sortDir,
                                                       @PathVariable(name = "id_rutina") long id_rutina){

        return comentarioServicio.getComentariosRutina(numeroPagina,tamanoPagina,ordenarPor,sortDir,id_rutina);
    }

    @DeleteMapping("/{id_comentario}")
    public ResponseEntity<?> borrarComentario(@PathVariable(name = "id_comentario") long id_comentario, HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization").substring(7);
            String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
            Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username, username);
            comentarioServicio.eliminarComentario(id_comentario, usuario.get().getUsername());
            String mensaje = "El comentario con ID " + id_comentario + " ha sido eliminado exitosamente.";
            return ResponseEntity.noContent().header("Mensaje", mensaje).build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar este comentario.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al intentar eliminar el comentario.");
        }
    }
}
