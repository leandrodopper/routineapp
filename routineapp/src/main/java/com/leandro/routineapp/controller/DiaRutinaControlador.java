package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.ActualizarDiaRutinaDto;
import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.service.RutinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diasrutina")
public class DiaRutinaControlador {
    @Autowired
    private RutinaServicio rutinaServicio;

    @PostMapping
    public ResponseEntity<DiaRutinaDto> guardarDiarutina(@RequestBody DiaRutinaDto diaRutinaDto){
        DiaRutinaDto respuesta=rutinaServicio.crearDiarutina(diaRutinaDto);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaRutinaDto> obtenerDiarutinaPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(rutinaServicio.obtenerDiarutina(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDiaRutina(@PathVariable(name = "id") long id){
        rutinaServicio.eliminarDiarutina(id);
        return ResponseEntity.ok("DiaRutina eliminado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaRutinaDto> actualizarDiaRutina (@PathVariable(name = "id") long id, @RequestBody ActualizarDiaRutinaDto actualizarDiaRutinaDto){
       return ResponseEntity.ok(rutinaServicio.actualizarDiarutina(actualizarDiaRutinaDto,id));
    }

    @PostMapping("/{id}/addEjercicio")
    public ResponseEntity<DiaRutinaDto> addEjercicio (@PathVariable(name = "id") long id, @RequestBody EjercicioDiaRutinaDto ejercicioDiaRutinaDto){
        return ResponseEntity.ok(rutinaServicio.addEjercicioADia(ejercicioDiaRutinaDto,id));
    }

    @PostMapping("/{id}/addListEjercicios")
    public ResponseEntity<DiaRutinaDto> addEjercicioList (@PathVariable(name = "id") long id, @RequestBody List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDto){
        return ResponseEntity.ok(rutinaServicio.addEjercicioADiaList(ejerciciosDiaRutinaDto,id));
    }

    @DeleteMapping("/{id}/removeEjercicio/{id_ejercicio}")
    public ResponseEntity<DiaRutinaDto> removeEjercicio (@PathVariable(name = "id") long id, @PathVariable (name = "id_ejercicio") long id_ejercicio){
        return ResponseEntity.ok(rutinaServicio.deleteEjercicioDeDia(id,id_ejercicio));
    }
}
