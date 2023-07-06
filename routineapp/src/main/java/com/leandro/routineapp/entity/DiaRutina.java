package com.leandro.routineapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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


    @OneToMany(mappedBy = "diaRutina", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("diaRutina")
    private Set<EjercicioDiaRutina> ejerciciosDiaRutina;

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

    public Set<EjercicioDiaRutina> getEjerciciosDiaRutina() {
        return ejerciciosDiaRutina;
    }

    public void setEjerciciosDiaRutina(Set<EjercicioDiaRutina> ejerciciosDiaRutina) {
        this.ejerciciosDiaRutina = ejerciciosDiaRutina;
    }

    public DiaRutina() {
    }

    public void addEjercicioDiaRutina(EjercicioDiaRutina ejercicioDiaRutina) {
        ejerciciosDiaRutina.add(ejercicioDiaRutina);
        ejercicioDiaRutina.setDiaRutina(this);
    }

    public void removeEjercicioDiaRutina(EjercicioDiaRutina ejercicioDiaRutina) {
        ejerciciosDiaRutina.remove(ejercicioDiaRutina);
        ejercicioDiaRutina.setDiaRutina(null);
    }
}
