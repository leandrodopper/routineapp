package com.leandro.routineapp.repository;

import com.leandro.routineapp.entity.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol,Long> {
    public Optional<Rol> findByNombre(String nombre);
}
