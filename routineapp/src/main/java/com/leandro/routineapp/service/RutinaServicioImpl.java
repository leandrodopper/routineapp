package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutinaServicioImpl implements RutinaServicio{

    @Autowired
    private RutinaRepositorio rutinaRepositorio;

    @Override
    public RutinaDto crearRutina(RutinaDto rutinaDto) {
        Rutina rutina = mapearEntidad(rutinaDto);
        Rutina nuevaRutina = rutinaRepositorio.save(rutina);

        RutinaDto rutinaRespuesta=mapearDto(nuevaRutina);
        return rutinaRespuesta;
    }

    @Override
    public RutinaDto obtenerRutinaPorId(Long id) {
        Rutina rutina = rutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Rutina", "id", id.toString()));
        return mapearDto(rutina);
    }

    @Override
    public void eliminarRutina(Long id) {
        Rutina rutina  = rutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Rutina", "id", id.toString()));
        rutinaRepositorio.delete(rutina);
    }

    private RutinaDto mapearDto(Rutina rutina){
        RutinaDto rutinaDto=new RutinaDto();
        rutinaDto.setId(rutina.getId());
        rutinaDto.setNombre(rutina.getNombre());
        rutinaDto.setDescripcion(rutina.getDescripcion());
        rutinaDto.setDias_rutina(rutina.getDias_rutina());
        return rutinaDto;
    }

    //Convertimos Dto a entidad
    private Rutina mapearEntidad(RutinaDto rutinaDto){
        Rutina rutina=new Rutina();
        rutina.setNombre(rutinaDto.getNombre());
        rutina.setDescripcion(rutinaDto.getDescripcion());
        rutina.setDias_rutina(rutinaDto.getDias_rutina());
        return rutina;
    }
}
