package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.dto.RutinaRespuesta;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.RutinaRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RutinaServicioImpl implements RutinaServicio{

    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

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
        rutinaRepositorio.delete(rutina);
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


    private RutinaDto mapearDto(Rutina rutina){
        RutinaDto rutinaDto=new RutinaDto();
        rutinaDto.setId(rutina.getId());
        rutinaDto.setNombre(rutina.getNombre());
        rutinaDto.setDescripcion(rutina.getDescripcion());
        rutinaDto.setDias_rutina(rutina.getDias_rutina());
        rutinaDto.setCreador(rutina.getCreador());
        rutinaDto.setPuntuacion(rutina.getPuntuacion());
        return rutinaDto;
    }

    //Convertimos Dto a entidad
    private Rutina mapearEntidad(RutinaDto rutinaDto){
        Rutina rutina=new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setDescripcion(rutinaDto.getDescripcion());
        rutina.setDias_rutina(rutinaDto.getDias_rutina());
        rutina.setCreador(rutinaDto.getCreador());
        rutina.setPuntuacion(rutinaDto.getPuntuacion());
        return rutina;
    }
}
