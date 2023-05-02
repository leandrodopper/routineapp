package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.service.EjercicioServicio;
import com.leandro.routineapp.utility.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioControlador {
    @Autowired
    private EjercicioServicio ejercicioServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EjercicioDto> guardarEjercicio(@RequestBody EjercicioDto ejercicioDto){
      return new ResponseEntity<>(ejercicioServicio.crearEjercicio(ejercicioDto), HttpStatus.CREATED);
    }

    @GetMapping
    public EjercicioRespuesta listarEjercicios(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
                                               @RequestParam(value = "pageSize", defaultValue = AppConstantes.TAMANO_DE_PAGINA_POR_DEFECTO, required = false)int tamanoPagina,
                                               @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false)String ordenarPor,
                                               @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false)String sortDir){
        return ejercicioServicio.obtenerTodosEjercicios(numeroPagina,tamanoPagina, ordenarPor,sortDir );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDto> obtenerEjercicioPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(ejercicioServicio.obtenerEjercicioPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EjercicioDto> actualizarEjercicio(@RequestBody EjercicioDto ejercicioDto, @PathVariable(name = "id")long id){
        EjercicioDto ejercicioRespuesta = ejercicioServicio.actualizarEjercicio(ejercicioDto,id);
        return new ResponseEntity<>(ejercicioRespuesta,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEjercicio(@PathVariable(name = "id") long id){
        ejercicioServicio.eliminarEjercicio(id);
        return new ResponseEntity<>("Ejercicio eliminado con exito", HttpStatus.OK);
    }
}
