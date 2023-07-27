package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Rutina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {
    Page<Comentario> findByRutina(Rutina rutina, Pageable pageable);
}
