package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComidaRepositorio extends JpaRepository<Comida, Long> {
}
