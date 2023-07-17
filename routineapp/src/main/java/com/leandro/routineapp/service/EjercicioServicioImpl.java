package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioServicioImpl implements EjercicioServicio{

    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


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

    @Override
    public EjercicioRespuesta filtrarGrupoMuscular(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, String grupo_muscular) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina, sort);

        // Verificar si se proporcionó un filtro por grupo muscular
        boolean filtroProvided = grupo_muscular != null && !grupo_muscular.isEmpty();

        // Obtener todos los ejercicios sin aplicar filtro por grupo muscular
        List<Ejercicio> ejercicios = ejercicioRepositorio.findAll();

        // Filtrar manualmente por grupo muscular si se proporcionó un filtro, de lo contrario, obtener todos
        List<Ejercicio> ejerciciosFiltrados;
        if (filtroProvided) {
            ejerciciosFiltrados = ejercicios.stream()
                    .filter(ejercicio -> ejercicio.getGrupo_muscular().toLowerCase().contains(grupo_muscular.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            ejerciciosFiltrados=ejercicios.stream().collect(Collectors.toList());
        }

        // Ordenar los ejercicios filtrados en la dirección correcta
        if (sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            Collections.reverse(ejerciciosFiltrados);
        }

        // Obtener el número total de páginas según el tamaño de página especificado
        int totalPaginas = (int) Math.ceil((double) ejerciciosFiltrados.size() / tamanoPagina);

        // Verificar si el número de página solicitado es válido
        if (numeroPagina >= totalPaginas) {
            numeroPagina = totalPaginas - 1;
        }

        // Obtener el rango de ejercicios correspondiente a la página solicitada
        int desde = numeroPagina * tamanoPagina;
        int hasta = Math.min(desde + tamanoPagina, ejerciciosFiltrados.size());

        // Obtener los ejercicios de la página solicitada
        List<Ejercicio> ejerciciosPagina = ejerciciosFiltrados.subList(desde, hasta);

        List<EjercicioDto> contenido = ejerciciosPagina.stream()
                .map(ejercicio -> mapearDto(ejercicio))
                .collect(Collectors.toList());

        EjercicioRespuesta ejercicioRespuesta = new EjercicioRespuesta();
        ejercicioRespuesta.setContenido(contenido);
        ejercicioRespuesta.setNumPagina(numeroPagina);
        ejercicioRespuesta.setTamPagina(tamanoPagina);
        ejercicioRespuesta.setTotalElementos(ejerciciosFiltrados.size());
        ejercicioRespuesta.setTotalPaginas(totalPaginas);
        ejercicioRespuesta.setUltima(numeroPagina == totalPaginas - 1);

        return ejercicioRespuesta;
    }

    @Override
    public EjercicioRespuesta filtrarPorNombre(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, String nombre) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina, sort);

        // Verificar si se proporcionó un filtro por grupo muscular
        boolean filtroProvided = nombre != null && !nombre.isEmpty();

        // Obtener todos los ejercicios sin aplicar filtro por nombre
        List<Ejercicio> ejercicios = ejercicioRepositorio.findAll();

        // Filtrar manualmente por nombre si se proporcionó un filtro, de lo contrario, obtener todos
        List<Ejercicio> ejerciciosFiltrados;
        if (filtroProvided) {
            String[] palabrasClave = StringUtils.stripAccents(nombre.toLowerCase()).split("\\s+"); // Remover tildes del filtro
            ejerciciosFiltrados = ejercicios.stream()
                    .filter(ejercicio -> {
                        String nombreEjercicio = StringUtils.stripAccents(ejercicio.getNombre().toLowerCase()); // Remover tildes del nombre del ejercicio
                        return Arrays.stream(palabrasClave)
                                .allMatch(palabra -> nombreEjercicio.contains(palabra));
                    })
                    .collect(Collectors.toList());
        } else {
            ejerciciosFiltrados = ejercicios.stream().collect(Collectors.toList());
        }
        if (ejerciciosFiltrados.isEmpty()) {
            EjercicioRespuesta ejercicioRespuestaNotFound = new EjercicioRespuesta();
            ejercicioRespuestaNotFound.setContenido(Collections.emptyList());
            ejercicioRespuestaNotFound.setNumPagina(numeroPagina);
            ejercicioRespuestaNotFound.setTamPagina(tamanoPagina);
            ejercicioRespuestaNotFound.setTotalElementos(0);
            ejercicioRespuestaNotFound.setTotalPaginas(0);
            ejercicioRespuestaNotFound.setUltima(true);
            return ejercicioRespuestaNotFound;
        }

        // Ordenar los ejercicios filtrados en la dirección correcta
        if (sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            Collections.reverse(ejerciciosFiltrados);
        }

        // Obtener el número total de páginas según el tamaño de página especificado
        int totalPaginas = (int) Math.ceil((double) ejerciciosFiltrados.size() / tamanoPagina);

        // Verificar si el número de página solicitado es válido
        if (numeroPagina >= totalPaginas) {
            numeroPagina = totalPaginas - 1;
        }

        // Obtener el rango de ejercicios correspondiente a la página solicitada
        int desde = numeroPagina * tamanoPagina;
        int hasta = Math.min(desde + tamanoPagina, ejerciciosFiltrados.size());

        // Obtener los ejercicios de la página solicitada
        List<Ejercicio> ejerciciosPagina = ejerciciosFiltrados.subList(desde, hasta);

        List<EjercicioDto> contenido = ejerciciosPagina.stream()
                .map(ejercicio -> mapearDto(ejercicio))
                .collect(Collectors.toList());

        EjercicioRespuesta ejercicioRespuesta = new EjercicioRespuesta();
        ejercicioRespuesta.setContenido(contenido);
        ejercicioRespuesta.setNumPagina(numeroPagina);
        ejercicioRespuesta.setTamPagina(tamanoPagina);
        ejercicioRespuesta.setTotalElementos(ejerciciosFiltrados.size());
        ejercicioRespuesta.setTotalPaginas(totalPaginas);
        ejercicioRespuesta.setUltima(numeroPagina == totalPaginas - 1);

        return ejercicioRespuesta;
    }

    @Override
    public EjercicioRespuesta filtrarPorCreador(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, String creador) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina, sort);

        // Verificar si se proporcionó un filtro por creador
        boolean filtroProvided = creador != null && !creador.isEmpty();

        // Obtener los ejercicios filtrados por el nombre del creador
        Page<Ejercicio> paginaEjercicios;
        if (filtroProvided) {
            paginaEjercicios = ejercicioRepositorio.findByUsernameCreador(creador, pageable);
        } else {
            paginaEjercicios = ejercicioRepositorio.findAll(pageable);
        }

        List<Ejercicio> ejerciciosFiltrados = paginaEjercicios.getContent();

        if (ejerciciosFiltrados.isEmpty()) {
            EjercicioRespuesta ejercicioRespuestaNotFound = new EjercicioRespuesta();
            ejercicioRespuestaNotFound.setContenido(Collections.emptyList());
            ejercicioRespuestaNotFound.setNumPagina(numeroPagina);
            ejercicioRespuestaNotFound.setTamPagina(tamanoPagina);
            ejercicioRespuestaNotFound.setTotalElementos(paginaEjercicios.getTotalElements());
            ejercicioRespuestaNotFound.setTotalPaginas(paginaEjercicios.getTotalPages());
            ejercicioRespuestaNotFound.setUltima(true);
            return ejercicioRespuestaNotFound;
        }

        // Ordenar los ejercicios filtrados en la dirección correcta
        if (sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            Collections.reverse(ejerciciosFiltrados);
        }

        List<EjercicioDto> contenido = ejerciciosFiltrados.stream()
                .map(ejercicio -> mapearDto(ejercicio))
                .collect(Collectors.toList());

        EjercicioRespuesta ejercicioRespuesta = new EjercicioRespuesta();
        ejercicioRespuesta.setContenido(contenido);
        ejercicioRespuesta.setNumPagina(numeroPagina);
        ejercicioRespuesta.setTamPagina(tamanoPagina);
        ejercicioRespuesta.setTotalElementos(paginaEjercicios.getTotalElements());
        ejercicioRespuesta.setTotalPaginas(paginaEjercicios.getTotalPages());
        ejercicioRespuesta.setUltima(numeroPagina == paginaEjercicios.getTotalPages() - 1);

        return ejercicioRespuesta;
    }

    @Override
    public EjercicioRespuesta filtrarPorNombreYGrupoMuscular(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir, String filtro) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamanoPagina, sort);

        // Verificar si se proporcionó un filtro
        boolean filtroProvided = filtro != null && !filtro.isEmpty();

        // Obtener todos los ejercicios sin aplicar filtros
        List<Ejercicio> ejercicios = ejercicioRepositorio.findAll();

        // Filtrar manualmente por nombre y grupo muscular si se proporcionó el filtro, de lo contrario, obtener todos
        List<Ejercicio> ejerciciosFiltrados;
        if (filtroProvided) {
            String[] palabrasClave = StringUtils.stripAccents(filtro.toLowerCase()).split("\\s+"); // Remover tildes del filtro
            ejerciciosFiltrados = ejercicios.stream()
                    .filter(ejercicio ->
                            Arrays.stream(palabrasClave)
                                    .allMatch(palabra -> StringUtils.stripAccents(ejercicio.getNombre().toLowerCase()).contains(palabra))
                                    || ejercicio.getGrupo_muscular().toLowerCase().contains(filtro.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            ejerciciosFiltrados = ejercicios.stream().collect(Collectors.toList());
        }
        if (ejerciciosFiltrados.isEmpty()) {
            EjercicioRespuesta ejercicioRespuestaNotFound = new EjercicioRespuesta();
            ejercicioRespuestaNotFound.setContenido(Collections.emptyList());
            ejercicioRespuestaNotFound.setNumPagina(numeroPagina);
            ejercicioRespuestaNotFound.setTamPagina(tamanoPagina);
            ejercicioRespuestaNotFound.setTotalElementos(0);
            ejercicioRespuestaNotFound.setTotalPaginas(0);
            ejercicioRespuestaNotFound.setUltima(true);
            return ejercicioRespuestaNotFound;
        }

        // Ordenar los ejercicios filtrados en la dirección correcta
        if (sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            Collections.reverse(ejerciciosFiltrados);
        }

        // Obtener el número total de páginas según el tamaño de página especificado
        int totalPaginas = (int) Math.ceil((double) ejerciciosFiltrados.size() / tamanoPagina);

        // Verificar si el número de página solicitado es válido
        if (numeroPagina >= totalPaginas) {
            numeroPagina = totalPaginas - 1;
        }

        // Obtener el rango de ejercicios correspondiente a la página solicitada
        int desde = numeroPagina * tamanoPagina;
        int hasta = Math.min(desde + tamanoPagina, ejerciciosFiltrados.size());

        // Obtener los ejercicios de la página solicitada
        List<Ejercicio> ejerciciosPagina = ejerciciosFiltrados.subList(desde, hasta);

        List<EjercicioDto> contenido = ejerciciosPagina.stream()
                .map(ejercicio -> mapearDto(ejercicio))
                .collect(Collectors.toList());

        EjercicioRespuesta ejercicioRespuesta = new EjercicioRespuesta();
        ejercicioRespuesta.setContenido(contenido);
        ejercicioRespuesta.setNumPagina(numeroPagina);
        ejercicioRespuesta.setTamPagina(tamanoPagina);
        ejercicioRespuesta.setTotalElementos(ejerciciosFiltrados.size());
        ejercicioRespuesta.setTotalPaginas(totalPaginas);
        ejercicioRespuesta.setUltima(numeroPagina == totalPaginas - 1);

        return ejercicioRespuesta;
    }


    //Convertimos a Dto
    private EjercicioDto mapearDto(Ejercicio ejercicio){
        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setId(ejercicio.getId());
        ejercicioDto.setUsername_creador(ejercicio.getUsernameCreador());
        ejercicioDto.setNombre(ejercicio.getNombre());
        ejercicioDto.setDescripcion(ejercicio.getDescripcion());
        ejercicioDto.setGrupo_muscular(ejercicio.getGrupo_muscular());
        ejercicioDto.setImagen(ejercicio.getImagen());
        ejercicioDto.setDificultad(ejercicio.getDificultad());
        return ejercicioDto;
    }

    //Convertimos Dto a entidad
    private Ejercicio mapearEntidad(EjercicioDto ejercicioDto){
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setUsernameCreador(ejercicioDto.getUsername_creador());
        ejercicio.setNombre(ejercicioDto.getNombre());
        ejercicio.setDescripcion(ejercicioDto.getDescripcion());
        ejercicio.setGrupo_muscular(ejercicioDto.getGrupo_muscular());
        ejercicio.setImagen(ejercicioDto.getImagen());
        ejercicio.setDificultad(ejercicioDto.getDificultad());
        return ejercicio;
    }
}
