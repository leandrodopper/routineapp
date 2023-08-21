package com.leandro.routineapp.dto;

import java.util.List;

public class ComidaDto {
    private Long id;
    private String nombre;
    private Long dietaId;

    private List<AlimentoDto> alimentos;

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

    public Long getDietaId() {
        return dietaId;
    }

    public void setDietaId(Long dietaId) {
        this.dietaId = dietaId;
    }

    public List<AlimentoDto> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<AlimentoDto> alimentos) {
        this.alimentos = alimentos;
    }

    public ComidaDto() {
    }
}
