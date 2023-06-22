package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.DiaRutina;


import java.util.List;

public class RutinaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<DiaRutina> dias_rutina;

    private String creador;
    private double puntuacion;

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

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }


    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public RutinaDto(Long id, String nombre, String descripcion, List<DiaRutina> dias_rutina, String creador, double puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
        this.creador = creador;
        this.puntuacion = puntuacion;
    }

    public RutinaDto(String nombre, String descripcion, List<DiaRutina> dias_rutina, String creador, double puntuacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
        this.creador = creador;
        this.puntuacion = puntuacion;
    }

    public RutinaDto() {
    }
}
