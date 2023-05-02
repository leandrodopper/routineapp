package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.service.EjercicioServicio;
import com.leandro.routineapp.service.RutinaServicio;
import com.leandro.routineapp.utility.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaControlador {

    @Autowired
    private RutinaServicio rutinaServicio;

    @PostMapping
    public ResponseEntity<RutinaDto> guardarRutina(@RequestBody RutinaDto rutinaDto){
        return new ResponseEntity<>(rutinaServicio.crearRutina(rutinaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDto> obtenerRutinaPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(rutinaServicio.obtenerRutinaPorId(id));
    }
}
