package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.DiaRutinaRepositorio;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class DiaRutinaServicioImpl implements DiaRutinaServicio{

    @Autowired
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @Autowired
    private RutinaRepositorio rutinaRepositorio;

    @Autowired
    private EjercicioRepositorio ejercicioRepositorio;

    @Override
    public DiaRutinaDto crearDiarutina(DiaRutinaDto diaRutinaDto) {
        List<Long> lista_ejercicios = diaRutinaDto.getEjerciciosIds();
        List<Ejercicio> lista_ejercicios_bd = ejercicioRepositorio.findAll();
        List<Long> lista_ejercicios_bd_id = new ArrayList<>();
        for(int i=0; i<lista_ejercicios_bd.size();i++){
            lista_ejercicios_bd_id.add(lista_ejercicios_bd.get(i).getId());
        }

        if(!lista_ejercicios_bd_id.containsAll(lista_ejercicios)){
            lista_ejercicios.removeAll(lista_ejercicios_bd_id);
            throw new ResourceNotFoundException("Ejercicio", "id: ", lista_ejercicios.toString());
        }

        DiaRutina diaRutina = mapearEntidad(diaRutinaDto);
        DiaRutina nuevodiaRutina = diaRutinaRepositorio.save(diaRutina);


        DiaRutinaDto diaRutinaRespuesta=mapearDto(nuevodiaRutina);
        return diaRutinaRespuesta;
    }

    @Override
    public DiaRutinaDto obtenerDiarutina(Long id) {
        DiaRutina diaRutina= diaRutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", id.toString()));
        return mapearDto(diaRutina);
    }

    @Override
    public DiaRutinaDto actualizarDiarutina(DiaRutinaDto diaRutinaDto, Long id) {
        return null;
    }

    @Override
    public void eliminarDiarutina(Long id) {

    }

    //Convertimos a Dto
    private DiaRutinaDto mapearDto(DiaRutina diaRutina){
        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        List<Long> ejercicios_ids = new ArrayList<>();
        diaRutinaDto.setId(diaRutina.getId());
        diaRutinaDto.setId_rutina(diaRutina.getRutina().getId());
        diaRutinaDto.setDescripcion(diaRutina.getDescripcion());
        diaRutinaDto.setNombre(diaRutina.getNombre());
        for(int i=0; i<diaRutina.getEjercicios().size();i++){
            ejercicios_ids.add(diaRutina.getEjercicios().stream().toList().get(i).getId());
        }
        diaRutinaDto.setEjerciciosIds(ejercicios_ids);
        return diaRutinaDto;
    }

    //Convertimos Dto a entidad
    private DiaRutina mapearEntidad(DiaRutinaDto diaRutinaDto){
        DiaRutina diaRutina= new DiaRutina();
        List<Ejercicio> ejercicios=new ArrayList<>();
        diaRutina.setRutina(rutinaRepositorio.getReferenceById(diaRutinaDto.getId_rutina()));
        diaRutina.setDescripcion(diaRutinaDto.getDescripcion());
        diaRutina.setNombre(diaRutinaDto.getNombre());
        ejercicios=ejercicioRepositorio.findAllById(diaRutinaDto.getEjerciciosIds());
        Set<Ejercicio> ejercicioSet = new HashSet<>(ejercicios);
        diaRutina.setEjercicios(ejercicioSet);
        return diaRutina;
    }
}
