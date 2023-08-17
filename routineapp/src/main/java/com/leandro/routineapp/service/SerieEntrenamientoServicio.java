package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.SerieEntrenamientoDto;

import java.util.List;

public interface SerieEntrenamientoServicio {
    //public SerieEntrenamientoDto guardarSerieEntrenamiento(SerieEntrenamientoDto serieEntrenamientoDto);
    List<Object[]> obtenerDatosEjercicioPorUsuario(Long usuario_id, Long ejercicio_id);

    List<Object[]> obtenerPorcentajePorMusculo(Long usuario_id);

}
