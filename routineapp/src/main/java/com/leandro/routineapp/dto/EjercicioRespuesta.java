package com.leandro.routineapp.dto;

import java.util.List;

public class EjercicioRespuesta {

    private List<EjercicioDto> contenido;
    private int numPagina;
    private int tamPagina;
    private long totalElementos;
    private long totalPaginas;
    private boolean ultima;

    public List<EjercicioDto> getContenido() {
        return contenido;
    }

    public void setContenido(List<EjercicioDto> contenido) {
        this.contenido = contenido;
    }

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    public int getTamPagina() {
        return tamPagina;
    }

    public void setTamPagina(int tamPagina) {
        this.tamPagina = tamPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public long getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(long totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public EjercicioRespuesta() {
    }
}
