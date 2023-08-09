package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.SerieEntrenamientoDto;
import com.leandro.routineapp.entity.SerieEntrenamiento;
import com.leandro.routineapp.repository.SerieEntrenamientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieEntrenamientoServicioImpl implements SerieEntrenamientoServicio {

    @Autowired
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;


    @Override
    public SerieEntrenamientoDto guardarSerieEntrenamiento(SerieEntrenamientoDto serieEntrenamientoDto) {
        SerieEntrenamiento serieEntrenamiento=new SerieEntrenamiento();
        /*serieEntrenamiento.setEntrenamientoEjercicio();
        serieEntrenamiento.setNumeroSerie();
        serieEntrenamiento.setRepeticionesRealizadas();
        serieEntrenamiento.setPesoUtilizado();
        serieEntrenamiento.setObjetivoCumplido();*/
        return null;
    }
}
