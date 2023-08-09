package com.leandro.routineapp.dto;

import java.util.List;

public class NuevoEjercicioEntrenamientoDto {
    private Long ejercicioId;
    private int nivelEsfuerzoPercibido;
    private List<NuevaSerieEntrenamientoDto> seriesRealizadas;

    public Long getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Long ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public int getNivelEsfuerzoPercibido() {
        return nivelEsfuerzoPercibido;
    }

    public void setNivelEsfuerzoPercibido(int nivelEsfuerzoPercibido) {
        this.nivelEsfuerzoPercibido = nivelEsfuerzoPercibido;
    }

    public List<NuevaSerieEntrenamientoDto> getSeriesRealizadas() {
        return seriesRealizadas;
    }

    public void setSeriesRealizadas(List<NuevaSerieEntrenamientoDto> seriesRealizadas) {
        this.seriesRealizadas = seriesRealizadas;
    }

    public NuevoEjercicioEntrenamientoDto() {
    }
}
