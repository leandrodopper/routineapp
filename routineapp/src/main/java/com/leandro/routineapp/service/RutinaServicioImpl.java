package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.*;
import com.leandro.routineapp.entity.*;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RutinaServicioImpl implements RutinaServicio{

    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Override
    public RutinaDto crearRutina(RutinaDto rutinaDto) {
        Rutina rutina = mapearEntidad(rutinaDto);
        Rutina nuevaRutina = rutinaRepositorio.save(rutina);

        RutinaDto rutinaRespuesta=mapearDto(nuevaRutina);
        return rutinaRespuesta;
    }

    /*@Override
    public RutinaDto obtenerRutinaPorId(Long id) {
        Rutina rutina = rutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Rutina", "id", id.toString()));
        return mapearDto(rutina);
    }*/

    @Override
    public RutinaDto obtenerRutinaPorId(Long id) {
        Optional<Rutina> rutinaOptional = rutinaRepositorio.findById(id);

        if (rutinaOptional.isPresent()) {
            Rutina rutina = rutinaOptional.get();
            // Mapear la entidad Rutina a RutinaDto
            RutinaDto rutinaDto = mapearDto(rutina);

            return rutinaDto;
        } else {
            // Manejar el caso en que no se encuentre la rutina con el id proporcionado
            throw new ResourceNotFoundException("Rutina", "id", id.toString());
        }
    }

    @Override
    public RutinaRespuesta obtenerRutinas(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina,tamanoPagina, sort);
        Page<Rutina> rutinas = rutinaRepositorio.findAll(pageable);
        List<RutinaDto> contenido = rutinas.stream().map(rutina -> mapearDto(rutina)).collect(Collectors.toList());
        RutinaRespuesta rutinaRespuesta = new RutinaRespuesta();
        rutinaRespuesta.setContenido(contenido);
        rutinaRespuesta.setNumPagina(rutinas.getNumber());
        rutinaRespuesta.setTamPagina(rutinas.getSize());
        rutinaRespuesta.setTotalElementos(rutinas.getTotalElements());
        rutinaRespuesta.setTotalPaginas(rutinas.getTotalPages());
        rutinaRespuesta.setUltima(rutinas.isLast());
        return rutinaRespuesta;

    }

    @Override
    public void eliminarRutina(Long id) {
        Rutina rutina  = rutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Rutina", "id", id.toString()));

        List<Usuario> usuarios = rutina.getSeguidores();

        for (Usuario usuario : usuarios){
            usuario.getRutinasSeguidas().remove(rutina);
            usuarioRepositorio.save(usuario);
        }
        rutinaRepositorio.delete(rutina);
    }

    @Override
    public RutinaDto actualizarRutina(RutinaDto rutinaDto, Long id) {
        Rutina rutina = new Rutina();
        rutina = rutinaRepositorio.getById(id);
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setDescripcion(rutinaDto.getDescripcion());
        Rutina rutinaActualizada = rutinaRepositorio.save(rutina);
        RutinaDto repsuesta = mapearDto(rutinaActualizada);
        return repsuesta;
    }


    @Override
    public void seguirRutina(Long id_rutina, Long id_usuario) {
        Optional<Usuario> usuario_opt = usuarioRepositorio.findById(id_usuario);
        Optional<Rutina> rutina_opt = rutinaRepositorio.findById(id_rutina);

        if (usuario_opt.isPresent() && rutina_opt.isPresent()) {

            Usuario usuario = usuario_opt.get();
            Rutina rutina = rutina_opt.get();
            if (usuario.getRutinasSeguidas().contains(rutina)){
                throw  new IllegalArgumentException("El usuario ya sigue la rutina con id: "+rutina.getId());
            }
            usuario.getRutinasSeguidas().add(rutina);
            usuarioRepositorio.save(usuario);
            rutina.getSeguidores().add(usuario);
            rutinaRepositorio.save(rutina);
        }else{
            throw new ResourceNotFoundException("Usuario o rutina","usuario_id: "+id_usuario,"rutina_id: "+id_rutina);
        }
    }

    @Override
    public void dejarseguirRutina(Long id_rutina, Long id_usuario) {
        Optional<Usuario> usuario_opt = usuarioRepositorio.findById(id_usuario);
        Optional<Rutina> rutina_opt = rutinaRepositorio.findById(id_rutina);

        if (usuario_opt.isPresent() && rutina_opt.isPresent()) {

            Usuario usuario = usuario_opt.get();
            Rutina rutina = rutina_opt.get();
            if (!usuario.getRutinasSeguidas().contains(rutina)){
                throw  new IllegalArgumentException("El usuario no sigue la rutina con id: "+rutina.getId());
            }
            usuario.getRutinasSeguidas().remove(rutina);
            usuarioRepositorio.save(usuario);
            rutina.getSeguidores().remove(usuario);
            rutinaRepositorio.save(rutina);
        }else{
            throw new ResourceNotFoundException("Usuario o rutina","usuario_id: "+id_usuario,"rutina_id: "+id_rutina);
        }
    }

    @Override
    public List<RutinaDto> obtenerRutinasSeguidasUsuario(Long id_usuario) {
        Optional<Usuario> usuario_opt = usuarioRepositorio.findById(id_usuario);
        List<RutinaDto> rutinas = new ArrayList<>();
        if (usuario_opt.isPresent()){
            Usuario usuario = usuario_opt.get();
            List<Rutina> rutinas_seguidas = usuario.getRutinasSeguidas();
            for (Rutina rutina : rutinas_seguidas){
                rutinas.add( mapearDto(rutina));
            }
            return rutinas;
        }else{
            throw new ResourceNotFoundException("Usuario","usuario_id: ","");
        }
    }

    @Override
    public List<RutinaDto> obtenerRutinasCreadasUsuario(Long id_usuario) {
        Optional<Usuario> usuario_opt = usuarioRepositorio.findById(id_usuario);
        List<Rutina> rutinas = new ArrayList<>();
        List<RutinaDto> respuesta = new ArrayList<>();

        if (usuario_opt.isPresent()){
            rutinas=rutinaRepositorio.findByCreador(usuario_opt.get().getEmail());
            for (Rutina rutina : rutinas){
                respuesta.add(mapearDto(rutina));
            }
            return respuesta;
        }else{
            throw new ResourceNotFoundException("Usuario","usuario_id: ","");
        }
    }

    @Override
    public List<RutinaDto> obtenerRutinasPorNombre(String nombre) {
        List<Rutina> rutinas = new ArrayList<>();
        rutinas = rutinaRepositorio.findByNombreContainingIgnoreCase(removerTildes(nombre));
        List<RutinaDto> rutinas_resp = new ArrayList<>();
        for (Rutina rutina : rutinas){
            rutinas_resp.add(mapearDto(rutina));
        }
        return rutinas_resp;
    }

    private String removerTildes(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }


    private RutinaDto mapearDto(Rutina rutina){
        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setId(rutina.getId());
        rutinaDto.setNombre(rutina.getNombre());
        rutinaDto.setDescripcion(rutina.getDescripcion());
        rutinaDto.setCreador(rutina.getCreador());
        rutinaDto.setPuntuacion(rutina.getPuntuacion());

        List<DiaRutinaDto> diasRutinaDtoList = new ArrayList<>();

        for (DiaRutina diaRutina : rutina.getDias_rutina()) {
            DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
            diaRutinaDto.setId(diaRutina.getId());
            diaRutinaDto.setId_rutina(rutina.getId());
            diaRutinaDto.setDescripcion(diaRutina.getDescripcion());
            diaRutinaDto.setNombre(diaRutina.getNombre());

            List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDtoSet = new ArrayList<>();

            for (EjercicioDiaRutina ejercicioDiaRutina : diaRutina.getEjerciciosDiaRutina()) {
                EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
                ejercicioDiaRutinaDto.setId_EjercicioRutina(ejercicioDiaRutina.getId());
                ejercicioDiaRutinaDto.setSeries(ejercicioDiaRutina.getSeries());
                ejercicioDiaRutinaDto.setRepeticiones(ejercicioDiaRutina.getRepeticiones());

                EjercicioDto ejercicioDto = new EjercicioDto();
                ejercicioDto.setId(ejercicioDiaRutina.getEjercicio().getId());
                ejercicioDto.setNombre(ejercicioDiaRutina.getEjercicio().getNombre());
                ejercicioDto.setDescripcion(ejercicioDiaRutina.getEjercicio().getDescripcion());
                ejercicioDto.setGrupo_muscular(ejercicioDiaRutina.getEjercicio().getGrupo_muscular());
                ejercicioDto.setImagen(ejercicioDiaRutina.getEjercicio().getImagen());
                ejercicioDto.setDificultad(ejercicioDiaRutina.getEjercicio().getDificultad());

                ejercicioDiaRutinaDto.setEjercicioId(ejercicioDto.getId());
                ejerciciosDiaRutinaDtoSet.add(ejercicioDiaRutinaDto);
            }

            diaRutinaDto.setEjerciciosDiaRutina(ejerciciosDiaRutinaDtoSet);
            diasRutinaDtoList.add(diaRutinaDto);
        }

        rutinaDto.setDias_rutina(diasRutinaDtoList);
        return rutinaDto;
    }

    //Convertimos Dto a entidad
    private Rutina mapearEntidad(RutinaDto rutinaDto){
        Rutina rutina=new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setDescripcion(rutinaDto.getDescripcion());
        rutina.setCreador(rutinaDto.getCreador());
        rutina.setPuntuacion(rutinaDto.getPuntuacion());
        List<DiaRutina> diasRutina = new ArrayList<>();
        for (DiaRutinaDto diaRutinaDto : rutinaDto.getDias_rutina()) {
            DiaRutina diaRutina = new DiaRutina();
            diaRutina.setId(diaRutinaDto.getId());
            diaRutina.setNombre(diaRutinaDto.getNombre());
            diaRutina.setDescripcion(diaRutinaDto.getDescripcion());

            Set<EjercicioDiaRutina> ejerciciosDiaRutina = new HashSet<>();
            for (EjercicioDiaRutinaDto ejercicioDiaRutinaDto : diaRutinaDto.getEjerciciosDiaRutina()) {
                EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
                ejercicioDiaRutina.setId(ejercicioDiaRutinaDto.getId_EjercicioRutina());
                ejercicioDiaRutina.setSeries(ejercicioDiaRutinaDto.getSeries());
                ejercicioDiaRutina.setRepeticiones(ejercicioDiaRutinaDto.getRepeticiones());

                Ejercicio ejercicio = new Ejercicio();

                ejercicio.setId(ejercicioDiaRutinaDto.getEjercicioId());
                // Mapear los demás atributos del ejercicio

                ejercicioDiaRutina.setEjercicio(ejercicio);
                ejercicioDiaRutina.setDiaRutina(diaRutina);
                ejerciciosDiaRutina.add(ejercicioDiaRutina);
            }

            diaRutina.setEjerciciosDiaRutina(ejerciciosDiaRutina);
            diaRutina.setRutina(rutina);
            diasRutina.add(diaRutina);
        }

        rutina.setDias_rutina(diasRutina);

        return rutina;
    }
}
