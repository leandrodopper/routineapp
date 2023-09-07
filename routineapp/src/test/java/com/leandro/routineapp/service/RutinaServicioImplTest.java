package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.DiaRutinaDto;
import com.leandro.routineapp.dto.EjercicioDiaRutinaDto;
import com.leandro.routineapp.dto.RutinaDto;
import com.leandro.routineapp.dto.RutinaRespuesta;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.EjercicioDiaRutina;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RutinaServicioImpl.class})
@ExtendWith(SpringExtension.class)
class RutinaServicioImplTest {
    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @MockBean
    private RutinaRepositorio rutinaRepositorio;

    @Autowired
    private RutinaServicioImpl rutinaServicioImpl;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Method under test: {@link RutinaServicioImpl#crearRutina(RutinaDto)}
     */
    @Test
    void testCrearRutina() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        ArrayList<DiaRutinaDto> diaRutinaDtoList = new ArrayList<>();
        rutinaDto.setDias_rutina(diaRutinaDtoList);
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualCrearRutinaResult = rutinaServicioImpl.crearRutina(rutinaDto);
        assertEquals("Creador", actualCrearRutinaResult.getCreador());
        assertEquals(10.0d, actualCrearRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualCrearRutinaResult.getNombre());
        assertEquals(123L, actualCrearRutinaResult.getId().longValue());
        assertEquals(diaRutinaDtoList, actualCrearRutinaResult.getDias_rutina());
        assertEquals("Descripcion", actualCrearRutinaResult.getDescripcion());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#crearRutina(RutinaDto)}
     */
    @Test
    void testCrearRutina2() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        ArrayList<DiaRutinaDto> diaRutinaDtoList = new ArrayList<>();
        rutinaDto.setDias_rutina(diaRutinaDtoList);
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualCrearRutinaResult = rutinaServicioImpl.crearRutina(rutinaDto);
        assertEquals("Creador", actualCrearRutinaResult.getCreador());
        assertEquals(10.0d, actualCrearRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualCrearRutinaResult.getNombre());
        assertEquals(123L, actualCrearRutinaResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualCrearRutinaResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualCrearRutinaResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(diaRutinaDtoList, getResult.getEjerciciosDiaRutina());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#crearRutina(RutinaDto)}
     */
    @Test
    void testCrearRutina3() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina);

        DiaRutinaDto diaRutinaDto = new DiaRutinaDto();
        diaRutinaDto.setDescripcion("Descripcion");
        diaRutinaDto.setEjerciciosDiaRutina(new ArrayList<>());
        diaRutinaDto.setId(123L);
        diaRutinaDto.setId_rutina(1L);
        diaRutinaDto.setNombre("Nombre");

        ArrayList<DiaRutinaDto> diaRutinaDtoList = new ArrayList<>();
        diaRutinaDtoList.add(diaRutinaDto);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        rutinaDto.setDias_rutina(diaRutinaDtoList);
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualCrearRutinaResult = rutinaServicioImpl.crearRutina(rutinaDto);
        assertEquals("Creador", actualCrearRutinaResult.getCreador());
        assertEquals(10.0d, actualCrearRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualCrearRutinaResult.getNombre());
        assertEquals(123L, actualCrearRutinaResult.getId().longValue());
        assertEquals(comentarioList, actualCrearRutinaResult.getDias_rutina());
        assertEquals("Descripcion", actualCrearRutinaResult.getDescripcion());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#crearRutina(RutinaDto)}
     */
    @Test
    void testCrearRutina4() {
        when(rutinaRepositorio.save((Rutina) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        rutinaDto.setDias_rutina(new ArrayList<>());
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.crearRutina(rutinaDto));
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#crearRutina(RutinaDto)}
     */
    @Test
    void testCrearRutina5() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(diaRutinaList);
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina2);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        rutinaDto.setDias_rutina(new ArrayList<>());
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualCrearRutinaResult = rutinaServicioImpl.crearRutina(rutinaDto);
        assertEquals("Creador", actualCrearRutinaResult.getCreador());
        assertEquals(10.0d, actualCrearRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualCrearRutinaResult.getNombre());
        assertEquals(123L, actualCrearRutinaResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualCrearRutinaResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualCrearRutinaResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = getResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult1 = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult1.getEjercicioId().longValue());
        assertEquals(1, getResult1.getSeries());
        assertEquals(1, getResult1.getRepeticiones());
        assertEquals(123L, getResult1.getId_EjercicioRutina().longValue());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinaPorId(Long)}
     */
    @Test
    void testObtenerRutinaPorId() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        RutinaDto actualObtenerRutinaPorIdResult = rutinaServicioImpl.obtenerRutinaPorId(123L);
        assertEquals("Creador", actualObtenerRutinaPorIdResult.getCreador());
        assertEquals(10.0d, actualObtenerRutinaPorIdResult.getPuntuacion());
        assertEquals("Nombre", actualObtenerRutinaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerRutinaPorIdResult.getId().longValue());
        assertEquals(comentarioList, actualObtenerRutinaPorIdResult.getDias_rutina());
        assertEquals("Descripcion", actualObtenerRutinaPorIdResult.getDescripcion());
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinaPorId(Long)}
     */
    @Test
    void testObtenerRutinaPorId2() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina1.setComentarios(comentarioList);
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());
        Optional<Rutina> ofResult = Optional.of(rutina1);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        RutinaDto actualObtenerRutinaPorIdResult = rutinaServicioImpl.obtenerRutinaPorId(123L);
        assertEquals("Creador", actualObtenerRutinaPorIdResult.getCreador());
        assertEquals(10.0d, actualObtenerRutinaPorIdResult.getPuntuacion());
        assertEquals("Nombre", actualObtenerRutinaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerRutinaPorIdResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualObtenerRutinaPorIdResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualObtenerRutinaPorIdResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(comentarioList, getResult.getEjerciciosDiaRutina());
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinaPorId(Long)}
     */
    @Test
    void testObtenerRutinaPorId3() {
        when(rutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinaPorId(123L));
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinaPorId(Long)}
     */
    @Test
    void testObtenerRutinaPorId4() {
        when(rutinaRepositorio.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Rutina", "Rutina", "Rutina"));
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinaPorId(123L));
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinaPorId(Long)}
     */
    @Test
    void testObtenerRutinaPorId5() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(diaRutinaList);
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());
        Optional<Rutina> ofResult = Optional.of(rutina2);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        RutinaDto actualObtenerRutinaPorIdResult = rutinaServicioImpl.obtenerRutinaPorId(123L);
        assertEquals("Creador", actualObtenerRutinaPorIdResult.getCreador());
        assertEquals(10.0d, actualObtenerRutinaPorIdResult.getPuntuacion());
        assertEquals("Nombre", actualObtenerRutinaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerRutinaPorIdResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualObtenerRutinaPorIdResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualObtenerRutinaPorIdResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        List<EjercicioDiaRutinaDto> ejerciciosDiaRutina = getResult.getEjerciciosDiaRutina();
        assertEquals(1, ejerciciosDiaRutina.size());
        EjercicioDiaRutinaDto getResult1 = ejerciciosDiaRutina.get(0);
        assertEquals(123L, getResult1.getEjercicioId().longValue());
        assertEquals(1, getResult1.getSeries());
        assertEquals(1, getResult1.getRepeticiones());
        assertEquals(123L, getResult1.getId_EjercicioRutina().longValue());
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    void testObtenerRutinas() {
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(rutinaList));
        RutinaRespuesta actualObtenerRutinasResult = rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir");
        assertEquals(rutinaList, actualObtenerRutinasResult.getContenido());
        assertTrue(actualObtenerRutinasResult.isUltima());
        assertEquals(1L, actualObtenerRutinasResult.getTotalPaginas());
        assertEquals(0L, actualObtenerRutinasResult.getTotalElementos());
        assertEquals(0, actualObtenerRutinasResult.getTamPagina());
        assertEquals(0, actualObtenerRutinasResult.getNumPagina());
        verify(rutinaRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    void testObtenerRutinas2() {
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

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina);
        PageImpl<Rutina> pageImpl = new PageImpl<>(rutinaList);
        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        RutinaRespuesta actualObtenerRutinasResult = rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir");
        assertEquals(1, actualObtenerRutinasResult.getContenido().size());
        assertTrue(actualObtenerRutinasResult.isUltima());
        assertEquals(1L, actualObtenerRutinasResult.getTotalPaginas());
        assertEquals(1L, actualObtenerRutinasResult.getTotalElementos());
        assertEquals(1, actualObtenerRutinasResult.getTamPagina());
        assertEquals(0, actualObtenerRutinasResult.getNumPagina());
        verify(rutinaRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    void testObtenerRutinas3() {
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

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina1);
        rutinaList.add(rutina);
        PageImpl<Rutina> pageImpl = new PageImpl<>(rutinaList);
        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        RutinaRespuesta actualObtenerRutinasResult = rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir");
        assertEquals(2, actualObtenerRutinasResult.getContenido().size());
        assertTrue(actualObtenerRutinasResult.isUltima());
        assertEquals(1L, actualObtenerRutinasResult.getTotalPaginas());
        assertEquals(2L, actualObtenerRutinasResult.getTotalElementos());
        assertEquals(2, actualObtenerRutinasResult.getTamPagina());
        assertEquals(0, actualObtenerRutinasResult.getNumPagina());
        verify(rutinaRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerRutinas4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.stream()" because "rutinas" is null
        //       at com.leandro.routineapp.service.RutinaServicioImpl.obtenerRutinas(RutinaServicioImpl.java:68)
        //   See https://diff.blue/R013 to resolve this issue.

        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(null);
        rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir");
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerRutinas5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.RutinaServicioImpl.obtenerRutinas(RutinaServicioImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        rutinaServicioImpl.obtenerRutinas(-1, 1, "Ordenar Por", "Sort Dir");
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerRutinas6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Property must not null or empty!
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:992)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.leandro.routineapp.service.RutinaServicioImpl.obtenerRutinas(RutinaServicioImpl.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        rutinaServicioImpl.obtenerRutinas(10, 1, "", "Sort Dir");
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    void testObtenerRutinas7() {
        when(rutinaRepositorio.findAll((Pageable) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class,
                () -> rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir"));
        verify(rutinaRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinas(int, int, String, String)}
     */
    @Test
    void testObtenerRutinas8() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina1);
        PageImpl<Rutina> pageImpl = new PageImpl<>(rutinaList);
        when(rutinaRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        RutinaRespuesta actualObtenerRutinasResult = rutinaServicioImpl.obtenerRutinas(10, 1, "Ordenar Por", "Sort Dir");
        assertEquals(1, actualObtenerRutinasResult.getContenido().size());
        assertTrue(actualObtenerRutinasResult.isUltima());
        assertEquals(1L, actualObtenerRutinasResult.getTotalPaginas());
        assertEquals(1L, actualObtenerRutinasResult.getTotalElementos());
        assertEquals(1, actualObtenerRutinasResult.getTamPagina());
        assertEquals(0, actualObtenerRutinasResult.getNumPagina());
        verify(rutinaRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#eliminarRutina(Long)}
     */
    @Test
    void testEliminarRutina() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        doNothing().when(rutinaRepositorio).delete((Rutina) any());
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        rutinaServicioImpl.eliminarRutina(123L);
        verify(rutinaRepositorio).findById((Long) any());
        verify(rutinaRepositorio).delete((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#eliminarRutina(Long)}
     */
    @Test
    void testEliminarRutina2() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        doThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo")).when(rutinaRepositorio)
                .delete((Rutina) any());
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.eliminarRutina(123L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(rutinaRepositorio).delete((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#eliminarRutina(Long)}
     */
    @Test
    void testEliminarRutina3() {
        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");

        ArrayList<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(usuarioList);
        Optional<Rutina> ofResult = Optional.of(rutina);
        doNothing().when(rutinaRepositorio).delete((Rutina) any());
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario1 = new Usuario();
        usuario1.setAltura(10.0d);
        usuario1.setApellidos("Apellidos");
        usuario1.setEdad(1);
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setImagen("Imagen");
        usuario1.setNombre("Nombre");
        usuario1.setPassword("iloveyou");
        usuario1.setPeso(10.0d);
        usuario1.setRoles(new HashSet<>());
        usuario1.setRutinasSeguidas(new ArrayList<>());
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");
        when(usuarioRepositorio.save((Usuario) any())).thenReturn(usuario1);
        rutinaServicioImpl.eliminarRutina(123L);
        verify(rutinaRepositorio).findById((Long) any());
        verify(rutinaRepositorio).delete((Rutina) any());
        verify(usuarioRepositorio).save((Usuario) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#eliminarRutina(Long)}
     */
    @Test
    void testEliminarRutina4() {
        doNothing().when(rutinaRepositorio).delete((Rutina) any());
        when(rutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        when(usuarioRepositorio.save((Usuario) any())).thenReturn(usuario);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.eliminarRutina(123L));
        verify(rutinaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#eliminarRutina(Long)}
     */
    @Test
    void testEliminarRutina5() {
        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");

        ArrayList<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);

        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(usuarioList);
        Optional<Rutina> ofResult = Optional.of(rutina);
        doNothing().when(rutinaRepositorio).delete((Rutina) any());
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(usuarioRepositorio.save((Usuario) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.eliminarRutina(123L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).save((Usuario) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#actualizarRutina(RutinaDto, Long)}
     */
    @Test
    void testActualizarRutina() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        ArrayList<DiaRutinaDto> diaRutinaDtoList = new ArrayList<>();
        rutinaDto.setDias_rutina(diaRutinaDtoList);
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualActualizarRutinaResult = rutinaServicioImpl.actualizarRutina(rutinaDto, 123L);
        assertEquals("Creador", actualActualizarRutinaResult.getCreador());
        assertEquals(10.0d, actualActualizarRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualActualizarRutinaResult.getNombre());
        assertEquals(123L, actualActualizarRutinaResult.getId().longValue());
        assertEquals(diaRutinaDtoList, actualActualizarRutinaResult.getDias_rutina());
        assertEquals("Descripcion", actualActualizarRutinaResult.getDescripcion());
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#actualizarRutina(RutinaDto, Long)}
     */
    @Test
    void testActualizarRutina2() {
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
        when(rutinaRepositorio.save((Rutina) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        rutinaDto.setDias_rutina(new ArrayList<>());
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.actualizarRutina(rutinaDto, 123L));
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#actualizarRutina(RutinaDto, Long)}
     */
    @Test
    void testActualizarRutina3() {
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

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina1);

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("Creador");
        rutina2.setDescripcion("Descripcion");
        rutina2.setDias_rutina(diaRutinaList);
        rutina2.setId(123L);
        rutina2.setNombre("Nombre");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina2);
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);

        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setCreador("Creador");
        rutinaDto.setDescripcion("Descripcion");
        ArrayList<DiaRutinaDto> diaRutinaDtoList = new ArrayList<>();
        rutinaDto.setDias_rutina(diaRutinaDtoList);
        rutinaDto.setId(123L);
        rutinaDto.setNombre("Nombre");
        rutinaDto.setPuntuacion(10.0d);
        RutinaDto actualActualizarRutinaResult = rutinaServicioImpl.actualizarRutina(rutinaDto, 123L);
        assertEquals("Creador", actualActualizarRutinaResult.getCreador());
        assertEquals(10.0d, actualActualizarRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualActualizarRutinaResult.getNombre());
        assertEquals(123L, actualActualizarRutinaResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualActualizarRutinaResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualActualizarRutinaResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(diaRutinaDtoList, getResult.getEjerciciosDiaRutina());
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#seguirRutina(Long, Long)}
     */
    @Test
    void testSeguirRutina() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);

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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult1 = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setAltura(10.0d);
        usuario1.setApellidos("Apellidos");
        usuario1.setEdad(1);
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setImagen("Imagen");
        usuario1.setNombre("Nombre");
        usuario1.setPassword("iloveyou");
        usuario1.setPeso(10.0d);
        usuario1.setRoles(new HashSet<>());
        usuario1.setRutinasSeguidas(new ArrayList<>());
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");
        when(usuarioRepositorio.save((Usuario) any())).thenReturn(usuario1);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult1);
        rutinaServicioImpl.seguirRutina(1L, 1L);
        verify(rutinaRepositorio).save((Rutina) any());
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).save((Usuario) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#seguirRutina(Long, Long)}
     */
    @Test
    void testSeguirRutina2() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);

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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult1 = Optional.of(usuario);
        when(usuarioRepositorio.save((Usuario) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.seguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).save((Usuario) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#seguirRutina(Long, Long)}
     */
    @Test
    void testSeguirRutina3() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setAltura(10.0d);
        usuario1.setApellidos("Apellidos");
        usuario1.setEdad(1);
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setImagen("Imagen");
        usuario1.setNombre("Nombre");
        usuario1.setPassword("iloveyou");
        usuario1.setPeso(10.0d);
        usuario1.setRoles(new HashSet<>());
        usuario1.setRutinasSeguidas(new ArrayList<>());
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");
        when(usuarioRepositorio.save((Usuario) any())).thenReturn(usuario1);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.seguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#seguirRutina(Long, Long)}
     */
    @Test
    void testSeguirRutina4() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);

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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        when(usuarioRepositorio.save((Usuario) any())).thenReturn(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.seguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#dejarseguirRutina(Long, Long)}
     */
    @Test
    void testDejarseguirRutina() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult1 = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(IllegalArgumentException.class, () -> rutinaServicioImpl.dejarseguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#dejarseguirRutina(Long, Long)}
     */
    @Test
    void testDejarseguirRutina2() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(usuarioRepositorio.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Usuario o rutina", "Usuario o rutina", "Usuario o rutina"));
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.dejarseguirRutina(1L, 1L));
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#dejarseguirRutina(Long, Long)}
     */
    @Test
    void testDejarseguirRutina3() {
        when(rutinaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.dejarseguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#dejarseguirRutina(Long, Long)}
     */
    @Test
    void testDejarseguirRutina4() {
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
        Optional<Rutina> ofResult = Optional.of(rutina);
        when(rutinaRepositorio.findById((Long) any())).thenReturn(ofResult);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.dejarseguirRutina(1L, 1L));
        verify(rutinaRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasSeguidasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasSeguidasUsuario() {
        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertTrue(rutinaServicioImpl.obtenerRutinasSeguidasUsuario(1L).isEmpty());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasSeguidasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasSeguidasUsuario2() {
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

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(rutinaList);
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasSeguidasUsuario(1L).size());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasSeguidasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasSeguidasUsuario3() {
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinasSeguidasUsuario(1L));
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasSeguidasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasSeguidasUsuario4() {
        when(usuarioRepositorio.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Usuario", "Usuario", "Usuario"));
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinasSeguidasUsuario(1L));
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasSeguidasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasSeguidasUsuario5() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina1);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(rutinaList);
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasSeguidasUsuario(1L).size());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasCreadasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasCreadasUsuario() {
        when(rutinaRepositorio.findByCreador((String) any())).thenReturn(new ArrayList<>());

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertTrue(rutinaServicioImpl.obtenerRutinasCreadasUsuario(1L).isEmpty());
        verify(rutinaRepositorio).findByCreador((String) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasCreadasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasCreadasUsuario2() {
        when(rutinaRepositorio.findByCreador((String) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinasCreadasUsuario(1L));
        verify(rutinaRepositorio).findByCreador((String) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasCreadasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasCreadasUsuario3() {
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

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina);
        when(rutinaRepositorio.findByCreador((String) any())).thenReturn(rutinaList);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasCreadasUsuario(1L).size());
        verify(rutinaRepositorio).findByCreador((String) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasCreadasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasCreadasUsuario4() {
        when(rutinaRepositorio.findByCreador((String) any())).thenReturn(new ArrayList<>());
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinasCreadasUsuario(1L));
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasCreadasUsuario(Long)}
     */
    @Test
    void testObtenerRutinasCreadasUsuario5() {
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

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina1);
        when(rutinaRepositorio.findByCreador((String) any())).thenReturn(rutinaList);

        Usuario usuario = new Usuario();
        usuario.setAltura(10.0d);
        usuario.setApellidos("Apellidos");
        usuario.setEdad(1);
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setImagen("Imagen");
        usuario.setNombre("Nombre");
        usuario.setPassword("iloveyou");
        usuario.setPeso(10.0d);
        usuario.setRoles(new HashSet<>());
        usuario.setRutinasSeguidas(new ArrayList<>());
        usuario.setTelefono("Telefono");
        usuario.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasCreadasUsuario(1L).size());
        verify(rutinaRepositorio).findByCreador((String) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(rutinaServicioImpl.obtenerRutinasPorNombre("Nombre").isEmpty());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre2() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("̀");
        rutina.setDescripcion("̀");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("̀");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina);
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(rutinaList);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasPorNombre("Nombre").size());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre3() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(rutinaServicioImpl.obtenerRutinasPorNombre("̀").isEmpty());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre4() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any()))
                .thenThrow(new ResourceNotFoundException("̀", "̀", "̀"));
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.obtenerRutinasPorNombre("Nombre"));
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre5() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(rutinaServicioImpl.obtenerRutinasPorNombre("̀̀").isEmpty());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre6() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(rutinaServicioImpl.obtenerRutinasPorNombre("̀\\p{InCombiningDiacriticalMarks}+").isEmpty());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre7() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("̀");
        rutina.setDescripcion("̀");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("̀");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("̀");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("̀");
        diaRutina.setRutina(rutina);

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        rutina1.setComentarios(new ArrayList<>());
        rutina1.setCreador("̀");
        rutina1.setDescripcion("̀");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("̀");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina1);
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(rutinaList);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasPorNombre("Nombre").size());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre8() {
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(rutinaServicioImpl.obtenerRutinasPorNombre("̀Nombre").isEmpty());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#obtenerRutinasPorNombre(String)}
     */
    @Test
    void testObtenerRutinasPorNombre9() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("̀");
        rutina.setDescripcion("̀");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("̀");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(10.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("̀");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("̀");
        diaRutina.setRutina(rutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
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
        rutina1.setCreador("̀");
        rutina1.setDescripcion("̀");
        rutina1.setDias_rutina(new ArrayList<>());
        rutina1.setId(123L);
        rutina1.setNombre("̀");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina1 = new DiaRutina();
        diaRutina1.setDescripcion("̀");
        diaRutina1.setEjerciciosDiaRutina(ejercicioDiaRutinaSet);
        diaRutina1.setId(123L);
        diaRutina1.setNombre("̀");
        diaRutina1.setRutina(rutina1);

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina1);

        Rutina rutina2 = new Rutina();
        rutina2.setComentarios(new ArrayList<>());
        rutina2.setCreador("̀");
        rutina2.setDescripcion("̀");
        rutina2.setDias_rutina(diaRutinaList);
        rutina2.setId(123L);
        rutina2.setNombre("̀");
        rutina2.setNumPuntuaciones(1L);
        rutina2.setPuntuacion(10.0d);
        rutina2.setSeguidores(new ArrayList<>());

        ArrayList<Rutina> rutinaList = new ArrayList<>();
        rutinaList.add(rutina2);
        when(rutinaRepositorio.findByNombreContainingIgnoreCase((String) any())).thenReturn(rutinaList);
        assertEquals(1, rutinaServicioImpl.obtenerRutinasPorNombre("Nombre").size());
        verify(rutinaRepositorio).findByNombreContainingIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#puntuarRutina(Long, double)}
     */
    @Test
    void testPuntuarRutina() {
        assertThrows(IllegalArgumentException.class, () -> rutinaServicioImpl.puntuarRutina(123L, 10.0d));
    }

    /**
     * Method under test: {@link RutinaServicioImpl#puntuarRutina(Long, double)}
     */
    @Test
    void testPuntuarRutina2() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);
        RutinaDto actualPuntuarRutinaResult = rutinaServicioImpl.puntuarRutina(123L, 5.0d);
        assertEquals("Creador", actualPuntuarRutinaResult.getCreador());
        assertEquals(7.5d, actualPuntuarRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualPuntuarRutinaResult.getNombre());
        assertEquals(123L, actualPuntuarRutinaResult.getId().longValue());
        assertEquals(comentarioList, actualPuntuarRutinaResult.getDias_rutina());
        assertEquals("Descripcion", actualPuntuarRutinaResult.getDescripcion());
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#puntuarRutina(Long, double)}
     */
    @Test
    void testPuntuarRutina3() {
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
        when(rutinaRepositorio.save((Rutina) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);
        assertThrows(ResourceNotFoundException.class, () -> rutinaServicioImpl.puntuarRutina(123L, 5.0d));
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#puntuarRutina(Long, double)}
     */
    @Test
    void testPuntuarRutina4() {
        Rutina rutina = new Rutina();
        rutina.setComentarios(new ArrayList<>());
        rutina.setCreador("Creador");
        rutina.setDescripcion("Descripcion");
        rutina.setDias_rutina(new ArrayList<>());
        rutina.setId(123L);
        rutina.setNombre("Nombre");
        rutina.setNumPuntuaciones(1L);
        rutina.setPuntuacion(5.0d);
        rutina.setSeguidores(new ArrayList<>());

        DiaRutina diaRutina = new DiaRutina();
        diaRutina.setDescripcion("Descripcion");
        diaRutina.setEjerciciosDiaRutina(new HashSet<>());
        diaRutina.setId(123L);
        diaRutina.setNombre("Nombre");
        diaRutina.setRutina(rutina);

        ArrayList<DiaRutina> diaRutinaList = new ArrayList<>();
        diaRutinaList.add(diaRutina);

        Rutina rutina1 = new Rutina();
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        rutina1.setComentarios(comentarioList);
        rutina1.setCreador("Creador");
        rutina1.setDescripcion("Descripcion");
        rutina1.setDias_rutina(diaRutinaList);
        rutina1.setId(123L);
        rutina1.setNombre("Nombre");
        rutina1.setNumPuntuaciones(1L);
        rutina1.setPuntuacion(10.0d);
        rutina1.setSeguidores(new ArrayList<>());

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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina2);
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina1);
        RutinaDto actualPuntuarRutinaResult = rutinaServicioImpl.puntuarRutina(123L, 5.0d);
        assertEquals("Creador", actualPuntuarRutinaResult.getCreador());
        assertEquals(7.5d, actualPuntuarRutinaResult.getPuntuacion());
        assertEquals("Nombre", actualPuntuarRutinaResult.getNombre());
        assertEquals(123L, actualPuntuarRutinaResult.getId().longValue());
        List<DiaRutinaDto> dias_rutina = actualPuntuarRutinaResult.getDias_rutina();
        assertEquals(1, dias_rutina.size());
        assertEquals("Descripcion", actualPuntuarRutinaResult.getDescripcion());
        DiaRutinaDto getResult = dias_rutina.get(0);
        assertEquals("Descripcion", getResult.getDescripcion());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId_rutina().longValue());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(comentarioList, getResult.getEjerciciosDiaRutina());
        verify(rutinaRepositorio).getById((Long) any());
        verify(rutinaRepositorio).save((Rutina) any());
    }

    /**
     * Method under test: {@link RutinaServicioImpl#puntuarRutina(Long, double)}
     */
    @Test
    void testPuntuarRutina5() {
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
        when(rutinaRepositorio.save((Rutina) any())).thenReturn(rutina1);
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina);
        assertThrows(IllegalArgumentException.class, () -> rutinaServicioImpl.puntuarRutina(123L, -0.5d));
    }
}

