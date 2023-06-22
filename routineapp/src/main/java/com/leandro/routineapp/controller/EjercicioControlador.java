package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import com.leandro.routineapp.service.EjercicioServicio;
import com.leandro.routineapp.utility.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioControlador {
    @Autowired
    private EjercicioServicio ejercicioServicio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<EjercicioDto> guardarEjercicio(@RequestBody EjercicioDto ejercicioDto, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        ejercicioDto.setUsername_creador(username);
      return new ResponseEntity<>(ejercicioServicio.crearEjercicio(ejercicioDto), HttpStatus.CREATED);
    }

    @GetMapping
    public EjercicioRespuesta listarEjercicios(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
                                               @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false)int tamanoPagina,
                                               @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false)String ordenarPor,
                                               @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false)String sortDir){
        return ejercicioServicio.obtenerTodosEjercicios(numeroPagina,tamanoPagina, ordenarPor,sortDir );
    }

    @GetMapping("/filtrarGrupo")
    public EjercicioRespuesta filtrarEjerciciosPorGrupoMuscular(
            @RequestParam(value = "grupo_muscular") String grupoMuscular,
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false) int tamanoPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return ejercicioServicio.filtrarGrupoMuscular(numeroPagina, tamanoPagina, ordenarPor, sortDir, grupoMuscular);
    }

    @GetMapping("/filtrarNombre")
    public EjercicioRespuesta filtrarEjerciciosPorNombre(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false) int tamanoPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return ejercicioServicio.filtrarPorNombre(numeroPagina, tamanoPagina, ordenarPor, sortDir, nombre);
    }

    @GetMapping("/filtrarCreador")
    public EjercicioRespuesta filtrarEjerciciosPorCreador(
            @RequestParam(value = "username_creador") String username_creador,
            @RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false) int tamanoPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return ejercicioServicio.filtrarPorCreador(numeroPagina, tamanoPagina, ordenarPor, sortDir, username_creador);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDto> obtenerEjercicioPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(ejercicioServicio.obtenerEjercicioPorId(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<EjercicioDto> actualizarEjercicio(@RequestBody EjercicioDto ejercicioDto, @PathVariable(name = "id")long id){
        EjercicioDto ejercicioRespuesta = ejercicioServicio.actualizarEjercicio(ejercicioDto,id);
        return new ResponseEntity<>(ejercicioRespuesta,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEjercicio(@PathVariable(name = "id") long id){
        ejercicioServicio.eliminarEjercicio(id);
        return new ResponseEntity<>("Ejercicio eliminado con exito", HttpStatus.OK);
    }

}
