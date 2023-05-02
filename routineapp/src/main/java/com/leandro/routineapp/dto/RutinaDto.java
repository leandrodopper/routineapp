package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.DiaRutina;


import java.util.List;

public class RutinaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<DiaRutina> dias_rutina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DiaRutina> getDias_rutina() {
        return dias_rutina;
    }

    public void setDias_rutina(List<DiaRutina> dias_rutina) {
        this.dias_rutina = dias_rutina;
    }

    public RutinaDto(Long id, String nombre, String descripcion, List<DiaRutina> dias_rutina) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
    }

    public RutinaDto(String nombre, String descripcion, List<DiaRutina> dias_rutina) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
    }

    public RutinaDto() {
    }
}
