package com.leandro.routineapp.dto;

public class NuevaSerieEntrenamientoDto {
    private int numeroSerie;
    private int repeticionesRealizadas;
    private boolean objetivoCumplido;

    private double pesoUtilizado;

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getRepeticionesRealizadas() {
        return repeticionesRealizadas;
    }

    public void setRepeticionesRealizadas(int repeticionesRealizadas) {
        this.repeticionesRealizadas = repeticionesRealizadas;
    }

    public boolean isObjetivoCumplido() {
        return objetivoCumplido;
    }

    public void setObjetivoCumplido(boolean objetivoCumplido) {
        this.objetivoCumplido = objetivoCumplido;
    }

    public double getPesoUtilizado() {
        return pesoUtilizado;
    }

    public void setPesoUtilizado(double pesoUtilizado) {
        this.pesoUtilizado = pesoUtilizado;
    }

    public NuevaSerieEntrenamientoDto() {
    }
}
