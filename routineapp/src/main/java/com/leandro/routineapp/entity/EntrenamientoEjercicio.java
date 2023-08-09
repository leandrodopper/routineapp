package com.leandro.routineapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class EntrenamientoEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id")
    private Entrenamiento entrenamiento;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;

    @OneToMany(mappedBy = "entrenamientoEjercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieEntrenamiento> seriesRealizadas;

    @Column(name = "nivel_esfuerzo_percibido")
    private int nivelEsfuerzoPercibido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entrenamiento getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<SerieEntrenamiento> getSeriesRealizadas() {
        return seriesRealizadas;
    }

    public void setSeriesRealizadas(List<SerieEntrenamiento> seriesRealizadas) {
        this.seriesRealizadas = seriesRealizadas;
    }

    public int getNivelEsfuerzoPercibido() {
        return nivelEsfuerzoPercibido;
    }

    public void setNivelEsfuerzoPercibido(int nivelEsfuerzoPercibido) {
        this.nivelEsfuerzoPercibido = nivelEsfuerzoPercibido;
    }

    public EntrenamientoEjercicio() {
    }
}
