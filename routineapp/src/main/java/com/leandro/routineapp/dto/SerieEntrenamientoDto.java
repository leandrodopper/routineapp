package com.leandro.routineapp.dto;



public class SerieEntrenamientoDto {


    private Long id;


    private Long entrenamientoEjercicioId;


    private int numeroSerie;


    private double pesoUtilizado;


    private int repeticionesRealizadas;


    private boolean objetivoCumplido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntrenamientoEjercicioId() {
        return entrenamientoEjercicioId;
    }

    public void setEntrenamientoEjercicioId(Long entrenamientoEjercicioId) {
        this.entrenamientoEjercicioId = entrenamientoEjercicioId;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public double getPesoUtilizado() {
        return pesoUtilizado;
    }

    public void setPesoUtilizado(double pesoUtilizado) {
        this.pesoUtilizado = pesoUtilizado;
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

    public SerieEntrenamientoDto() {
    }
}
