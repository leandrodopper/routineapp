package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;

public class EjercicioDiaRutinaDto {
    private Long id_EjercicioRutina;
    private Long ejercicioId;
    private int series;
    private int repeticiones;

    public Long getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Long ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public int getSeries() {
        return series;
    }

    public Long getId_EjercicioRutina() {
        return id_EjercicioRutina;
    }

    public void setId_EjercicioRutina(Long id_EjercicioRutina) {
        this.id_EjercicioRutina = id_EjercicioRutina;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public EjercicioDiaRutinaDto() {
    }
}
