package com.leandro.routineapp.dto;



public class EjercicioDto {
    private Long id;
    private String username_creador;
    private String nombre;
    private String descripcion;
    private String grupo_muscular;
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

    public String getUsername_creador() {
        return username_creador;
    }

    public void setUsername_creador(String username_creador) {
        this.username_creador = username_creador;
    }



    public EjercicioDto() {
    }
}
