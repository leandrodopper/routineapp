package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.*;
import com.leandro.routineapp.entity.*;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntrenamientoServicioImpl implements EntrenamientoServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Autowired
    private EntrenamientoRepositorio entrenamientoRepositorio;

    @Autowired
    private EntrenamientoEjercicioRepositorio entrenamientoEjercicioRepositorio;

    @Autowired
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;

    @Override
    public EntrenamientoDto guardarEntrenamiento(GuardarEntrenoDto guardarEntrenoDto, Long id_usuario) {
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setFecha(new Timestamp(System.currentTimeMillis()));
        entrenamiento.setDuracionMinutos(guardarEntrenoDto.getDuracionMinutos());

        Usuario usuario = usuarioRepositorio.getById(id_usuario);
        DiaRutina diaRutina = diaRutinaRepositorio.getById(guardarEntrenoDto.getDiaRutinaId());
        entrenamiento.setUsuario(usuario);
        entrenamiento.setDiaRutina(diaRutina);

        entrenamientoRepositorio.save(entrenamiento);

        List<EntrenamientoEjercicio> ejerciciosRealizados = new ArrayList<>();
        if (guardarEntrenoDto.getEjerciciosRealizados() != null) {
            for (NuevoEjercicioEntrenamientoDto ejercicioDto : guardarEntrenoDto.getEjerciciosRealizados()) {
                EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
                entrenamientoEjercicio.setNivelEsfuerzoPercibido(ejercicioDto.getNivelEsfuerzoPercibido());

                Optional<Ejercicio> ejercicioOptional = ejercicioRepositorio.findById(ejercicioDto.getEjercicioId());
                if (ejercicioOptional.isEmpty()) {
                    throw new ResourceNotFoundException("Ejercicio", "id", ejercicioDto.getEjercicioId().toString());
                }

                Ejercicio ejercicio = ejercicioOptional.get();
                entrenamientoEjercicio.setEjercicio(ejercicio);

                entrenamientoEjercicio.setEntrenamiento(entrenamiento);

                entrenamientoEjercicioRepositorio.save(entrenamientoEjercicio);

                List<SerieEntrenamiento> seriesRealizadas = new ArrayList<>();
                if (ejercicioDto.getSeriesRealizadas() != null) {
                    for (NuevaSerieEntrenamientoDto serieDto : ejercicioDto.getSeriesRealizadas()) {
                        SerieEntrenamiento serie = new SerieEntrenamiento();
                        serie.setNumeroSerie(serieDto.getNumeroSerie());
                        serie.setRepeticionesRealizadas(serieDto.getRepeticionesRealizadas());
                        serie.setObjetivoCumplido(serieDto.isObjetivoCumplido());
                        serie.setPesoUtilizado(serieDto.getPesoUtilizado());

                        serie.setEntrenamientoEjercicio(entrenamientoEjercicio);
                        seriesRealizadas.add(serie);

                        serieEntrenamientoRepositorio.save(serie);
                    }
                }
                entrenamientoEjercicio.setSeriesRealizadas(seriesRealizadas);

                ejerciciosRealizados.add(entrenamientoEjercicio);
            }
        }
        entrenamiento.setEjercicios_realizados(ejerciciosRealizados);

        EntrenamientoDto resultado = convertirEntrenamientoADto(entrenamiento);
        return resultado;
    }



    public Entrenamiento convertirDtoAEntrenamiento(EntrenamientoDto entrenamientoDto) {
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setId(entrenamientoDto.getId());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        entrenamiento.setFecha(date);
        entrenamiento.setDuracionMinutos(entrenamientoDto.getDuracionMinutos());

        Usuario usuario = usuarioRepositorio.getById(entrenamientoDto.getUsuarioId());

        DiaRutina diaRutina = diaRutinaRepositorio.getById(entrenamientoDto.getDiaRutinaId());
        entrenamiento.setUsuario(usuario);
        entrenamiento.setDiaRutina(diaRutina);

        List<EntrenamientoEjercicio> ejerciciosRealizados = new ArrayList<>();
        if (entrenamientoDto.getEjerciciosRealizados() != null) {
            for (EntrenamientoEjercicioDto ejercicioDto : entrenamientoDto.getEjerciciosRealizados()) {
                EntrenamientoEjercicio ejercicio = convertirDtoAEntrenamientoEjercicio(ejercicioDto);
                ejerciciosRealizados.add(ejercicio);
            }
        }
        entrenamiento.setEjercicios_realizados(ejerciciosRealizados);
        return entrenamiento;
    }


    private  EntrenamientoEjercicio convertirDtoAEntrenamientoEjercicio(EntrenamientoEjercicioDto entrenamientoEjercicioDto) {
        EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
        entrenamientoEjercicio.setId(entrenamientoEjercicioDto.getId());
        entrenamientoEjercicio.setNivelEsfuerzoPercibido(entrenamientoEjercicioDto.getNivelEsfuerzoPercibido());

        Entrenamiento entrenamiento = entrenamientoRepositorio.getById(entrenamientoEjercicioDto.getEntrenamientoId());
        Ejercicio ejercicio = ejercicioRepositorio.getById(entrenamientoEjercicioDto.getEjercicioId());
        entrenamientoEjercicio.setEntrenamiento(entrenamiento);
        entrenamientoEjercicio.setEjercicio(ejercicio);

        List<SerieEntrenamiento> seriesRealizadas = new ArrayList<>();
        if (entrenamientoEjercicioDto.getSeriesRealizadas() != null) {
            for (SerieEntrenamientoDto serieDto : entrenamientoEjercicioDto.getSeriesRealizadas()) {
                SerieEntrenamiento serie = convertirDtoASerieEntrenamiento(serieDto);
                seriesRealizadas.add(serie);
            }
        }
        entrenamientoEjercicio.setSeriesRealizadas(seriesRealizadas);

        return entrenamientoEjercicio;
    }

    private  SerieEntrenamiento convertirDtoASerieEntrenamiento(SerieEntrenamientoDto serieDto) {
        SerieEntrenamiento serie = new SerieEntrenamiento();
        serie.setId(serieDto.getId());
        serie.setNumeroSerie(serieDto.getNumeroSerie());
        serie.setRepeticionesRealizadas(serieDto.getRepeticionesRealizadas());
        serie.setObjetivoCumplido(serieDto.isObjetivoCumplido());

        return serie;
    }

    private EntrenamientoDto convertirEntrenamientoADto(Entrenamiento entrenamiento){
        EntrenamientoDto entrenamientoDto = new EntrenamientoDto();
        entrenamientoDto.setId(entrenamiento.getId());
        entrenamientoDto.setUsuarioId(entrenamiento.getUsuario().getId());
        entrenamientoDto.setDiaRutinaId(entrenamiento.getDiaRutina().getId());
        entrenamientoDto.setFecha(entrenamiento.getFecha().toString());
        entrenamientoDto.setDuracionMinutos(entrenamiento.getDuracionMinutos());

        List<EntrenamientoEjercicioDto> ejerciciosRealizadosDto = new ArrayList<>();
        if (entrenamiento.getEjercicios_realizados() != null) {
            for (EntrenamientoEjercicio ejercicio : entrenamiento.getEjercicios_realizados()) {
                EntrenamientoEjercicioDto ejercicioDto = convertirEntrenamientoEjercicioADto(ejercicio);
                ejerciciosRealizadosDto.add(ejercicioDto);
            }
        }
        entrenamientoDto.setEjerciciosRealizados(ejerciciosRealizadosDto);

        return entrenamientoDto;
    }

    private EntrenamientoEjercicioDto convertirEntrenamientoEjercicioADto(EntrenamientoEjercicio entrenamientoEjercicio) {
        EntrenamientoEjercicioDto ejercicioDto = new EntrenamientoEjercicioDto();
        ejercicioDto.setId(entrenamientoEjercicio.getId());
        ejercicioDto.setNivelEsfuerzoPercibido(entrenamientoEjercicio.getNivelEsfuerzoPercibido());
        ejercicioDto.setEntrenamientoId(entrenamientoEjercicio.getEntrenamiento().getId());
        ejercicioDto.setEjercicioId(entrenamientoEjercicio.getEjercicio().getId());

        List<SerieEntrenamientoDto> seriesRealizadasDto = new ArrayList<>();
        if (entrenamientoEjercicio.getSeriesRealizadas() != null) {
            for (SerieEntrenamiento serie : entrenamientoEjercicio.getSeriesRealizadas()) {
                SerieEntrenamientoDto serieDto = convertirSerieEntrenamientoADto(serie);
                seriesRealizadasDto.add(serieDto);
            }
        }
        ejercicioDto.setSeriesRealizadas(seriesRealizadasDto);

        return ejercicioDto;
    }

    private SerieEntrenamientoDto convertirSerieEntrenamientoADto(SerieEntrenamiento serie) {
        SerieEntrenamientoDto serieDto = new SerieEntrenamientoDto();
        serieDto.setId(serie.getId());
        serieDto.setNumeroSerie(serie.getNumeroSerie());
        serieDto.setRepeticionesRealizadas(serie.getRepeticionesRealizadas());
        serieDto.setObjetivoCumplido(serie.isObjetivoCumplido());
        serieDto.setPesoUtilizado(serie.getPesoUtilizado());


        return serieDto;
    }
}
