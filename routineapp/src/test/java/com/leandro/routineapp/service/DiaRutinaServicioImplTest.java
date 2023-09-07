package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.ActualizarDiaRutinaDto;
import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.EjercicioDiaRutina;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.DiaRutinaRepositorio;
import com.leandro.routineapp.repository.EjercicioDiaRutinaRepositorio;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DiaRutinaServicioImpl.class})
@ExtendWith(SpringExtension.class)
class DiaRutinaServicioImplTest {
    @MockBean
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @Autowired
    private DiaRutinaServicioImpl diaRutinaServicioImpl;

    @MockBean
    private EjercicioDiaRutinaRepositorio ejercicioDiaRutinaRepositorio;

    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @MockBean
    private RutinaRepositorio rutinaRepositorio;

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    void testCrearDiarutina() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina1);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualCrearDiarutinaResult = diaRutinaServicioImpl.crearDiarutina(diaRutinaDto);
        assertEquals("Descripcion", actualCrearDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualCrearDiarutinaResult.getNombre());
        assertEquals(123L, actualCrearDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualCrearDiarutinaResult.getId().longValue());
        assertEquals(ejercicioDiaRutinaDtoList, actualCrearDiarutinaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    void testCrearDiarutina2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(rutinaRepositorio.getById((Long) any())).thenThrow(new IllegalArgumentException());

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.crearDiarutina(diaRutinaDto));
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    void testCrearDiarutina3() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina2);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualCrearDiarutinaResult = diaRutinaServicioImpl.crearDiarutina(diaRutinaDto);
        assertEquals("Descripcion", actualCrearDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualCrearDiarutinaResult.getNombre());
        assertEquals(123L, actualCrearDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualCrearDiarutinaResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualCrearDiarutinaResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    void testCrearDiarutina4() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualCrearDiarutinaResult = diaRutinaServicioImpl.crearDiarutina(diaRutinaDto);
        assertEquals("Descripcion", actualCrearDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualCrearDiarutinaResult.getNombre());
        assertEquals(123L, actualCrearDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualCrearDiarutinaResult.getId().longValue());
        assertEquals(comentarioList, actualCrearDiarutinaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(ejercicioRepositorio).findById((Long) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCrearDiarutina5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.EjercicioRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.toEntityEjercicioDiaRutina(DiaRutinaServicioImpl.java:173)
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.mapearEntidad(DiaRutinaServicioImpl.java:156)
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.crearDiarutina(DiaRutinaServicioImpl.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(null);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        diaRutinaServicioImpl.crearDiarutina(diaRutinaDto);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#crearDiarutina(DiaRutinaDto)}
     */
    @Test
    void testCrearDiarutina6() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.crearDiarutina(diaRutinaDto));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#obtenerDiarutina(Long)}
     */
    @Test
    void testObtenerDiarutina() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DiaRutinaDto actualObtenerDiarutinaResult = diaRutinaServicioImpl.obtenerDiarutina(123L);
        assertEquals("Descripcion", actualObtenerDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualObtenerDiarutinaResult.getNombre());
        assertEquals(123L, actualObtenerDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualObtenerDiarutinaResult.getId().longValue());
        assertEquals(comentarioList, actualObtenerDiarutinaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#obtenerDiarutina(Long)}
     */
    @Test
    void testObtenerDiarutina2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DiaRutinaDto actualObtenerDiarutinaResult = diaRutinaServicioImpl.obtenerDiarutina(123L);
        assertEquals("Descripcion", actualObtenerDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualObtenerDiarutinaResult.getNombre());
        assertEquals(123L, actualObtenerDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualObtenerDiarutinaResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualObtenerDiarutinaResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#obtenerDiarutina(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerDiarutina3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.DiaRutinaRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.obtenerDiarutina(DiaRutinaServicioImpl.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(null);
        diaRutinaServicioImpl.obtenerDiarutina(123L);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#obtenerDiarutina(Long)}
     */
    @Test
    void testObtenerDiarutina4() {
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> diaRutinaServicioImpl.obtenerDiarutina(123L));
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#obtenerDiarutina(Long)}
     */
    @Test
    void testObtenerDiarutina5() {
        when(diaRutinaRepositorio.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.obtenerDiarutina(123L));
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#actualizarDiarutina(ActualizarDiaRutinaDto, Long)}
     */
    @Test
    void testActualizarDiarutina() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        ActualizarDiaRutinaDto actualizarDiaRutinaDto = new ActualizarDiaRutinaDto();
        actualizarDiaRutinaDto.setDescripcion("Descripcion");
        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        actualizarDiaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        actualizarDiaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualActualizarDiarutinaResult = diaRutinaServicioImpl.actualizarDiarutina(actualizarDiaRutinaDto,
                1L);
        assertEquals("Descripcion", actualActualizarDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualActualizarDiarutinaResult.getNombre());
        assertEquals(123L, actualActualizarDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualActualizarDiarutinaResult.getId().longValue());
        assertEquals(ejercicioDiaRutinaDtoList, actualActualizarDiarutinaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#actualizarDiarutina(ActualizarDiaRutinaDto, Long)}
     */
    @Test
    void testActualizarDiarutina2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenThrow(new IllegalArgumentException());
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        ActualizarDiaRutinaDto actualizarDiaRutinaDto = new ActualizarDiaRutinaDto();
        actualizarDiaRutinaDto.setDescripcion("Descripcion");
        actualizarDiaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        actualizarDiaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.actualizarDiarutina(actualizarDiaRutinaDto, 1L));
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#actualizarDiarutina(ActualizarDiaRutinaDto, Long)}
     */
    @Test
    void testActualizarDiarutina3() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina2);
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina1);

        ActualizarDiaRutinaDto actualizarDiaRutinaDto = new ActualizarDiaRutinaDto();
        actualizarDiaRutinaDto.setDescripcion("Descripcion");
        actualizarDiaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        actualizarDiaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualActualizarDiarutinaResult = diaRutinaServicioImpl.actualizarDiarutina(actualizarDiaRutinaDto,
                1L);
        assertEquals("Descripcion", actualActualizarDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualActualizarDiarutinaResult.getNombre());
        assertEquals(123L, actualActualizarDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualActualizarDiarutinaResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualActualizarDiarutinaResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#actualizarDiarutina(ActualizarDiaRutinaDto, Long)}
     */
    @Test
    void testActualizarDiarutina4() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina2);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        Optional<EjercicioDiaRutina> ofResult = Optional.of(ejercicioDiaRutina);

        Rutina rutina3 = new Rutina();
        rutina3.setComentarios(new ArrayList<>());
        rutina3.setCreador("Creador");
        rutina3.setDescripcion("Descripcion");
        rutina3.setDias_rutina(new ArrayList<>());
        rutina3.setId(123L);
        rutina3.setNombre("Nombre");
        rutina3.setNumPuntuaciones(1L);
        rutina3.setPuntuacion(10.0d);
        rutina3.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina3 = new DiaRutina();
        diaRutina3.setDescripcion("Descripcion");
        diaRutina3.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina3.setId(123L);
        diaRutina3.setNombre("Nombre");
        diaRutina3.setRutina(rutina3);

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina1 = new EjercicioDiaRutina();
        ejercicioDiaRutina1.setDiaRutina(diaRutina3);
        ejercicioDiaRutina1.setEjercicio(ejercicio1);
        ejercicioDiaRutina1.setId(123L);
        ejercicioDiaRutina1.setRepeticiones(1);
        ejercicioDiaRutina1.setSeries(1);
        when(ejercicioDiaRutinaRepositorio.save((EjercicioDiaRutina) any())).thenReturn(ejercicioDiaRutina1);
        when(ejercicioDiaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        ActualizarDiaRutinaDto actualizarDiaRutinaDto = new ActualizarDiaRutinaDto();
        actualizarDiaRutinaDto.setDescripcion("Descripcion");
        actualizarDiaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        actualizarDiaRutinaDto.setNombre("Nombre");
        DiaRutinaDto actualActualizarDiarutinaResult = diaRutinaServicioImpl.actualizarDiarutina(actualizarDiaRutinaDto,
                1L);
        assertEquals("Descripcion", actualActualizarDiarutinaResult.getDescripcion());
        assertEquals("Nombre", actualActualizarDiarutinaResult.getNombre());
        assertEquals(123L, actualActualizarDiarutinaResult.getId_rutina().longValue());
        assertEquals(123L, actualActualizarDiarutinaResult.getId().longValue());
        assertEquals(comentarioList, actualActualizarDiarutinaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(ejercicioDiaRutinaRepositorio).save((EjercicioDiaRutina) any());
        verify(ejercicioDiaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#actualizarDiarutina(ActualizarDiaRutinaDto, Long)}
     */
    @Test
    void testActualizarDiarutina5() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina2);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        Optional<EjercicioDiaRutina> ofResult = Optional.of(ejercicioDiaRutina);
        when(ejercicioDiaRutinaRepositorio.save((EjercicioDiaRutina) any())).thenThrow(new IllegalArgumentException());
        when(ejercicioDiaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        ActualizarDiaRutinaDto actualizarDiaRutinaDto = new ActualizarDiaRutinaDto();
        actualizarDiaRutinaDto.setDescripcion("Descripcion");
        actualizarDiaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        actualizarDiaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.actualizarDiarutina(actualizarDiaRutinaDto, 1L));
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).save((EjercicioDiaRutina) any());
        verify(ejercicioDiaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#eliminarDiarutina(Long)}
     */
    @Test
    void testEliminarDiarutina() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);
        doNothing().when(diaRutinaRepositorio).delete((DiaRutina) any());
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        diaRutinaServicioImpl.eliminarDiarutina(123L);
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(diaRutinaRepositorio).delete((DiaRutina) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#eliminarDiarutina(Long)}
     */
    @Test
    void testEliminarDiarutina2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);
        doThrow(new IllegalArgumentException()).when(diaRutinaRepositorio).delete((DiaRutina) any());
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.eliminarDiarutina(123L));
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(diaRutinaRepositorio).delete((DiaRutina) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#eliminarDiarutina(Long)}
     */
    @Test
    void testEliminarDiarutina3() {
        doNothing().when(diaRutinaRepositorio).delete((DiaRutina) any());
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> diaRutinaServicioImpl.eliminarDiarutina(123L));
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult1 = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        DiaRutinaDto actualAddEjercicioADiaResult = diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L);
        assertEquals("Descripcion", actualAddEjercicioADiaResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaResult.getId().longValue());
        assertEquals(comentarioList, actualAddEjercicioADiaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenThrow(new IllegalArgumentException());
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult1 = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L));
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia3() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina1);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina2);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult1 = Optional.of(ejercicio1);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        DiaRutinaDto actualAddEjercicioADiaResult = diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L);
        assertEquals("Descripcion", actualAddEjercicioADiaResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualAddEjercicioADiaResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddEjercicioADia4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.DiaRutinaRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.addEjercicioADia(DiaRutinaServicioImpl.java:97)
        //   See https://diff.blue/R013 to resolve this issue.

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(null);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia5() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        assertThrows(ResourceNotFoundException.class,
                () -> diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L));
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddEjercicioADia6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.EjercicioRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.toEntityEjercicioDiaRutina(DiaRutinaServicioImpl.java:173)
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.addEjercicioADia(DiaRutinaServicioImpl.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(null);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia7() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L));
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADia(EjercicioDiaRutinaDto, Long)}
     */
    @Test
    void testAddEjercicioADia8() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult1 = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(null);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        DiaRutinaDto actualAddEjercicioADiaResult = diaRutinaServicioImpl.addEjercicioADia(ejercicioDiaRutinaDto, 123L);
        assertEquals("Descripcion", actualAddEjercicioADiaResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaResult.getId().longValue());
        assertEquals(comentarioList, actualAddEjercicioADiaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    void testAddEjercicioADiaList() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        DiaRutinaDto actualAddEjercicioADiaListResult = diaRutinaServicioImpl
                .addEjercicioADiaList(ejercicioDiaRutinaDtoList, 123L);
        assertEquals("Descripcion", actualAddEjercicioADiaListResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaListResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId().longValue());
        assertEquals(ejercicioDiaRutinaDtoList, actualAddEjercicioADiaListResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    void testAddEjercicioADiaList2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenThrow(new IllegalArgumentException());
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.addEjercicioADiaList(new ArrayList<>(), 123L));
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    void testAddEjercicioADiaList3() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina1);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina2);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DiaRutinaDto actualAddEjercicioADiaListResult = diaRutinaServicioImpl.addEjercicioADiaList(new ArrayList<>(),
                123L);
        assertEquals("Descripcion", actualAddEjercicioADiaListResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaListResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualAddEjercicioADiaListResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddEjercicioADiaList4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.DiaRutinaRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.addEjercicioADiaList(DiaRutinaServicioImpl.java:106)
        //   See https://diff.blue/R013 to resolve this issue.

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(null);
        diaRutinaServicioImpl.addEjercicioADiaList(new ArrayList<>(), 123L);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    void testAddEjercicioADiaList5() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> diaRutinaServicioImpl.addEjercicioADiaList(new ArrayList<>(), 123L));
        verify(diaRutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#addEjercicioADiaList(List, Long)}
     */
    @Test
    void testAddEjercicioADiaList6() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult1 = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult1);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);
        DiaRutinaDto actualAddEjercicioADiaListResult = diaRutinaServicioImpl
                .addEjercicioADiaList(ejercicioDiaRutinaDtoList, 123L);
        assertEquals("Descripcion", actualAddEjercicioADiaListResult.getDescripcion());
        assertEquals("Nombre", actualAddEjercicioADiaListResult.getNombre());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId_rutina().longValue());
        assertEquals(123L, actualAddEjercicioADiaListResult.getId().longValue());
        assertEquals(comentarioList, actualAddEjercicioADiaListResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#deleteEjercicioDeDia(Long, Long)}
     */
    @Test
    void testDeleteEjercicioDeDia() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina2);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        when(ejercicioDiaRutinaRepositorio.getById((Long) any())).thenReturn(ejercicioDiaRutina);
        doNothing().when(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
        DiaRutinaDto actualDeleteEjercicioDeDiaResult = diaRutinaServicioImpl.deleteEjercicioDeDia(1L, 1L);
        assertEquals("Descripcion", actualDeleteEjercicioDeDiaResult.getDescripcion());
        assertEquals("Nombre", actualDeleteEjercicioDeDiaResult.getNombre());
        assertEquals(123L, actualDeleteEjercicioDeDiaResult.getId_rutina().longValue());
        assertEquals(123L, actualDeleteEjercicioDeDiaResult.getId().longValue());
        assertEquals(comentarioList, actualDeleteEjercicioDeDiaResult.getEjerciciosDiaRutina());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).getById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#deleteEjercicioDeDia(Long, Long)}
     */
    @Test
    void testDeleteEjercicioDeDia2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina1);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(ejercicioDiaRutinaRepositorio.getById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        doThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"))
                .when(ejercicioDiaRutinaRepositorio)
                .deleteById((Long) any());
        assertThrows(ResourceNotFoundException.class, () -> diaRutinaServicioImpl.deleteEjercicioDeDia(1L, 1L));
        verify(ejercicioDiaRutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#deleteEjercicioDeDia(Long, Long)}
     */
    @Test
    void testDeleteEjercicioDeDia3() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        Optional<DiaRutina> ofResult = Optional.of(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina1);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina2 = new DiaRutina();
        diaRutina2.setDescripcion("Descripcion");
        diaRutina2.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina2.setId(123L);
        diaRutina2.setNombre("Nombre");
        diaRutina2.setRutina(rutina2);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina2);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Rutina rutina3 = new Rutina();
        rutina3.setComentarios(new ArrayList<>());
        rutina3.setCreador("Creador");
        rutina3.setDescripcion("Descripcion");
        rutina3.setDias_rutina(new ArrayList<>());
        rutina3.setId(123L);
        rutina3.setNombre("Nombre");
        rutina3.setNumPuntuaciones(1L);
        rutina3.setPuntuacion(10.0d);
        rutina3.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina3 = new DiaRutina();
        diaRutina3.setDescripcion("Descripcion");
        diaRutina3.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina3.setId(123L);
        diaRutina3.setNombre("Nombre");
        diaRutina3.setRutina(rutina3);

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina1 = new EjercicioDiaRutina();
        ejercicioDiaRutina1.setDiaRutina(diaRutina3);
        ejercicioDiaRutina1.setEjercicio(ejercicio1);
        ejercicioDiaRutina1.setId(123L);
        ejercicioDiaRutina1.setRepeticiones(1);
        ejercicioDiaRutina1.setSeries(1);
        when(ejercicioDiaRutinaRepositorio.getById((Long) any())).thenReturn(ejercicioDiaRutina1);
        doNothing().when(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
        DiaRutinaDto actualDeleteEjercicioDeDiaResult = diaRutinaServicioImpl.deleteEjercicioDeDia(1L, 1L);
        assertEquals("Descripcion", actualDeleteEjercicioDeDiaResult.getDescripcion());
        assertEquals("Nombre", actualDeleteEjercicioDeDiaResult.getNombre());
        assertEquals(123L, actualDeleteEjercicioDeDiaResult.getId_rutina().longValue());
        assertEquals(123L, actualDeleteEjercicioDeDiaResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualDeleteEjercicioDeDiaResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
        verify(diaRutinaRepositorio).save((DiaRutina) any());
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).getById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#deleteEjercicioDeDia(Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteEjercicioDeDia4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.DiaRutinaRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.deleteEjercicioDeDia(DiaRutinaServicioImpl.java:122)
        //   See https://diff.blue/R013 to resolve this issue.

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(null);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina1);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        when(ejercicioDiaRutinaRepositorio.getById((Long) any())).thenReturn(ejercicioDiaRutina);
        doNothing().when(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
        diaRutinaServicioImpl.deleteEjercicioDeDia(1L, 1L);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#deleteEjercicioDeDia(Long, Long)}
     */
    @Test
    void testDeleteEjercicioDeDia5() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        when(diaRutinaRepositorio.save((DiaRutina) any())).thenReturn(diaRutina);
        when(diaRutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina1);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        when(ejercicioDiaRutinaRepositorio.getById((Long) any())).thenReturn(ejercicioDiaRutina);
        doNothing().when(ejercicioDiaRutinaRepositorio).deleteById((Long) any());
        assertThrows(ResourceNotFoundException.class, () -> diaRutinaServicioImpl.deleteEjercicioDeDia(1L, 1L));
        verify(diaRutinaRepositorio).findById((Long) any());
        verify(ejercicioDiaRutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearDto(DiaRutina)}
     */
    @Test
    void testMapearDto() {
        Rutina rutina = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina.setComentarios(comentarioList);
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);
        DiaRutinaDto actualMapearDtoResult = diaRutinaServicioImpl.mapearDto(diaRutina);
        assertEquals("Descripcion", actualMapearDtoResult.getDescripcion());
        assertEquals("Nombre", actualMapearDtoResult.getNombre());
        assertEquals(123L, actualMapearDtoResult.getId_rutina().longValue());
        assertEquals(123L, actualMapearDtoResult.getId().longValue());
        assertEquals(comentarioList, actualMapearDtoResult.getEjerciciosDiaRutina());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearDto(DiaRutina)}
     */
    @Test
    void testMapearDto2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);

        HashSet<EjercicioDiaRutina> ejercicioDiaRutinaSet = new HashSet<>();
        ejercicioDiaRutinaSet.add(ejercicioDiaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("Descripcion");
        diaRutina1.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina1.setId(123L);
        diaRutina1.setNombre("Nombre");
        diaRutina1.setRutina(rutina1);
        DiaRutinaDto actualMapearDtoResult = diaRutinaServicioImpl.mapearDto(diaRutina1);
        assertEquals("Descripcion", actualMapearDtoResult.getDescripcion());
        assertEquals("Nombre", actualMapearDtoResult.getNombre());
        assertEquals(123L, actualMapearDtoResult.getId_rutina().longValue());
        assertEquals(123L, actualMapearDtoResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = actualMapearDtoResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult.getEjercicioId().longValue());
        assertEquals(1, getResult.getSeries());
        assertEquals(1, getResult.getRepeticiones());
        assertEquals(123L, getResult.getId_EjercicioRutina().longValue());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearDto(DiaRutina)}
     */
    @Test
    void testMapearDto3() {
        Comentario comentario = new Comentario();
        comentario.setComentarioPadre(new Comentario());
        comentario.setContenido("Contenido");
        comentario.setFecha(mock(Timestamp.class));
        comentario.setId(123L);
        comentario.setRespuestas(new ArrayList<>());
        comentario.setRutina(new Rutina());
        comentario.setUsuario("Usuario");

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        Comentario comentario1 = new Comentario();
        comentario1.setComentarioPadre(comentario);
        comentario1.setContenido("Contenido");
        comentario1.setFecha(mock(Timestamp.class));
        comentario1.setId(123L);
        comentario1.setRespuestas(new ArrayList<>());
        comentario1.setRutina(rutina);
        comentario1.setUsuario("Usuario");

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        Comentario comentario2 = new Comentario();
        comentario2.setComentarioPadre(comentario1);
        comentario2.setContenido("Contenido");
        comentario2.setFecha(mock(Timestamp.class));
        comentario2.setId(123L);
        comentario2.setRespuestas(new ArrayList<>());
        comentario2.setRutina(rutina1);
        comentario2.setUsuario("Usuario");

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        Comentario comentario3 = new Comentario();
        comentario3.setComentarioPadre(comentario2);
        comentario3.setContenido("Contenido");
        comentario3.setFecha(mock(Timestamp.class));
        comentario3.setId(123L);
        comentario3.setRespuestas(new ArrayList<>());
        comentario3.setRutina(rutina2);
        comentario3.setUsuario("Usuario");

        ArrayList<Comentario> comentarioList = new ArrayList<>();
        comentarioList.add(comentario3);

        Rutina rutina3 = new Rutina();
        rutina3.setComentarios(comentarioList);
        rutina3.setCreador("Creador");
        rutina3.setDescripcion("Descripcion");
        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        rutina3.setDias_rutina(diaRutinaList);
        rutina3.setId(123L);
        rutina3.setNombre("Nombre");
        rutina3.setNumPuntuaciones(1L);
        rutina3.setPuntuacion(10.0d);
        rutina3.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina3);
        DiaRutinaDto actualMapearDtoResult = diaRutinaServicioImpl.mapearDto(diaRutina);
        assertEquals("Descripcion", actualMapearDtoResult.getDescripcion());
        assertEquals("Nombre", actualMapearDtoResult.getNombre());
        assertEquals(123L, actualMapearDtoResult.getId_rutina().longValue());
        assertEquals(123L, actualMapearDtoResult.getId().longValue());
        assertEquals(diaRutinaList, actualMapearDtoResult.getEjerciciosDiaRutina());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    void testMapearEntidad() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutina actualMapearEntidadResult = diaRutinaServicioImpl.mapearEntidad(diaRutinaDto);
        assertEquals("Descripcion", actualMapearEntidadResult.getDescripcion());
        assertSame(rutina, actualMapearEntidadResult.getRutina());
        assertEquals("Nombre", actualMapearEntidadResult.getNombre());
        assertTrue(actualMapearEntidadResult.getEjerciciosDiaRutina().isEmpty());
        assertEquals(123L, actualMapearEntidadResult.getId().longValue());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    void testMapearEntidad2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutina actualMapearEntidadResult = diaRutinaServicioImpl.mapearEntidad(diaRutinaDto);
        assertEquals("Descripcion", actualMapearEntidadResult.getDescripcion());
        assertSame(rutina, actualMapearEntidadResult.getRutina());
        assertEquals("Nombre", actualMapearEntidadResult.getNombre());
        assertEquals(1, actualMapearEntidadResult.getEjerciciosDiaRutina().size());
        assertEquals(123L, actualMapearEntidadResult.getId().longValue());
        verify(ejercicioRepositorio).findById((Long) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapearEntidad3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.EjercicioRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.toEntityEjercicioDiaRutina(DiaRutinaServicioImpl.java:173)
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.mapearEntidad(DiaRutinaServicioImpl.java:156)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findById((Long) any())).thenReturn(null);

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        diaRutinaServicioImpl.mapearEntidad(diaRutinaDto);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    void testMapearEntidad4() {
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.mapearEntidad(diaRutinaDto));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    void testMapearEntidad5() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(null);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        DiaRutina actualMapearEntidadResult = diaRutinaServicioImpl.mapearEntidad(diaRutinaDto);
        assertEquals("Descripcion", actualMapearEntidadResult.getDescripcion());
        assertSame(rutina, actualMapearEntidadResult.getRutina());
        assertEquals("Nombre", actualMapearEntidadResult.getNombre());
        assertEquals(1, actualMapearEntidadResult.getEjerciciosDiaRutina().size());
        assertEquals(123L, actualMapearEntidadResult.getId().longValue());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#mapearEntidad(DiaRutinaDto)}
     */
    @Test
    void testMapearEntidad6() {
        when(ejercicioRepositorio.findById((Long) any())).thenThrow(new IllegalArgumentException());

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);

        ArrayList<EjercicioDiaRutinaDto> ejercicioDiaRutinaDtoList = new ArrayList<>();
        ejercicioDiaRutinaDtoList.add(ejercicioDiaRutinaDto);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(ejercicioDiaRutinaDtoList);
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");
        assertThrows(IllegalArgumentException.class, () -> diaRutinaServicioImpl.mapearEntidad(diaRutinaDto));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEntityEjercicioDiaRutina(EjercicioDiaRutinaDto)}
     */
    @Test
    void testToEntityEjercicioDiaRutina() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        EjercicioDiaRutina actualToEntityEjercicioDiaRutinaResult = diaRutinaServicioImpl
                .toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto);
        assertEquals(1, actualToEntityEjercicioDiaRutinaResult.getSeries());
        assertEquals(1, actualToEntityEjercicioDiaRutinaResult.getRepeticiones());
        assertEquals(1L, actualToEntityEjercicioDiaRutinaResult.getId().longValue());
        assertSame(ejercicio, actualToEntityEjercicioDiaRutinaResult.getEjercicio());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEntityEjercicioDiaRutina(EjercicioDiaRutinaDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testToEntityEjercicioDiaRutina2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.leandro.routineapp.repository.EjercicioRepositorio.findById(Object)" is null
        //       at com.leandro.routineapp.service.DiaRutinaServicioImpl.toEntityEjercicioDiaRutina(DiaRutinaServicioImpl.java:173)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findById((Long) any())).thenReturn(null);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        diaRutinaServicioImpl.toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto);
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEntityEjercicioDiaRutina(EjercicioDiaRutinaDto)}
     */
    @Test
    void testToEntityEjercicioDiaRutina3() {
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEntityEjercicioDiaRutina(EjercicioDiaRutinaDto)}
     */
    @Test
    void testToEntityEjercicioDiaRutina4() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        Optional<Ejercicio> ofResult = Optional.of(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(null);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        EjercicioDiaRutina actualToEntityEjercicioDiaRutinaResult = diaRutinaServicioImpl
                .toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto);
        assertEquals(1, actualToEntityEjercicioDiaRutinaResult.getSeries());
        assertEquals(1, actualToEntityEjercicioDiaRutinaResult.getRepeticiones());
        assertEquals(1L, actualToEntityEjercicioDiaRutinaResult.getId().longValue());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEntityEjercicioDiaRutina(EjercicioDiaRutinaDto)}
     */
    @Test
    void testToEntityEjercicioDiaRutina5() {
        when(ejercicioRepositorio.findById((Long) any())).thenThrow(new IllegalArgumentException());

        EjercicioDiaRutinaDto ejercicioDiaRutinaDto = new EjercicioDiaRutinaDto();
        ejercicioDiaRutinaDto.setEjercicioId(123L);
        ejercicioDiaRutinaDto.setId_EjercicioRutina(1L);
        ejercicioDiaRutinaDto.setRepeticiones(1);
        ejercicioDiaRutinaDto.setSeries(1);
        assertThrows(IllegalArgumentException.class,
                () -> diaRutinaServicioImpl.toEntityEjercicioDiaRutina(ejercicioDiaRutinaDto));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEjercicioDiaRutinaDto(EjercicioDiaRutina)}
     */
    @Test
    void testToEjercicioDiaRutinaDto() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        EjercicioDiaRutinaDto actualToEjercicioDiaRutinaDtoResult = diaRutinaServicioImpl
                .toEjercicioDiaRutinaDto(ejercicioDiaRutina);
        assertEquals(123L, actualToEjercicioDiaRutinaDtoResult.getEjercicioId().longValue());
        assertEquals(1, actualToEjercicioDiaRutinaDtoResult.getSeries());
        assertEquals(1, actualToEjercicioDiaRutinaDtoResult.getRepeticiones());
        assertEquals(123L, actualToEjercicioDiaRutinaDtoResult.getId_EjercicioRutina().longValue());
    }

    /**
     * Method under test: {@link DiaRutinaServicioImpl#toEjercicioDiaRutinaDto(EjercicioDiaRutina)}
     */
    @Test
    void testToEjercicioDiaRutinaDto2() {
        Comentario comentario = new Comentario();
        comentario.setComentarioPadre(new Comentario());
        comentario.setContenido("Contenido");
        comentario.setFecha(mock(Timestamp.class));
        comentario.setId(123L);
        comentario.setRespuestas(new ArrayList<>());
        comentario.setRutina(new Rutina());
        comentario.setUsuario("Usuario");

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        Comentario comentario1 = new Comentario();
        comentario1.setComentarioPadre(comentario);
        comentario1.setContenido("Contenido");
        comentario1.setFecha(mock(Timestamp.class));
        comentario1.setId(123L);
        comentario1.setRespuestas(new ArrayList<>());
        comentario1.setRutina(rutina);
        comentario1.setUsuario("Usuario");

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        Comentario comentario2 = new Comentario();
        comentario2.setComentarioPadre(comentario1);
        comentario2.setContenido("Contenido");
        comentario2.setFecha(mock(Timestamp.class));
        comentario2.setId(123L);
        comentario2.setRespuestas(new ArrayList<>());
        comentario2.setRutina(rutina1);
        comentario2.setUsuario("Usuario");

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(new ArrayList<>());
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        Comentario comentario3 = new Comentario();
        comentario3.setComentarioPadre(comentario2);
        comentario3.setContenido("Contenido");
        comentario3.setFecha(mock(Timestamp.class));
        comentario3.setId(123L);
        comentario3.setRespuestas(new ArrayList<>());
        comentario3.setRutina(rutina2);
        comentario3.setUsuario("Usuario");

        ArrayList<Comentario> comentarioList = new ArrayList<>();
        comentarioList.add(comentario3);

        Rutina rutina3 = new Rutina();
        rutina3.setComentarios(comentarioList);
        rutina3.setCreador("Creador");
        rutina3.setDescripcion("Descripcion");
        rutina3.setDias_rutina(new ArrayList<>());
        rutina3.setId(123L);
        rutina3.setNombre("Nombre");
        rutina3.setNumPuntuaciones(1L);
        rutina3.setPuntuacion(10.0d);
        rutina3.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina3);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        EjercicioDiaRutina ejercicioDiaRutina = new EjercicioDiaRutina();
        ejercicioDiaRutina.setDiaRutina(diaRutina);
        ejercicioDiaRutina.setEjercicio(ejercicio);
        ejercicioDiaRutina.setId(123L);
        ejercicioDiaRutina.setRepeticiones(1);
        ejercicioDiaRutina.setSeries(1);
        EjercicioDiaRutinaDto actualToEjercicioDiaRutinaDtoResult = diaRutinaServicioImpl
                .toEjercicioDiaRutinaDto(ejercicioDiaRutina);
        assertEquals(123L, actualToEjercicioDiaRutinaDtoResult.getEjercicioId().longValue());
        assertEquals(1, actualToEjercicioDiaRutinaDtoResult.getSeries());
        assertEquals(1, actualToEjercicioDiaRutinaDtoResult.getRepeticiones());
        assertEquals(123L, actualToEjercicioDiaRutinaDtoResult.getId_EjercicioRutina().longValue());
    }
}

