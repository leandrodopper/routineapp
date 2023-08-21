package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoEjercicioDto;

import java.util.List;

public interface EntrenamientoEjercicioServicio {

    public List<Integer> obtenerEsfuerzosPorUsuarioYEjercicio(Long id_usuario, Long id_ejercicio);

}
