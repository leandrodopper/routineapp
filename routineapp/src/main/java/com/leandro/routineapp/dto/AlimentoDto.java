package com.leandro.routineapp.dto;

public class AlimentoDto {
    private Long id;
    private String nombre;
    private double cantidad;
    private Long comidaId;

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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getComidaId() {
        return comidaId;
    }

    public void setComidaId(Long comidaId) {
        this.comidaId = comidaId;
    }

    public AlimentoDto() {
    }
}
