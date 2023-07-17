package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.dto.RutinaRespuesta;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.RutinaRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import com.leandro.routineapp.service.RutinaServicio;
import com.leandro.routineapp.utility.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaControlador {

    @Autowired
    private RutinaServicio rutinaServicio;

    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;



    @PostMapping
    public ResponseEntity<RutinaDto> guardarRutina(@RequestBody RutinaDto rutinaDto, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        rutinaDto.setCreador(username);
        rutinaDto.setPuntuacion(0);
        return new ResponseEntity<>(rutinaServicio.crearRutina(rutinaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDto> obtenerRutinaPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(rutinaServicio.obtenerRutinaPorId(id));
    }

    @GetMapping
    public RutinaRespuesta listarRutinas(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false)int tamanoPagina,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false)String ordenarPor,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false)String sortDir){
        return rutinaServicio.obtenerRutinas(numeroPagina,tamanoPagina, ordenarPor,sortDir );
    }

    @PostMapping("/seguir/{id_rutina}")
    public ResponseEntity<?> seguirRutina(@PathVariable(name = "id_rutina") long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        rutinaServicio.seguirRutina(id, usuario.get().getId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/dejarseguir/{id_rutina}")
    public ResponseEntity<?> dejarseguirRutina(@PathVariable(name = "id_rutina") long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        rutinaServicio.dejarseguirRutina(id, usuario.get().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rutinas_usuario")
    public ResponseEntity<List<RutinaDto>> obtenerRutinasSeguidasPorUsuario(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        List<RutinaDto> rutinas= rutinaServicio.obtenerRutinasSeguidasUsuario(usuario.get().getId());
        return ResponseEntity.ok(rutinas);
    }

    @GetMapping("/creadas_usuario")
    public ResponseEntity<List<RutinaDto>> obtenerRutinasCreadasPorUsuario(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        List<RutinaDto> rutinas= rutinaServicio.obtenerRutinasCreadasUsuario(usuario.get().getId());
        return ResponseEntity.ok(rutinas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRutina(@PathVariable(name = "id") long id){
        rutinaServicio.eliminarRutina(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Rutina eliminada con éxito");

        return ResponseEntity.noContent()
                .headers(headers)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaDto> actualizarRutina(@PathVariable(name="id") long id, @RequestBody RutinaDto rutinaDto){
       return ResponseEntity.ok(rutinaServicio.actualizarRutina(rutinaDto,id));
    }

    @GetMapping("/filtrarNombre")
    public ResponseEntity<List<RutinaDto>> filtrarPorNombre(@RequestParam(name = "nombre") String nombre){
        return ResponseEntity.ok(rutinaServicio.obtenerRutinasPorNombre(nombre));
    }

}
