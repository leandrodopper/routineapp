package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.*;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.Usuario;

import java.util.List;

public interface RutinaServicio {

    public RutinaDto crearRutina(RutinaDto rutinaDto);
    public RutinaDto obtenerRutinaPorId(Long id);
    public RutinaRespuesta obtenerRutinas(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir);
    public void eliminarRutina(Long id);

    public RutinaDto actualizarRutina(RutinaDto rutinaDto, Long id);

    public void seguirRutina(Long id_rutina, Long id_usuario);

    public void dejarseguirRutina(Long id_rutina, Long id_usuario);

    public List<RutinaDto> obtenerRutinasSeguidasUsuario(Long id_usuario);

    public List<RutinaDto> obtenerRutinasCreadasUsuario(Long id_usuario);

    public List<RutinaDto> obtenerRutinasPorNombre(String nombre);

    public RutinaDto puntuarRutina(Long id, double puntuacion);

    public DiaRutinaDto crearDiarutina(DiaRutinaDto diaRutinaDto);
    public DiaRutinaDto obtenerDiarutina(Long id);
    public DiaRutinaDto actualizarDiarutina(ActualizarDiaRutinaDto actualizarDiaRutinaDto, Long id);
    public void eliminarDiarutina(Long id);

    public DiaRutinaDto addEjercicioADia(EjercicioDiaRutinaDto ejercicioDiaRutinaDto, Long id);
    public DiaRutinaDto addEjercicioADiaList(List<EjercicioDiaRutinaDto> ejerciciosDiaRutinaDto, Long id);
    public DiaRutinaDto deleteEjercicioDeDia(Long idDiaRutina, Long idEjercicioDiaRutina);
}
