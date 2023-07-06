package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.DiaRutina;


import java.util.List;

public class RutinaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<DiaRutinaDto> dias_rutina;

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

    public List<DiaRutinaDto> getDias_rutina() {
        return dias_rutina;
    }

    public void setDias_rutina(List<DiaRutinaDto> dias_rutina) {
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



    public RutinaDto() {
    }
}
