package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.ComentarioDto;
import com.leandro.routineapp.dto.ComentarioRespuesta;



public interface ComentarioServicio {
    public ComentarioDto publicarComentario(ComentarioDto comentarioDto, Long rutina_id, Long comentario_padre_id);
    public ComentarioRespuesta getComentariosRutina(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, Long rutina_id);

    public void eliminarComentario(Long id_comentario, String username);
}
