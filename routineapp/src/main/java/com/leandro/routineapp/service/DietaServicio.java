package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.AlimentoDto;
import com.leandro.routineapp.dto.ComidaDto;
import com.leandro.routineapp.dto.DietaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DietaServicio {
    public DietaDto guardarDieta(DietaDto dietaDto);

    public DietaDto añadirComida(Long dietaId, ComidaDto comidaDto);
    public DietaDto añadirAlimento(Long comidaId, AlimentoDto alimentoDto);

    public DietaDto obtenerDietaPorId(Long dietaId);

    public ResponseEntity eliminarDieta(Long dietaId);

    public DietaDto editarDieta(Long dietaId, DietaDto dietaDto);

    public DietaDto eliminarComida(Long dietaId, Long comidaId);

    public DietaDto eliminarAlimento(Long comidaId, Long alimentoId);

    public List<DietaDto> obtenerTodasDietas();

    public List<DietaDto> buscarPorNombre(String nombre);
}
