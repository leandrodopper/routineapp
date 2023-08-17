package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoDto;
import com.leandro.routineapp.dto.GetTiemposDto;
import com.leandro.routineapp.dto.GuardarEntrenoDto;
import com.leandro.routineapp.entity.Entrenamiento;

public interface EntrenamientoServicio {
    EntrenamientoDto guardarEntrenamiento(GuardarEntrenoDto entrenamientodto, Long id_usuario);

    GetTiemposDto obtenerTiemposUsuario (Long usuario_id);
}
