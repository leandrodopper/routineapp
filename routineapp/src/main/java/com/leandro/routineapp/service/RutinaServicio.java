package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.RutinaDto;

public interface RutinaServicio {

    public RutinaDto crearRutina(RutinaDto rutinaDto);
    public RutinaDto obtenerRutinaPorId(Long id);
    public void eliminarRutina(Long id);
}
