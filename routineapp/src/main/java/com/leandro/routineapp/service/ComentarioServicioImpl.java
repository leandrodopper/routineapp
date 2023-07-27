package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.ComentarioDto;
import com.leandro.routineapp.dto.ComentarioRespuesta;
import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.exceptions.ForbiddenException;
import com.leandro.routineapp.repository.ComentarioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{
    @Autowired
    RutinaRepositorio rutinaRepositorio;

    @Autowired
    ComentarioRepositorio comentarioRepositorio;
    @Override
    public ComentarioDto publicarComentario(ComentarioDto comentarioDto, Long rutina_id, Long comentario_padre_id) {
        Rutina rutina =  rutinaRepositorio.getById(rutina_id);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        List<Comentario> emptyList = new ArrayList<>();
        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDto.getContenido());
        comentario.setFecha(date);
        comentario.setRutina(rutina);
        comentario.setUsuario(comentarioDto.getUsuario());
        comentario.setComentarioPadre(null);
        comentario.setRespuestas(emptyList);

        if (comentario_padre_id != null) {
            Comentario comentarioPadre = comentarioRepositorio.getById(comentario_padre_id);
            comentario.setComentarioPadre(comentarioPadre);
        }

        Comentario comentariocreado = comentarioRepositorio.save(comentario);

        return mapearDto(comentariocreado);
    }

    @Override
    public ComentarioRespuesta getComentariosRutina(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, Long rutina_id) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina, sort);


        Page<Comentario> comentariosPage = comentarioRepositorio.findByRutina(rutinaRepositorio.getById(rutina_id), pageable);

        List<ComentarioDto> comentariosDtoList = comentariosPage.getContent().stream()
                .map(this::mapearDto)
                .collect(Collectors.toList());

        ComentarioRespuesta comentarioRespuesta = new ComentarioRespuesta();
        comentarioRespuesta.setContenido(comentariosDtoList);
        comentarioRespuesta.setNumPagina(comentariosPage.getNumber());
        comentarioRespuesta.setTamPagina(comentariosPage.getSize());
        comentarioRespuesta.setTotalElementos(comentariosPage.getTotalElements());
        comentarioRespuesta.setTotalPaginas(comentariosPage.getTotalPages());
        comentarioRespuesta.setUltima(comentariosPage.isLast());

        return comentarioRespuesta;
    }

    @Override
    public void eliminarComentario(Long id_comentario, String username) {
        Comentario comentario = comentarioRepositorio.getById(id_comentario);
        if(!comentario.getUsuario().equals(username)){
            throw new ForbiddenException("Denegado. Solo el autor puede borrar el comentario");
        }
        comentarioRepositorio.deleteById(id_comentario);
    }


    public ComentarioDto mapearDto(Comentario comentario){
        ComentarioDto comentarioDto = new ComentarioDto();
        List<Comentario> listarespuestas = comentario.getRespuestas();
        List<ComentarioDto> listarespuestasDto = new ArrayList<>();

        comentarioDto.setId(comentario.getId());
        comentarioDto.setFecha(comentario.getFecha().toString());
        comentarioDto.setContenido(comentario.getContenido());
        comentarioDto.setRutina_id(comentario.getRutina().getId());
        comentarioDto.setUsuario(comentario.getUsuario());

        Long comentarioPadreId = comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null;
        comentarioDto.setComentarioPadre_id(comentarioPadreId);

        for (Comentario comentario1 : listarespuestas){
            listarespuestasDto.add(mapearDto(comentario1));
        }
        comentarioDto.setRespuestas(listarespuestasDto);

        return comentarioDto;
    }

}
