package com.leandro.routineapp.dto;

import java.util.List;

public class GuardarEntrenoDto {
    private Long diaRutinaId;
    private int duracionMinutos;
    private List<NuevoEjercicioEntrenamientoDto> ejerciciosRealizados;

    public Long getDiaRutinaId() {
        return diaRutinaId;
    }

    public void setDiaRutinaId(Long diaRutinaId) {
        this.diaRutinaId = diaRutinaId;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public List<NuevoEjercicioEntrenamientoDto> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }

    public void setEjerciciosRealizados(List<NuevoEjercicioEntrenamientoDto> ejerciciosRealizados) {
        this.ejerciciosRealizados = ejerciciosRealizados;
    }

    public GuardarEntrenoDto() {
    }
}
