package com.leandro.routineapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.Rutina;


import java.util.List;

public class ComentarioDto {
    private Long id;

    private String usuario;

    private String contenido;

    private String fecha;

    private Long  comentarioPadre_id;

    private List<ComentarioDto> respuestas;

    public List<ComentarioDto> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<ComentarioDto> respuestas) {
        this.respuestas = respuestas;
    }

    private Long rutina_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getComentarioPadre_id() {
        return comentarioPadre_id;
    }

    public void setComentarioPadre_id(Long comentarioPadre_id) {
        this.comentarioPadre_id = comentarioPadre_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public Long getRutina_id() {
        return rutina_id;
    }

    public void setRutina_id(Long rutina_id) {
        this.rutina_id = rutina_id;
    }

    public ComentarioDto() {
    }
}
