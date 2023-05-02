package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.service.DiaRutinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diasrutina")
public class DiaRutinaControlador {
    @Autowired
    private DiaRutinaServicio diaRutinaServicio;

    @PostMapping
    public ResponseEntity<DiaRutinaDto> guardarDiarutina(@RequestBody DiaRutinaDto diaRutinaDto){
        return new ResponseEntity<>(diaRutinaServicio.crearDiarutina(diaRutinaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaRutinaDto> obtenerDiarutinaPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(diaRutinaServicio.obtenerDiarutina(id));
    }
}
