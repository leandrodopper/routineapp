package com.leandro.routineapp.repository;


import com.leandro.routineapp.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietaRepositorio extends JpaRepository<Dieta, Long> {
    @Query("SELECT d FROM Dieta d WHERE LOWER(REPLACE(d.nombre, ' ', '')) LIKE %:searchTerm%")
    List<Dieta> buscarPorNombreSinTildesIgnoreCase(String searchTerm);
}
