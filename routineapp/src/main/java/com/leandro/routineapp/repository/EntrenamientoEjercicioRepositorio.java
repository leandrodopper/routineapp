package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.EntrenamientoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntrenamientoEjercicioRepositorio extends JpaRepository<EntrenamientoEjercicio,Long> {

    @Query("SELECT ee.nivelEsfuerzoPercibido " +
            "FROM EntrenamientoEjercicio ee " +
            "WHERE ee.entrenamiento.usuario.id = :usuarioId AND ee.ejercicio.id = :ejercicioId")
    List<Integer> obtenerEsfuerzoPercibidoPorUsuarioYEjercicio(Long usuarioId, Long ejercicioId);
}
