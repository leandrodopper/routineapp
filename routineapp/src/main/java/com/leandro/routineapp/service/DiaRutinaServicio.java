package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.DiaRutinaDto;

public interface DiaRutinaServicio {

    public DiaRutinaDto crearDiarutina(DiaRutinaDto diaRutinaDto);
    public DiaRutinaDto obtenerDiarutina(Long id);
    public DiaRutinaDto actualizarDiarutina(DiaRutinaDto diaRutinaDto, Long id);
    public void eliminarDiarutina(Long id);
}
