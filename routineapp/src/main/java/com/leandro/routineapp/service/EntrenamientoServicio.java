package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoDto;
import com.leandro.routineapp.dto.GetTiemposDto;
import com.leandro.routineapp.dto.GuardarEntrenoDto;
import com.leandro.routineapp.entity.Entrenamiento;

public interface EntrenamientoServicio {
    public EntrenamientoDto guardarEntrenamiento(GuardarEntrenoDto entrenamientodto, Long id_usuario);

    public GetTiemposDto obtenerTiemposUsuario (Long usuario_id);
}
