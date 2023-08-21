package com.leandro.routineapp.dto;

import java.util.List;

public class DietaDto {
    private Long id;
    private String nombre;
    private Long creadorId;

    private String usernameCreador;
    private List<ComidaDto> comidas;

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

    public Long getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Long creadorId) {
        this.creadorId = creadorId;
    }


    public List<ComidaDto> getComidas() {
        return comidas;
    }

    public void setComidas(List<ComidaDto> comidas) {
        this.comidas = comidas;
    }

    public String getUsernameCreador() {
        return usernameCreador;
    }

    public void setUsernameCreador(String usernameCreador) {
        this.usernameCreador = usernameCreador;
    }

    public DietaDto() {
    }
}
