package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.SerieEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SerieEntrenamientoRepositorio extends JpaRepository<SerieEntrenamiento,Long> {

    @Query("SELECT se.pesoUtilizado, e.fecha, se.objetivoCumplido " +
            "FROM SerieEntrenamiento se " +
            "JOIN se.entrenamientoEjercicio ee " +
            "JOIN ee.entrenamiento e " +
            "WHERE e.usuario.id = :usuarioId AND ee.ejercicio.id = :ejercicioId " +
            "ORDER BY e.fecha")
    List<Object[]> obtenerProgresoPorUsuarioYEjercicio(Long usuarioId, Long ejercicioId);

    @Query("SELECT COUNT(se), e.grupo_muscular " +
            "FROM SerieEntrenamiento se " +
            "JOIN se.entrenamientoEjercicio ee " +
            "JOIN ee.entrenamiento ent " +
            "JOIN ee.ejercicio e " +
            "WHERE ent.usuario.id = :usuarioId " +
            "GROUP BY e.grupo_muscular")
    List<Object[]> obtenerTotalSeriesPorGrupoMuscular( Long usuarioId);
}
