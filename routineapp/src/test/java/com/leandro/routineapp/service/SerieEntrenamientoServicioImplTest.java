package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EntrenamientoServicioImpl.class})
@ExtendWith(SpringExtension.class)
class SerieEntrenamientoServicioImplTest {
    @MockBean
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;

    @Autowired
    private EntrenamientoServicioImpl serieEntrenamientoServicioImpl;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @MockBean
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @MockBean
    private EntrenamientoRepositorio entrenamientoRepositorio;

    @MockBean
    private EntrenamientoEjercicioRepositorio entrenamientoEjercicioRepositorio;

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerDatosEjercicioPorUsuario(Long, Long)}
     */
    @Test
    void testObtenerDatosEjercicioPorUsuario() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(serieEntrenamientoRepositorio.obtenerProgresoPorUsuarioYEjercicio((Long) any(), (Long) any()))
                .thenReturn(objectArrayList);
        List<Object[]> actualObtenerDatosEjercicioPorUsuarioResult = serieEntrenamientoServicioImpl
                .obtenerDatosEjercicioPorUsuario(1L, 1L);
        assertSame(objectArrayList, actualObtenerDatosEjercicioPorUsuarioResult);
        assertTrue(actualObtenerDatosEjercicioPorUsuarioResult.isEmpty());
        verify(serieEntrenamientoRepositorio).obtenerProgresoPorUsuarioYEjercicio((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerPorcentajePorMusculo(Long)}
     */
    @Test
    void testObtenerPorcentajePorMusculo() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(serieEntrenamientoRepositorio.obtenerTotalSeriesPorGrupoMuscular((Long) any())).thenReturn(objectArrayList);
        List<Object[]> actualObtenerPorcentajePorMusculoResult = serieEntrenamientoServicioImpl
                .obtenerPorcentajePorMusculo(1L);
        assertSame(objectArrayList, actualObtenerPorcentajePorMusculoResult);
        assertTrue(actualObtenerPorcentajePorMusculoResult.isEmpty());
        verify(serieEntrenamientoRepositorio).obtenerTotalSeriesPorGrupoMuscular((Long) any());
    }
}

