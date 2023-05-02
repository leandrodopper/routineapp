package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioServicioImpl implements EjercicioServicio{

    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Override
    public EjercicioDto crearEjercicio(EjercicioDto ejercicioDto) {
        Ejercicio ejercicio = mapearEntidad(ejercicioDto);
        Ejercicio nuevoEjercicio= ejercicioRepositorio.save(ejercicio);

        EjercicioDto ejercicioRespuesta=mapearDto(nuevoEjercicio);
        return ejercicioRespuesta;
    }

    @Override
    public EjercicioRespuesta obtenerTodosEjercicios(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina,tamanoPagina, sort);
        Page<Ejercicio> ejercicios = ejercicioRepositorio.findAll(pageable);
        List<Ejercicio> listaDeEjercicios = ejercicios.getContent();
        List<EjercicioDto> contenido = listaDeEjercicios.stream().map(ejercicio -> mapearDto(ejercicio)).collect(Collectors.toList());
        EjercicioRespuesta ejercicioRespuesta = new EjercicioRespuesta();
        ejercicioRespuesta.setContenido(contenido);
        ejercicioRespuesta.setNumPagina(ejercicios.getNumber());
        ejercicioRespuesta.setTamPagina(ejercicios.getSize());
        ejercicioRespuesta.setTotalElementos(ejercicios.getTotalElements());
        ejercicioRespuesta.setTotalPaginas(ejercicios.getTotalPages());
        ejercicioRespuesta.setUltima(ejercicios.isLast());

        return ejercicioRespuesta;
    }

    @Override
    public EjercicioDto obtenerEjercicioPorId(Long id) {
        Ejercicio ejercicio = ejercicioRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Ejercicio", "id", id.toString()));
                return mapearDto(ejercicio);
    }

    @Override
    public EjercicioDto actualizarEjercicio(EjercicioDto ejercicioDto, Long id) {
        Ejercicio ejercicio = ejercicioRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Ejercicio", "id", id.toString()));

        ejercicio.setNombre(ejercicioDto.getNombre());
        ejercicio.setDescripcion(ejercicioDto.getDescripcion());
        ejercicio.setGrupo_muscular(ejercicioDto.getGrupo_muscular());
        ejercicio.setSeries(ejercicioDto.getSeries());
        ejercicio.setRepeticiones(ejercicioDto.getRepeticiones());
        ejercicio.setImagen(ejercicioDto.getImagen());
        ejercicio.setDificultad(ejercicioDto.getDificultad());

        Ejercicio ejercicioactualizado = ejercicioRepositorio.save(ejercicio);
        return mapearDto(ejercicioactualizado);
    }

    @Override
    public void eliminarEjercicio(Long id) {
        Ejercicio ejercicio = ejercicioRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Ejercicio", "id", id.toString()));
        ejercicioRepositorio.delete(ejercicio);
    }

    //Convertimos a Dto
    private EjercicioDto mapearDto(Ejercicio ejercicio){
        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setId(ejercicio.getId());
        ejercicioDto.setNombre(ejercicio.getNombre());
        ejercicioDto.setDescripcion(ejercicio.getDescripcion());
        ejercicioDto.setGrupo_muscular(ejercicio.getGrupo_muscular());
        ejercicioDto.setSeries(ejercicio.getSeries());
        ejercicioDto.setRepeticiones(ejercicio.getRepeticiones());
        ejercicioDto.setImagen(ejercicio.getImagen());
        ejercicioDto.setDificultad(ejercicio.getDificultad());
        return ejercicioDto;
    }

    //Convertimos Dto a entidad
    private Ejercicio mapearEntidad(EjercicioDto ejercicioDto){
        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setNombre(ejercicioDto.getNombre());
        ejercicio.setDescripcion(ejercicioDto.getDescripcion());
        ejercicio.setGrupo_muscular(ejercicioDto.getGrupo_muscular());
        ejercicio.setSeries(ejercicioDto.getSeries());
        ejercicio.setRepeticiones(ejercicioDto.getRepeticiones());
        ejercicio.setImagen(ejercicioDto.getImagen());
        ejercicio.setDificultad(ejercicioDto.getDificultad());
        return ejercicio;
    }
}
