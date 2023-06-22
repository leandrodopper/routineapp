package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Ejercicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjercicioRepositorio extends JpaRepository<Ejercicio,Long> {
    Ejercicio findByNombre(String nombre);
    Page<Ejercicio> findByUsernameCreador(String usernameCreador, Pageable pageable);
}
