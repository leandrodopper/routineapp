package com.leandro.routineapp.entity;


import javax.persistence.*;

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

    @Column(name="creador", nullable = false)
    private String creador;

    @Column(name = "puntuacion", nullable = false)
    private double puntuacion;

    @ManyToMany(mappedBy = "rutinasSeguidas", fetch = FetchType.LAZY)
    private List<Usuario> seguidores;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    private Long numPuntuaciones;


    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
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

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public Long getNumPuntuaciones() {
        return numPuntuaciones;
    }

    public void setNumPuntuaciones(Long numPuntuaciones) {
        this.numPuntuaciones = numPuntuaciones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Rutina() {
    }
}
