package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.EjercicioDto;
import com.leandro.routineapp.dto.EjercicioRespuesta;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.EjercicioRepositorio;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EjercicioServicioImpl.class})
@ExtendWith(SpringExtension.class)
class EjercicioServicioImplTest {
    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @Autowired
    private EjercicioServicioImpl ejercicioServicioImpl;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Method under test: {@link EjercicioServicioImpl#crearEjercicio(EjercicioDto)}
     */
    @Test
    void testCrearEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.save((Ejercicio) any())).thenReturn(ejercicio);

        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setDescripcion("Descripcion");
        ejercicioDto.setDificultad("Dificultad");
        ejercicioDto.setGrupo_muscular("Grupo muscular");
        ejercicioDto.setId(123L);
        ejercicioDto.setImagen("Imagen");
        ejercicioDto.setNombre("Nombre");
        ejercicioDto.setUsername_creador("janedoe");
        EjercicioDto actualCrearEjercicioResult = ejercicioServicioImpl.crearEjercicio(ejercicioDto);
        assertEquals("Descripcion", actualCrearEjercicioResult.getDescripcion());
        assertEquals("janedoe", actualCrearEjercicioResult.getUsername_creador());
        assertEquals("Nombre", actualCrearEjercicioResult.getNombre());
        assertEquals("Imagen", actualCrearEjercicioResult.getImagen());
        assertEquals(123L, actualCrearEjercicioResult.getId().longValue());
        assertEquals("Grupo muscular", actualCrearEjercicioResult.getGrupo_muscular());
        assertEquals("Dificultad", actualCrearEjercicioResult.getDificultad());
        verify(ejercicioRepositorio).save((Ejercicio) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#crearEjercicio(EjercicioDto)}
     */
    @Test
    void testCrearEjercicio2() {
        when(ejercicioRepositorio.save((Ejercicio) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setDescripcion("Descripcion");
        ejercicioDto.setDificultad("Dificultad");
        ejercicioDto.setGrupo_muscular("Grupo muscular");
        ejercicioDto.setId(123L);
        ejercicioDto.setImagen("Imagen");
        ejercicioDto.setNombre("Nombre");
        ejercicioDto.setUsername_creador("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.crearEjercicio(ejercicioDto));
        verify(ejercicioRepositorio).save((Ejercicio) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    void testObtenerTodosEjercicios() {
        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(ejercicioList));
        EjercicioRespuesta actualObtenerTodosEjerciciosResult = ejercicioServicioImpl.obtenerTodosEjercicios(10, 1,
                "Ordenar Por", "Sort Dir");
        assertEquals(ejercicioList, actualObtenerTodosEjerciciosResult.getContenido());
        assertTrue(actualObtenerTodosEjerciciosResult.isUltima());
        assertEquals(1L, actualObtenerTodosEjerciciosResult.getTotalPaginas());
        assertEquals(0L, actualObtenerTodosEjerciciosResult.getTotalElementos());
        assertEquals(0, actualObtenerTodosEjerciciosResult.getTamPagina());
        assertEquals(0, actualObtenerTodosEjerciciosResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    void testObtenerTodosEjercicios2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        PageImpl<Ejercicio> pageImpl = new PageImpl<>(ejercicioList);
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        EjercicioRespuesta actualObtenerTodosEjerciciosResult = ejercicioServicioImpl.obtenerTodosEjercicios(10, 1,
                "Ordenar Por", "Sort Dir");
        assertEquals(1, actualObtenerTodosEjerciciosResult.getContenido().size());
        assertTrue(actualObtenerTodosEjerciciosResult.isUltima());
        assertEquals(1L, actualObtenerTodosEjerciciosResult.getTotalPaginas());
        assertEquals(1L, actualObtenerTodosEjerciciosResult.getTotalElementos());
        assertEquals(1, actualObtenerTodosEjerciciosResult.getTamPagina());
        assertEquals(0, actualObtenerTodosEjerciciosResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    void testObtenerTodosEjercicios3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        PageImpl<Ejercicio> pageImpl = new PageImpl<>(ejercicioList);
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        EjercicioRespuesta actualObtenerTodosEjerciciosResult = ejercicioServicioImpl.obtenerTodosEjercicios(10, 1,
                "Ordenar Por", "Sort Dir");
        assertEquals(2, actualObtenerTodosEjerciciosResult.getContenido().size());
        assertTrue(actualObtenerTodosEjerciciosResult.isUltima());
        assertEquals(1L, actualObtenerTodosEjerciciosResult.getTotalPaginas());
        assertEquals(2L, actualObtenerTodosEjerciciosResult.getTotalElementos());
        assertEquals(2, actualObtenerTodosEjerciciosResult.getTamPagina());
        assertEquals(0, actualObtenerTodosEjerciciosResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerTodosEjercicios4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "ejercicios" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.obtenerTodosEjercicios(EjercicioServicioImpl.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(null);
        ejercicioServicioImpl.obtenerTodosEjercicios(10, 1, "Ordenar Por", "Sort Dir");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerTodosEjercicios5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.obtenerTodosEjercicios(EjercicioServicioImpl.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ejercicioServicioImpl.obtenerTodosEjercicios(-1, 1, "Ordenar Por", "Sort Dir");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerTodosEjercicios6() {
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
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.obtenerTodosEjercicios(EjercicioServicioImpl.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ejercicioServicioImpl.obtenerTodosEjercicios(10, 1, "", "Sort Dir");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerTodosEjercicios(int, int, String, String)}
     */
    @Test
    void testObtenerTodosEjercicios7() {
        when(ejercicioRepositorio.findAll((Pageable) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.obtenerTodosEjercicios(10, 1, "Ordenar Por", "Sort Dir"));
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerEjercicioPorId(Long)}
     */
    @Test
    void testObtenerEjercicioPorId() {
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
        EjercicioDto actualObtenerEjercicioPorIdResult = ejercicioServicioImpl.obtenerEjercicioPorId(123L);
        assertEquals("Descripcion", actualObtenerEjercicioPorIdResult.getDescripcion());
        assertEquals("janedoe", actualObtenerEjercicioPorIdResult.getUsername_creador());
        assertEquals("Nombre", actualObtenerEjercicioPorIdResult.getNombre());
        assertEquals("Imagen", actualObtenerEjercicioPorIdResult.getImagen());
        assertEquals(123L, actualObtenerEjercicioPorIdResult.getId().longValue());
        assertEquals("Grupo muscular", actualObtenerEjercicioPorIdResult.getGrupo_muscular());
        assertEquals("Dificultad", actualObtenerEjercicioPorIdResult.getDificultad());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerEjercicioPorId(Long)}
     */
    @Test
    void testObtenerEjercicioPorId2() {
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.obtenerEjercicioPorId(123L));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerEjercicioPorId(Long)}
     */
    @Test
    void testObtenerEjercicioPorId3() {
        when(ejercicioRepositorio.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Ejercicio", "Ejercicio", "Ejercicio"));
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.obtenerEjercicioPorId(123L));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#actualizarEjercicio(EjercicioDto, Long)}
     */
    @Test
    void testActualizarEjercicio() {
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

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.save((Ejercicio) any())).thenReturn(ejercicio1);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setDescripcion("Descripcion");
        ejercicioDto.setDificultad("Dificultad");
        ejercicioDto.setGrupo_muscular("Grupo muscular");
        ejercicioDto.setId(123L);
        ejercicioDto.setImagen("Imagen");
        ejercicioDto.setNombre("Nombre");
        ejercicioDto.setUsername_creador("janedoe");
        EjercicioDto actualActualizarEjercicioResult = ejercicioServicioImpl.actualizarEjercicio(ejercicioDto, 123L);
        assertEquals("Descripcion", actualActualizarEjercicioResult.getDescripcion());
        assertEquals("janedoe", actualActualizarEjercicioResult.getUsername_creador());
        assertEquals("Nombre", actualActualizarEjercicioResult.getNombre());
        assertEquals("Imagen", actualActualizarEjercicioResult.getImagen());
        assertEquals(123L, actualActualizarEjercicioResult.getId().longValue());
        assertEquals("Grupo muscular", actualActualizarEjercicioResult.getGrupo_muscular());
        assertEquals("Dificultad", actualActualizarEjercicioResult.getDificultad());
        verify(ejercicioRepositorio).save((Ejercicio) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#actualizarEjercicio(EjercicioDto, Long)}
     */
    @Test
    void testActualizarEjercicio2() {
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
        when(ejercicioRepositorio.save((Ejercicio) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);

        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setDescripcion("Descripcion");
        ejercicioDto.setDificultad("Dificultad");
        ejercicioDto.setGrupo_muscular("Grupo muscular");
        ejercicioDto.setId(123L);
        ejercicioDto.setImagen("Imagen");
        ejercicioDto.setNombre("Nombre");
        ejercicioDto.setUsername_creador("janedoe");
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.actualizarEjercicio(ejercicioDto, 123L));
        verify(ejercicioRepositorio).save((Ejercicio) any());
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#actualizarEjercicio(EjercicioDto, Long)}
     */
    @Test
    void testActualizarEjercicio3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.save((Ejercicio) any())).thenReturn(ejercicio);
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        EjercicioDto ejercicioDto = new EjercicioDto();
        ejercicioDto.setDescripcion("Descripcion");
        ejercicioDto.setDificultad("Dificultad");
        ejercicioDto.setGrupo_muscular("Grupo muscular");
        ejercicioDto.setId(123L);
        ejercicioDto.setImagen("Imagen");
        ejercicioDto.setNombre("Nombre");
        ejercicioDto.setUsername_creador("janedoe");
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.actualizarEjercicio(ejercicioDto, 123L));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#eliminarEjercicio(Long)}
     */
    @Test
    void testEliminarEjercicio() {
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
        doNothing().when(ejercicioRepositorio).delete((Ejercicio) any());
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);
        ejercicioServicioImpl.eliminarEjercicio(123L);
        verify(ejercicioRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).delete((Ejercicio) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#eliminarEjercicio(Long)}
     */
    @Test
    void testEliminarEjercicio2() {
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
        doThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo")).when(ejercicioRepositorio)
                .delete((Ejercicio) any());
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.eliminarEjercicio(123L));
        verify(ejercicioRepositorio).findById((Long) any());
        verify(ejercicioRepositorio).delete((Ejercicio) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#eliminarEjercicio(Long)}
     */
    @Test
    void testEliminarEjercicio3() {
        doNothing().when(ejercicioRepositorio).delete((Ejercicio) any());
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.eliminarEjercicio(123L));
        verify(ejercicioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at java.util.AbstractList.subListRangeCheck(AbstractList.java:505)
        //       at java.util.ArrayList.subList(ArrayList.java:1108)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:134)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Grupo muscular");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarGrupoMuscularResult = ejercicioServicioImpl.filtrarGrupoMuscular(10, 1,
                "Ordenar Por", "Sort Dir", "Grupo muscular");
        assertEquals(1, actualFiltrarGrupoMuscularResult.getContenido().size());
        assertTrue(actualFiltrarGrupoMuscularResult.isUltima());
        assertEquals(1L, actualFiltrarGrupoMuscularResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarGrupoMuscularResult.getTamPagina());
        assertEquals(0, actualFiltrarGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarGrupoMuscularResult = ejercicioServicioImpl.filtrarGrupoMuscular(10, 1,
                "Ordenar Por", "Sort Dir", "Grupo muscular");
        assertEquals(1, actualFiltrarGrupoMuscularResult.getContenido().size());
        assertTrue(actualFiltrarGrupoMuscularResult.isUltima());
        assertEquals(2L, actualFiltrarGrupoMuscularResult.getTotalPaginas());
        assertEquals(2L, actualFiltrarGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarGrupoMuscularResult.getTamPagina());
        assertEquals(1, actualFiltrarGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(-1, 1, "Ordenar Por", "Sort Dir", "Grupo muscular");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular5() {
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
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:97)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, null, "Sort Dir", "Grupo muscular");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "sortDir" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:97)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", null, "Grupo muscular");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at java.util.AbstractList.subListRangeCheck(AbstractList.java:505)
        //       at java.util.ArrayList.subList(ArrayList.java:1108)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:134)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", null);
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at java.util.AbstractList.subListRangeCheck(AbstractList.java:505)
        //       at java.util.ArrayList.subList(ArrayList.java:1108)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:134)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular9() {
        when(ejercicioRepositorio.findAll())
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Grupo muscular"));
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular10() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarGrupoMuscularResult = ejercicioServicioImpl.filtrarGrupoMuscular(0, 1,
                "Ordenar Por", "Sort Dir", "Grupo muscular");
        assertEquals(1, actualFiltrarGrupoMuscularResult.getContenido().size());
        assertTrue(actualFiltrarGrupoMuscularResult.isUltima());
        assertEquals(1L, actualFiltrarGrupoMuscularResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarGrupoMuscularResult.getTamPagina());
        assertEquals(0, actualFiltrarGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular11() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at java.util.AbstractList.subListRangeCheck(AbstractList.java:505)
        //       at java.util.ArrayList.subList(ArrayList.java:1108)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:134)
        //   See https://diff.blue/R013 to resolve this issue.

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Ejercicio");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarGrupoMuscular12() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.toLowerCase()" because the return value of "com.leandro.routineapp.entity.Ejercicio.getGrupo_muscular()" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.lambda$filtrarGrupoMuscular$4(EjercicioServicioImpl.java:110)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarGrupoMuscular(EjercicioServicioImpl.java:111)
        //   See https://diff.blue/R013 to resolve this issue.

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular(null);
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        ejercicioServicioImpl.filtrarGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Grupo muscular");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular13() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarGrupoMuscularResult = ejercicioServicioImpl.filtrarGrupoMuscular(0, 1,
                "Ordenar Por", "Sort Dir", "Grupo muscular");
        assertEquals(1, actualFiltrarGrupoMuscularResult.getContenido().size());
        assertFalse(actualFiltrarGrupoMuscularResult.isUltima());
        assertEquals(2L, actualFiltrarGrupoMuscularResult.getTotalPaginas());
        assertEquals(2L, actualFiltrarGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarGrupoMuscularResult.getTamPagina());
        assertEquals(0, actualFiltrarGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarGrupoMuscular14() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarGrupoMuscularResult = ejercicioServicioImpl.filtrarGrupoMuscular(10, 7,
                "Ordenar Por", "Sort Dir", "Grupo muscular");
        assertEquals(2, actualFiltrarGrupoMuscularResult.getContenido().size());
        assertTrue(actualFiltrarGrupoMuscularResult.isUltima());
        assertEquals(1L, actualFiltrarGrupoMuscularResult.getTotalPaginas());
        assertEquals(2L, actualFiltrarGrupoMuscularResult.getTotalElementos());
        assertEquals(7, actualFiltrarGrupoMuscularResult.getTamPagina());
        assertEquals(0, actualFiltrarGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", "Nombre");
        assertTrue(actualFiltrarPorNombreResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", "Nombre");
        assertTrue(actualFiltrarPorNombreResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("̀");
        ejercicio1.setDificultad("̀");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("̀");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("̀");
        ejercicio1.setNombre("̀");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", "Nombre");
        assertTrue(actualFiltrarPorNombreResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombre4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombre(EjercicioServicioImpl.java:154)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombre(-1, 1, "Ordenar Por", "Sort Dir", "Nombre");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombre5() {
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
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombre(EjercicioServicioImpl.java:153)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombre(10, 1, null, "Sort Dir", "Nombre");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombre6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "sortDir" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombre(EjercicioServicioImpl.java:153)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por", null, "Nombre");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre7() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", null);
        assertTrue(actualFiltrarPorNombreResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre8() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", "");
        assertTrue(actualFiltrarPorNombreResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre9() {
        when(ejercicioRepositorio.findAll()).thenThrow(new ResourceNotFoundException("̀", "̀", "̀"));
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por", "Sort Dir", "Nombre"));
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombre10() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreResult = ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por",
                "Sort Dir", "̀");
        assertEquals(1, actualFiltrarPorNombreResult.getContenido().size());
        assertTrue(actualFiltrarPorNombreResult.isUltima());
        assertEquals(1L, actualFiltrarPorNombreResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarPorNombreResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreResult.getTamPagina());
        assertEquals(0, actualFiltrarPorNombreResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombre(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombre11() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.toLowerCase()" because the return value of "com.leandro.routineapp.entity.Ejercicio.getNombre()" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.lambda$filtrarPorNombre$7(EjercicioServicioImpl.java:168)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombre(EjercicioServicioImpl.java:172)
        //   See https://diff.blue/R013 to resolve this issue.

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre(null);
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("̀");
        ejercicio1.setDificultad("̀");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("̀");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("̀");
        ejercicio1.setNombre("̀");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        ejercicioServicioImpl.filtrarPorNombre(10, 1, "Ordenar Por", "Sort Dir", "Nombre");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador() {
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por",
                "Sort Dir", "Creador");
        assertTrue(actualFiltrarPorCreadorResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(10, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findByUsernameCreador((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        PageImpl<Ejercicio> pageImpl = new PageImpl<>(ejercicioList);
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any())).thenReturn(pageImpl);
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por",
                "Sort Dir", "Creador");
        assertEquals(1, actualFiltrarPorCreadorResult.getContenido().size());
        assertFalse(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(10, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findByUsernameCreador((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("Descripcion");
        ejercicio1.setDificultad("Dificultad");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("Grupo muscular");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("Imagen");
        ejercicio1.setNombre("Nombre");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        PageImpl<Ejercicio> pageImpl = new PageImpl<>(ejercicioList);
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any())).thenReturn(pageImpl);
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por",
                "Sort Dir", "Creador");
        assertEquals(2, actualFiltrarPorCreadorResult.getContenido().size());
        assertFalse(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(2L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(10, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findByUsernameCreador((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorCreador4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "paginaEjercicios" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorCreador(EjercicioServicioImpl.java:238)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any())).thenReturn(null);
        ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por", "Sort Dir", "Creador");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorCreador5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorCreador(EjercicioServicioImpl.java:225)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ejercicioServicioImpl.filtrarPorCreador(-1, 1, "Ordenar Por", "Sort Dir", "Creador");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorCreador6() {
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
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorCreador(EjercicioServicioImpl.java:224)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ejercicioServicioImpl.filtrarPorCreador(10, 1, null, "Sort Dir", "Creador");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorCreador7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "sortDir" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorCreador(EjercicioServicioImpl.java:224)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por", null, "Creador");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador8() {
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por",
                "Sort Dir", null);
        assertTrue(actualFiltrarPorCreadorResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(10, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador9() {
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por",
                "Sort Dir", "");
        assertTrue(actualFiltrarPorCreadorResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(10, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador10() {
        when(ejercicioRepositorio.findAll((Pageable) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.filtrarPorCreador(10, 1, "Ordenar Por", "Sort Dir", null));
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorCreador(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorCreador11() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        PageImpl<Ejercicio> pageImpl = new PageImpl<>(ejercicioList);
        when(ejercicioRepositorio.findAll((Pageable) any())).thenReturn(pageImpl);
        when(ejercicioRepositorio.findByUsernameCreador((String) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        EjercicioRespuesta actualFiltrarPorCreadorResult = ejercicioServicioImpl.filtrarPorCreador(0, 1, "Ordenar Por",
                "Sort Dir", null);
        assertEquals(1, actualFiltrarPorCreadorResult.getContenido().size());
        assertTrue(actualFiltrarPorCreadorResult.isUltima());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarPorCreadorResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorCreadorResult.getTamPagina());
        assertEquals(0, actualFiltrarPorCreadorResult.getNumPagina());
        verify(ejercicioRepositorio).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro");
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro");
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("̀");
        ejercicio1.setDificultad("̀");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("̀");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("̀");
        ejercicio1.setNombre("̀");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro");
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombreYGrupoMuscular4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombreYGrupoMuscular(EjercicioServicioImpl.java:274)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(-1, 1, "Ordenar Por", "Sort Dir", "Filtro");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombreYGrupoMuscular5() {
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
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombreYGrupoMuscular(EjercicioServicioImpl.java:273)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(10, 1, null, "Sort Dir", "Filtro");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombreYGrupoMuscular6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "sortDir" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombreYGrupoMuscular(EjercicioServicioImpl.java:273)
        //   See https://diff.blue/R013 to resolve this issue.

        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", null, "Filtro");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular7() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", null);
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular8() {
        when(ejercicioRepositorio.findAll()).thenReturn(new ArrayList<>());
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "");
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.getContenido().isEmpty());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(0L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(10, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular9() {
        when(ejercicioRepositorio.findAll()).thenThrow(new ResourceNotFoundException("̀", "̀", "̀"));
        assertThrows(ResourceNotFoundException.class,
                () -> ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro"));
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    void testFiltrarPorNombreYGrupoMuscular10() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        EjercicioRespuesta actualFiltrarPorNombreYGrupoMuscularResult = ejercicioServicioImpl
                .filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "̀");
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getContenido().size());
        assertTrue(actualFiltrarPorNombreYGrupoMuscularResult.isUltima());
        assertEquals(1L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalPaginas());
        assertEquals(1L, actualFiltrarPorNombreYGrupoMuscularResult.getTotalElementos());
        assertEquals(1, actualFiltrarPorNombreYGrupoMuscularResult.getTamPagina());
        assertEquals(0, actualFiltrarPorNombreYGrupoMuscularResult.getNumPagina());
        verify(ejercicioRepositorio).findAll();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombreYGrupoMuscular11() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.toLowerCase()" because the return value of "com.leandro.routineapp.entity.Ejercicio.getGrupo_muscular()" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.lambda$filtrarPorNombreYGrupoMuscular$11(EjercicioServicioImpl.java:290)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombreYGrupoMuscular(EjercicioServicioImpl.java:291)
        //   See https://diff.blue/R013 to resolve this issue.

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular(null);
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre("̀");
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("̀");
        ejercicio1.setDificultad("̀");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("̀");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("̀");
        ejercicio1.setNombre("̀");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#filtrarPorNombreYGrupoMuscular(int, int, String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFiltrarPorNombreYGrupoMuscular12() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.toLowerCase()" because the return value of "com.leandro.routineapp.entity.Ejercicio.getNombre()" is null
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.lambda$filtrarPorNombreYGrupoMuscular$10(EjercicioServicioImpl.java:289)
        //       at java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
        //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.allMatch(ReferencePipeline.java:637)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.lambda$filtrarPorNombreYGrupoMuscular$11(EjercicioServicioImpl.java:289)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.leandro.routineapp.service.EjercicioServicioImpl.filtrarPorNombreYGrupoMuscular(EjercicioServicioImpl.java:291)
        //   See https://diff.blue/R013 to resolve this issue.

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("̀");
        ejercicio.setDificultad("̀");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("̀");
        ejercicio.setId(123L);
        ejercicio.setImagen("̀");
        ejercicio.setNombre(null);
        ejercicio.setUsernameCreador("janedoe");

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setDescripcion("̀");
        ejercicio1.setDificultad("̀");
        ejercicio1.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio1.setGrupo_muscular("̀");
        ejercicio1.setId(123L);
        ejercicio1.setImagen("̀");
        ejercicio1.setNombre("̀");
        ejercicio1.setUsernameCreador("janedoe");

        ArrayList<Ejercicio> ejercicioList = new ArrayList<>();
        ejercicioList.add(ejercicio1);
        ejercicioList.add(ejercicio);
        when(ejercicioRepositorio.findAll()).thenReturn(ejercicioList);
        ejercicioServicioImpl.filtrarPorNombreYGrupoMuscular(10, 1, "Ordenar Por", "Sort Dir", "Filtro");
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerListaNombresEjercicios()}
     */
    @Test
    void testObtenerListaNombresEjercicios() {
        ArrayList<String> stringList = new ArrayList<>();
        when(ejercicioRepositorio.findAllNombres()).thenReturn(stringList);
        List<String> actualObtenerListaNombresEjerciciosResult = ejercicioServicioImpl.obtenerListaNombresEjercicios();
        assertSame(stringList, actualObtenerListaNombresEjerciciosResult);
        assertTrue(actualObtenerListaNombresEjerciciosResult.isEmpty());
        verify(ejercicioRepositorio).findAllNombres();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#obtenerListaNombresEjercicios()}
     */
    @Test
    void testObtenerListaNombresEjercicios2() {
        when(ejercicioRepositorio.findAllNombres())
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.obtenerListaNombresEjercicios());
        verify(ejercicioRepositorio).findAllNombres();
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#buscarPorNombreExacto(String)}
     */
    @Test
    void testBuscarPorNombreExacto() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.findByNombre((String) any())).thenReturn(ejercicio);
        ResponseEntity<EjercicioDto> actualBuscarPorNombreExactoResult = ejercicioServicioImpl
                .buscarPorNombreExacto("Nombre");
        assertTrue(actualBuscarPorNombreExactoResult.hasBody());
        assertTrue(actualBuscarPorNombreExactoResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualBuscarPorNombreExactoResult.getStatusCode());
        EjercicioDto body = actualBuscarPorNombreExactoResult.getBody();
        assertEquals(123L, body.getId().longValue());
        assertEquals("Grupo muscular", body.getGrupo_muscular());
        assertEquals("Dificultad", body.getDificultad());
        assertEquals("Descripcion", body.getDescripcion());
        assertEquals("janedoe", body.getUsername_creador());
        assertEquals("Imagen", body.getImagen());
        assertEquals("Nombre", body.getNombre());
        verify(ejercicioRepositorio).findByNombre((String) any());
    }

    /**
     * Method under test: {@link EjercicioServicioImpl#buscarPorNombreExacto(String)}
     */
    @Test
    void testBuscarPorNombreExacto2() {
        when(ejercicioRepositorio.findByNombre((String) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> ejercicioServicioImpl.buscarPorNombreExacto("Nombre"));
        verify(ejercicioRepositorio).findByNombre((String) any());
    }
}

