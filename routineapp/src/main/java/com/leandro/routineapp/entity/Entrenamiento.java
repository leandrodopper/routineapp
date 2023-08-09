package com.leandro.routineapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "dia_rutina_id")
    private DiaRutina diaRutina;
    @Column(nullable = false)
    private Timestamp fecha;

    @Column(name = "duracion_minutos")
    private int duracionMinutos;

    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntrenamientoEjercicio> ejercicios_realizados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DiaRutina getDiaRutina() {
        return diaRutina;
    }

    public void setDiaRutina(DiaRutina diaRutina) {
        this.diaRutina = diaRutina;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public List<EntrenamientoEjercicio> getEjercicios_realizados() {
        return ejercicios_realizados;
    }

    public void setEjercicios_realizados(List<EntrenamientoEjercicio> ejercicios_realizados) {
        this.ejercicios_realizados = ejercicios_realizados;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Entrenamiento() {
    }
}
