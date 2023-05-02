package com.leandro.routineapp.exceptions;

import org.springframework.http.HttpStatus;

public class RoutineAppException extends RuntimeException{

    private static final long serialVersionUID = 7607193750764693847L;

    private HttpStatus estado;
    private String mensaje;

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public RoutineAppException(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public RoutineAppException(HttpStatus estado, String mensaje, String mensaje1) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }
}
