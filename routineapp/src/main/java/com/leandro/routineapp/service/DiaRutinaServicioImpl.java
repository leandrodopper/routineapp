package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.ActualizarDiaRutinaDto;
import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.EjercicioDiaRutina;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.DiaRutinaRepositorio;
import com.leandro.routineapp.repository.EjercicioDiaRutinaRepositorio;
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

    @Autowired
    EjercicioDiaRutinaRepositorio ejercicioDiaRutinaRepositorio;

    @Override
    public DiaRutinaDto crearDiarutina(DiaRutinaDto diaRutinaDto) {
        DiaRutina diaRutina = mapearEntidad(diaRutinaDto);
        DiaRutina diaRutinaAdded = diaRutinaRepositorio.save(diaRutina);
        DiaRutinaDto diaRutinaRespuesta = mapearDto(diaRutinaAdded);
        return diaRutinaRespuesta;
    }



    @Override
    public DiaRutinaDto obtenerDiarutina(Long id) {
        DiaRutina diaRutina= diaRutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", id.toString()));
        return mapearDto(diaRutina);
    }

    @Override
    public DiaRutinaDto actualizarDiarutina(ActualizarDiaRutinaDto actualizarDiaRutinaDto, Long id_dia_rutina) {
        DiaRutina diaRutina = diaRutinaRepositorio.getById(id_dia_rutina);
        diaRutina.setNombre(actualizarDiaRutinaDto.getNombre());
        diaRutina.setDescripcion(actualizarDiaRutinaDto.getDescripcion());

        List<EjercicioDiaRutinaDto> ejerciciosDto = actualizarDiaRutinaDto.getEjerciciosDiaRutina();
        Set<EjercicioDiaRutina> ejerciciosBd = diaRutina.getEjerciciosDiaRutina();

        for (EjercicioDiaRutinaDto ejercicioDto : ejerciciosDto) {
            Long idEjercicioRutina = ejercicioDto.getId_EjercicioRutina();
            int series = ejercicioDto.getSeries();
            int repeticiones = ejercicioDto.getRepeticiones();

            Optional<EjercicioDiaRutina> ejercicioOptional = ejercicioDiaRutinaRepositorio.findById(idEjercicioRutina);

            if (ejercicioOptional.isPresent()) {
                EjercicioDiaRutina ejercicio = ejercicioOptional.get();
                ejercicio.setSeries(series);
                ejercicio.setRepeticiones(repeticiones);

                // Guardar el EjercicioDiaRutina actualizado en la base de datos
                ejercicioDiaRutinaRepositorio.save(ejercicio);
            }
        }

        // Guardar el DiaRutina actualizado en la base de datos
        diaRutinaRepositorio.save(diaRutina);

        DiaRutinaDto respuesta = new DiaRutinaDto();
        respuesta=mapearDto(diaRutina);
        return respuesta;
    }


    @Override
    public void eliminarDiarutina(Long id) {
        DiaRutina diaRutina= diaRutinaRepositorio
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", id.toString()));

        diaRutinaRepositorio.delete(diaRutina);
    }

    @Override
    public DiaRutinaDto addEjercicioADia(EjercicioDiaRutinaDto ejercicioDiaRutinaDto, Long id) {
        DiaRutina diaRutina = diaRutinaRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", id.toString()));
        EjercicioDiaRutina ejercicioDiaRutina= toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto);
        diaRutina.addEjercicioDiaRutina(ejercicioDiaRutina);
        DiaRutina diaRutinaActualizado = diaRutinaRepositorio.save(diaRutina);
        return mapearDto(diaRutinaActualizado);
    }

    @Override
    public DiaRutinaDto addEjercicioADiaList(List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDto, Long id) {
        DiaRutina diaRutina = diaRutinaRepositorio.findById(id).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", id.toString()));
        List<EjercicioDiaRutina> aux = new ArrayList<>();
        for (EjercicioDiaRutinaDto ejercicioDiaRutinaDto : ejerciciosDiaRutinaDto){
            aux.add(toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto));
        }
        for (EjercicioDiaRutina addEjercicio: aux){
            diaRutina.addEjercicioDiaRutina(addEjercicio);
        }

        DiaRutina diaRutinaActualizado = diaRutinaRepositorio.save(diaRutina);
        return mapearDto(diaRutinaActualizado);
    }

    @Override
    public DiaRutinaDto deleteEjercicioDeDia(Long idDiaRutina, Long idEjercicioDiaRutina) {
        EjercicioDiaRutina ejercicioDiaRutina = ejercicioDiaRutinaRepositorio.getById(idEjercicioDiaRutina);
        DiaRutina diaRutina = diaRutinaRepositorio.findById(idDiaRutina).orElseThrow(()-> new ResourceNotFoundException("DiaRutina", "id", idDiaRutina.toString()));
        ejercicioDiaRutinaRepositorio.deleteById(idEjercicioDiaRutina);
        diaRutina.getEjerciciosDiaRutina().remove(ejercicioDiaRutina);
        DiaRutina diaRutinaRespuesta= diaRutinaRepositorio.save(diaRutina);
        return mapearDto(diaRutinaRespuesta);
    }

    //Convertimos a Dto
    public DiaRutinaDto mapearDto(DiaRutina diaRutina){
        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setId_rutina(diaRutina.getRutina().getId());
        diaRutinaDto.setId(diaRutina.getId());
        diaRutinaDto.setNombre(diaRutina.getNombre());
        diaRutinaDto.setDescripcion(diaRutina.getDescripcion());
        if (diaRutina.getEjerciciosDiaRutina() != null) {
            List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDto = new ArrayList<>();
            for (EjercicioDiaRutina ejercicioDiaRutina : diaRutina.getEjerciciosDiaRutina()) {
                EjercicioDiaRutinaDto ejercicioDiaRutinaDto = toEjercicioDiaRutinaDto(ejercicioDiaRutina);
                ejerciciosDiaRutinaDto.add(ejercicioDiaRutinaDto);
            }
            diaRutinaDto.setEjerciciosDiaRutina(ejerciciosDiaRutinaDto);
        }
        return diaRutinaDto;
    }

    //Convertimos Dto a entidad
    public DiaRutina mapearEntidad(DiaRutinaDto diaRutinaDto){
        DiaRutina diaRutina= new DiaRutina();
        diaRutina.setId(diaRutinaDto.getId());
        diaRutina.setNombre(diaRutinaDto.getNombre());
        diaRutina.setDescripcion(diaRutinaDto.getDescripcion());
        if (diaRutinaDto.getEjerciciosDiaRutina() != null) {
            Set<EjercicioDiaRutina> ejerciciosDiaRutina = new HashSet<>();
            for (EjercicioDiaRutinaDto ejercicioDto : diaRutinaDto.getEjerciciosDiaRutina()) {
                EjercicioDiaRutina ejercicioDiaRutina = toEntityEjercicioDiaRutina(ejercicioDto);
                ejercicioDiaRutina.setDiaRutina(diaRutina);
                ejerciciosDiaRutina.add(ejercicioDiaRutina);
            }
            diaRutina.setEjerciciosDiaRutina(ejerciciosDiaRutina);
        }
        diaRutina.setRutina(rutinaRepositorio.getById(diaRutinaDto.getId_rutina()));
        return diaRutina;
    }

    public EjercicioDiaRutina toEntityEjercicioDiaRutina (EjercicioDiaRutinaDto ejercicioDiaRutinaDto){
        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setId(ejercicioDiaRutinaDto.getId_EjercicioRutina());
        ejercicioDiaRutina.setRepeticiones(ejercicioDiaRutinaDto.getRepeticiones());
        ejercicioDiaRutina.setSeries(ejercicioDiaRutinaDto.getSeries());
        if(ejercicioDiaRutinaDto.getEjercicioId()!=null){
            Ejercicio ejercicio = ejercicioRepositorio.findById(ejercicioDiaRutinaDto.getEjercicioId())
                    .orElseThrow(() -> new IllegalArgumentException("Ejercicio no encontrado con ID: " + ejercicioDiaRutinaDto.getEjercicioId()));
            ejercicioDiaRutina.setEjercicio(ejercicio);
        }
        return ejercicioDiaRutina;
    }

    public EjercicioDiaRutinaDto toEjercicioDiaRutinaDto(EjercicioDiaRutina ejercicioDiaRutina){
        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setId_EjercicioRutina(ejercicioDiaRutina.getId());
        ejercicioDiaRutinaDto.setEjercicioId(ejercicioDiaRutina.getEjercicio().getId());
        ejercicioDiaRutinaDto.setSeries(ejercicioDiaRutina.getSeries());
        ejercicioDiaRutinaDto.setRepeticiones(ejercicioDiaRutina.getRepeticiones());
        return ejercicioDiaRutinaDto;
    }
}
