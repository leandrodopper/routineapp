package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutinaRepositorio extends JpaRepository<Rutina,Long> {
}
