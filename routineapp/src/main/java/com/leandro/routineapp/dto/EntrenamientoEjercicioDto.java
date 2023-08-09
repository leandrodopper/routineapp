package com.leandro.routineapp.dto;


import java.util.List;

public class EntrenamientoEjercicioDto {

    private Long id;

    private Long entrenamientoId;

    private Long ejercicioId;

    private List<SerieEntrenamientoDto> seriesRealizadas;

    private int nivelEsfuerzoPercibido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntrenamientoId() {
        return entrenamientoId;
    }

    public void setEntrenamientoId(Long entrenamientoId) {
        this.entrenamientoId = entrenamientoId;
    }

    public Long getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Long ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public List<SerieEntrenamientoDto> getSeriesRealizadas() {
        return seriesRealizadas;
    }

    public void setSeriesRealizadas(List<SerieEntrenamientoDto> seriesRealizadas) {
        this.seriesRealizadas = seriesRealizadas;
    }

    public int getNivelEsfuerzoPercibido() {
        return nivelEsfuerzoPercibido;
    }

    public void setNivelEsfuerzoPercibido(int nivelEsfuerzoPercibido) {
        this.nivelEsfuerzoPercibido = nivelEsfuerzoPercibido;
    }

    public EntrenamientoEjercicioDto() {
    }
}
