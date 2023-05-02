package com.leandro.routineapp.entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name="ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "grupo_muscular", nullable = false)
    private String grupo_muscular;

    @Column(name = "series", nullable = false)
    private int series;

    @Column(name = "repeticiones", nullable = false)
    private int repeticiones;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @ManyToMany(mappedBy = "ejercicios")
    private Set<DiaRutina> dia_rutina;

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

    public String getGrupo_muscular() {
        return grupo_muscular;
    }

    public void setGrupo_muscular(String grupo_muscular) {
        this.grupo_muscular = grupo_muscular;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Set<DiaRutina> getDia_rutina() {
        return dia_rutina;
    }

    public void setDia_rutina(Set<DiaRutina> dia_rutina) {
        this.dia_rutina = dia_rutina;
    }

    public Ejercicio(Long id, String nombre, String descripcion, String grupo_muscular, int series, int repeticiones, String imagen, String dificultad, Set<DiaRutina> dia_rutina) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo_muscular = grupo_muscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.dia_rutina = dia_rutina;
    }

    public Ejercicio(String nombre, String descripcion, String grupo_muscular, int series, int repeticiones, String imagen, String dificultad, Set<DiaRutina> dia_rutina) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo_muscular = grupo_muscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.dia_rutina = dia_rutina;
    }

    public Ejercicio() {
    }
}
