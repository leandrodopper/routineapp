package com.leandro.routineapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ejercicio_dia_rutina")
public class EjercicioDiaRutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;

    @ManyToOne
    @JoinColumn(name = "dia_rutina_id")
    private DiaRutina diaRutina;

    @Column(nullable = false)
    private int series;

    @Column(nullable = false)
    private int repeticiones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public DiaRutina getDiaRutina() {
        return diaRutina;
    }

    public void setDiaRutina(DiaRutina diaRutina) {
        this.diaRutina = diaRutina;
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

    public EjercicioDiaRutina() {
    }


}
