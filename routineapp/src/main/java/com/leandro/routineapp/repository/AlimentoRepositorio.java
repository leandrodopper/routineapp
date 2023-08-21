package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepositorio extends JpaRepository<Alimento, Long> {
}
