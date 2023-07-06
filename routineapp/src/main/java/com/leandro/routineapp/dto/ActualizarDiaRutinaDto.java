package com.leandro.routineapp.dto;

import java.util.List;

public class ActualizarDiaRutinaDto {
    private String descripcion;
    private String nombre;
    private  List<EjercicioDiaRutinaDto> ejerciciosDiaRutina;

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
}
