package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientoRepositorio extends JpaRepository<Entrenamiento,Long> {
}
