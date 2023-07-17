package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;





public interface EjercicioServicio {

    public EjercicioDto crearEjercicio(EjercicioDto ejercicioDto);
    public EjercicioRespuesta obtenerTodosEjercicios(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir);
    public EjercicioDto obtenerEjercicioPorId(Long id);

    public EjercicioDto actualizarEjercicio(EjercicioDto ejercicioDto, Long id);
    public void eliminarEjercicio(Long id);
    public EjercicioRespuesta filtrarGrupoMuscular(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir,String grupo_muscular);
    public EjercicioRespuesta filtrarPorNombre(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir,String nombre);
    public EjercicioRespuesta filtrarPorCreador(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir,String creador);
    public EjercicioRespuesta filtrarPorNombreYGrupoMuscular(int numeroPagina, int tamanoPagina, String ordenarPor, String sortDir,String filtro);
}
