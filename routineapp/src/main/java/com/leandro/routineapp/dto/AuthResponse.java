package com.leandro.routineapp.dto;

import com.leandro.routineapp.entity.Usuario;

import java.util.Optional;

public class AuthResponse {
    private String token;
    private Optional<Usuario> usuario;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Optional<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(Optional<Usuario> usuario) {
        this.usuario = usuario;
    }

    public AuthResponse(String token, Optional<Usuario> usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}
