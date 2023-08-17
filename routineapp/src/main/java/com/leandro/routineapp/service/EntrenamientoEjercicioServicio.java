package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoEjercicioDto;

import java.util.List;

public interface EntrenamientoEjercicioServicio {

    List<Integer> obtenerEsfuerzosPorUsuarioYEjercicio(Long id_usuario, Long id_ejercicio);

}
