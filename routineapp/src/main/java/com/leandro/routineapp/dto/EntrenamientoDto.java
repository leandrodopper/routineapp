package com.leandro.routineapp.dto;

import java.sql.Timestamp;
import java.util.List;

public class EntrenamientoDto {
    private Long id;
    private Long usuarioId;
    private Long diaRutinaId;
    private String fecha;
    private int duracionMinutos;
    private List<EntrenamientoEjercicioDto> ejerciciosRealizados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getDiaRutinaId() {
        return diaRutinaId;
    }

    public void setDiaRutinaId(Long diaRutinaId) {
        this.diaRutinaId = diaRutinaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public List<EntrenamientoEjercicioDto> getEjerciciosRealizados() {
        return ejerciciosRealizados;
    }

    public void setEjerciciosRealizados(List<EntrenamientoEjercicioDto> ejerciciosRealizados) {
        this.ejerciciosRealizados = ejerciciosRealizados;
    }

    public EntrenamientoDto() {
    }
}
