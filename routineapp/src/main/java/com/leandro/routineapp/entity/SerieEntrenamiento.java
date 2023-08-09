package com.leandro.routineapp.entity;

import javax.persistence.*;

@Entity
public class SerieEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_ejercicio_id")
    private EntrenamientoEjercicio entrenamientoEjercicio;

    @Column(nullable = false)
    private int numeroSerie;

    @Column(nullable = false)
    private double pesoUtilizado;

    @Column(nullable = false)
    private int repeticionesRealizadas;

    @Column(nullable = false)
    private boolean objetivoCumplido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntrenamientoEjercicio getEntrenamientoEjercicio() {
        return entrenamientoEjercicio;
    }

    public void setEntrenamientoEjercicio(EntrenamientoEjercicio entrenamientoEjercicio) {
        this.entrenamientoEjercicio = entrenamientoEjercicio;
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

    public SerieEntrenamiento() {
    }
}
