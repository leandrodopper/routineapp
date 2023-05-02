package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepositorio extends JpaRepository<Ejercicio,Long> {
}
