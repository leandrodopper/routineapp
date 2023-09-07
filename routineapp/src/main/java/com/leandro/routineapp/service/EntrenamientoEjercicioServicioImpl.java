package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EntrenamientoEjercicioDto;
import com.leandro.routineapp.dto.SerieEntrenamientoDto;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.EntrenamientoEjercicio;
import com.leandro.routineapp.entity.SerieEntrenamiento;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.EntrenamientoEjercicioRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrenamientoEjercicioServicioImpl implements EntrenamientoEjercicioServicio{

    @Autowired
    private EntrenamientoEjercicioRepositorio entrenamientoEjercicioRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Override
    public List<Integer> obtenerEsfuerzosPorUsuarioYEjercicio(Long id_usuario, Long id_ejercicio) {
        Optional<Usuario> usuario= usuarioRepositorio.findById(id_usuario);
        Optional<Ejercicio> ejercicio=ejercicioRepositorio.findById(id_ejercicio);

        if(usuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el usuario con id: "+id_usuario);
        }
        if(ejercicio.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el ejercicio con id: "+ejercicio);
        }
        return entrenamientoEjercicioRepositorio.obtenerEsfuerzoPercibidoPorUsuarioYEjercicio(id_usuario,id_ejercicio);
    }


    public EntrenamientoEjercicioDto convertEntrenamientoEjercicioToDto(EntrenamientoEjercicio entity) {
        EntrenamientoEjercicioDto dto = new EntrenamientoEjercicioDto();
        dto.setId(entity.getId());
        dto.setEntrenamientoId(entity.getEntrenamiento().getId());
        dto.setEjercicioId(entity.getEjercicio().getId());
        dto.setSeriesRealizadas(entity.getSeriesRealizadas()
                .stream()
                .map(this::convertSerieEntrenamientoToDto)
                .collect(Collectors.toList()));
        dto.setNivelEsfuerzoPercibido(entity.getNivelEsfuerzoPercibido());
        return dto;
    }

    public SerieEntrenamientoDto convertSerieEntrenamientoToDto(SerieEntrenamiento serie) {
        SerieEntrenamientoDto serieDto = new SerieEntrenamientoDto();
        serieDto.setId(serie.getId());
        serieDto.setEntrenamientoEjercicioId(serie.getEntrenamientoEjercicio().getId());
        serieDto.setNumeroSerie(serie.getNumeroSerie());
        serieDto.setPesoUtilizado(serie.getPesoUtilizado());
        serieDto.setRepeticionesRealizadas(serie.getRepeticionesRealizadas());
        serieDto.setObjetivoCumplido(serie.isObjetivoCumplido());
        return serieDto;
    }


}
