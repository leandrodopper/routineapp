package com.leandro.routineapp.repository;

import com.leandro.routineapp.dto.GetTiemposDto;
import com.leandro.routineapp.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenamientoRepositorio extends JpaRepository<Entrenamiento,Long> {

    @Query("SELECT MAX(e.duracionMinutos), MIN(e.duracionMinutos), AVG(e.duracionMinutos) " +
            "FROM Entrenamiento e " +
            "WHERE e.usuario.id = :usuarioId " +
            "GROUP BY e.usuario.id")
    List<Object[]> obtenerTiemposDeEntrenamientoPorUsuario(@Param("usuarioId") Long usuarioId);
}
