package com.leandro.routineapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leandro.routineapp.entity.Rutina;

import java.util.List;

public class DiaRutinaDto {
    private Long id;
    //@JsonIgnore
    private Long id_rutina;
    private String descripcion;
    private String nombre;
    private List<EjercicioDiaRutinaDto> ejerciciosDiaRutina;

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

    public List<EjercicioDiaRutinaDto> getEjerciciosDiaRutina() {
        return ejerciciosDiaRutina;
    }

    public void setEjerciciosDiaRutina(List<EjercicioDiaRutinaDto> ejerciciosDiaRutina) {
        this.ejerciciosDiaRutina = ejerciciosDiaRutina;
    }

    public DiaRutinaDto() {
    }
}
