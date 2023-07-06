package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.dto.RutinaRespuesta;
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
}
