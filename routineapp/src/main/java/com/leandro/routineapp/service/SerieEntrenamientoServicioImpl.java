package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.SerieEntrenamientoDto;
import com.leandro.routineapp.entity.SerieEntrenamiento;
import com.leandro.routineapp.repository.SerieEntrenamientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieEntrenamientoServicioImpl implements SerieEntrenamientoServicio {

    @Autowired
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;


    @Override
    public List<Object[]> obtenerDatosEjercicioPorUsuario(Long usuario_id, Long ejercicio_id) {
        return serieEntrenamientoRepositorio.obtenerProgresoPorUsuarioYEjercicio(usuario_id,ejercicio_id);
    }

    @Override
    public List<Object[]> obtenerPorcentajePorMusculo(Long usuario_id) {
        return serieEntrenamientoRepositorio.obtenerTotalSeriesPorGrupoMuscular(usuario_id);
    }
}
