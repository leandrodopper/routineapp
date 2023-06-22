package com.leandro.routineapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dia_rutina")
public class DiaRutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_id")
    @JsonBackReference
    private Rutina rutina;
    private String descripcion;
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "dia_rutina_ejercicios",
            joinColumns = @JoinColumn(name = "dia_rutina_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
    )
    private Set<Ejercicio> ejercicios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public DiaRutina(Long id, Rutina rutina, String descripcion, String nombre, Set<Ejercicio> ejercicios) {
        this.id = id;
        this.rutina = rutina;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public DiaRutina(Rutina rutina, String descripcion, String nombre, Set<Ejercicio> ejercicios) {
        this.rutina = rutina;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.ejercicios = ejercicios;
    }

    public DiaRutina() {
    }
}
