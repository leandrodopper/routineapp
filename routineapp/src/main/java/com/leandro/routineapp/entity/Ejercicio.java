package com.leandro.routineapp.entity;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username_creador", nullable = false)
    private String usernameCreador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "grupo_muscular", nullable = false)
    private String grupo_muscular;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @OneToMany(mappedBy = "ejercicio", cascade = CascadeType.REMOVE)
    private Set<EjercicioDiaRutina> ejerciciosDiaRutina;

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

    public Set<EjercicioDiaRutina> getEjerciciosDiaRutina() {
        return ejerciciosDiaRutina;
    }

    public void setEjerciciosDiaRutina(Set<EjercicioDiaRutina> ejerciciosDiaRutina) {
        this.ejerciciosDiaRutina = ejerciciosDiaRutina;
    }

    public Ejercicio() {
    }


    public String getUsernameCreador() {
        return usernameCreador;
    }

    public void setUsernameCreador(String usernameCreador) {
        this.usernameCreador = usernameCreador;
    }


    public Ejercicio(String usernameCreador,String nombre, String descripcion, String grupo_muscular, String imagen, String dificultad) {
        this.usernameCreador=usernameCreador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo_muscular = grupo_muscular;
        this.imagen = imagen;
        this.dificultad = dificultad;
    }
}
