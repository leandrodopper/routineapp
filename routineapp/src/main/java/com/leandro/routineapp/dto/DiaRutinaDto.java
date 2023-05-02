package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.Rutina;

import java.util.List;

public class DiaRutinaDto {
    private Long id;
    private Long id_rutina;
    private String descripcion;
    private String nombre;
    private List<Long> ejerciciosIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(Long id_rutina) {
        this.id_rutina = id_rutina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getEjerciciosIds() {
        return ejerciciosIds;
    }

    public void setEjerciciosIds(List<Long> ejerciciosIds) {
        this.ejerciciosIds = ejerciciosIds;
    }

    public DiaRutinaDto(Long id, Long id_rutina, String descripcion, String nombre, List<Long> ejerciciosIds) {
        this.id = id;
        this.id_rutina = id_rutina;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.ejerciciosIds = ejerciciosIds;
    }

    public DiaRutinaDto(Long id_rutina, String descripcion, String nombre, List<Long> ejerciciosIds) {
        this.id_rutina = id_rutina;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.ejerciciosIds = ejerciciosIds;
    }

    public DiaRutinaDto() {
    }
}
