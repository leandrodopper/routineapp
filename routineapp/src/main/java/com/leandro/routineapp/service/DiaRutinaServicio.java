package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.ActualizarDiaRutinaDto;
import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.entity.EjercicioDiaRutina;

import java.util.List;

public interface DiaRutinaServicio {

    public DiaRutinaDto crearDiarutina(DiaRutinaDto diaRutinaDto);
    public DiaRutinaDto obtenerDiarutina(Long id);
    public DiaRutinaDto actualizarDiarutina(ActualizarDiaRutinaDto actualizarDiaRutinaDto, Long id);
    public void eliminarDiarutina(Long id);

    public DiaRutinaDto addEjercicioADia(EjercicioDiaRutinaDto ejercicioDiaRutinaDto, Long id);
    public DiaRutinaDto addEjercicioADiaList(List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDto, Long id);
    public DiaRutinaDto deleteEjercicioDeDia(Long idDiaRutina, Long idEjercicioDiaRutina);
}
