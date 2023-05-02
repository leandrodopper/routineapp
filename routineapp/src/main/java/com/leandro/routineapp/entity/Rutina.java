package com.leandro.routineapp.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rutina")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre",nullable = false)
    private String nombre;
    @Column(name="descripcion",nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaRutina> dias_rutina;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DiaRutina> getDias_rutina() {
        return dias_rutina;
    }

    public void setDias_rutina(List<DiaRutina> dias_rutina) {
        this.dias_rutina = dias_rutina;
    }

    public Rutina(Long id, String nombre, String descripcion, List<DiaRutina> dias_rutina) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
    }

    public Rutina(String nombre, String descripcion, List<DiaRutina> dias_rutina) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias_rutina = dias_rutina;
    }

    public Rutina() {
    }
}
