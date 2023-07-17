package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepositorio extends JpaRepository<Rutina,Long> {
    List<Rutina> findByCreador(String creador);
    List<Rutina> findByNombreContainingIgnoreCase(String nombre);



}
