package com.leandro.routineapp.dto;



public class EjercicioDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String grupo_muscular;
    private int series;
    private int repeticiones;
    private String imagen;
    private String dificultad;

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

    public EjercicioDto(Long id, String nombre, String descripcion, String grupo_muscular, int series, int repeticiones, String imagen, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo_muscular = grupo_muscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.dificultad = dificultad;
    }

    public EjercicioDto(String nombre, String descripcion, String grupo_muscular, int series, int repeticiones, String imagen, String dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo_muscular = grupo_muscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.dificultad = dificultad;
    }

    public EjercicioDto() {
    }
}
