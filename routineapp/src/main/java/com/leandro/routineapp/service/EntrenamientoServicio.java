package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoDto;
import com.leandro.routineapp.dto.GetTiemposDto;
import com.leandro.routineapp.dto.GuardarEntrenoDto;
import com.leandro.routineapp.entity.Entrenamiento;

import java.util.List;

public interface EntrenamientoServicio {
    public EntrenamientoDto guardarEntrenamiento(GuardarEntrenoDto entrenamientodto, Long id_usuario);

    public GetTiemposDto obtenerTiemposUsuario (Long usuario_id);

    public List<Integer> obtenerEsfuerzosPorUsuarioYEjercicio(Long id_usuario, Long id_ejercicio);

    public List<Object[]> obtenerDatosEjercicioPorUsuario(Long usuario_id, Long ejercicio_id);

    public List<Object[]> obtenerPorcentajePorMusculo(Long usuario_id);
}
