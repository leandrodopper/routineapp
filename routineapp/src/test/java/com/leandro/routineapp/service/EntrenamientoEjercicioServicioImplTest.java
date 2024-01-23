package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.EntrenamientoEjercicioDto;
import com.leandro.routineapp.dto.SerieEntrenamientoDto;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Entrenamiento;
import com.leandro.routineapp.entity.EntrenamientoEjercicio;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.SerieEntrenamiento;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.repository.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {EntrenamientoServicioImpl.class})
@ExtendWith(SpringExtension.class)
class EntrenamientoEjercicioServicioImplTest {
    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @MockBean
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @MockBean
    private EntrenamientoRepositorio entrenamientoRepositorio;

    @MockBean
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;

    @MockBean
    private EntrenamientoEjercicioRepositorio entrenamientoEjercicioRepositorio;

    @Autowired
    private EntrenamientoServicioImpl entrenamientoEjercicioServicioImpl;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerEsfuerzosPorUsuarioYEjercicio(Long, Long)}
     */
    @Test
    void testObtenerEsfuerzosPorUsuarioYEjercicio() {
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
        ArrayList<Integer> integerList = new ArrayList<>();
        when(entrenamientoEjercicioRepositorio.obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(), (Long) any()))
                .thenReturn(integerList);

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
        List<Integer> actualObtenerEsfuerzosPorUsuarioYEjercicioResult = entrenamientoEjercicioServicioImpl
                .obtenerEsfuerzosPorUsuarioYEjercicio(1L, 1L);
        assertSame(integerList, actualObtenerEsfuerzosPorUsuarioYEjercicioResult);
        assertTrue(actualObtenerEsfuerzosPorUsuarioYEjercicioResult.isEmpty());
        verify(ejercicioRepositorio).findById((Long) any());
        verify(entrenamientoEjercicioRepositorio).obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(),
                (Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerEsfuerzosPorUsuarioYEjercicio(Long, Long)}
     */
    @Test
    void testObtenerEsfuerzosPorUsuarioYEjercicio2() {
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
        when(entrenamientoEjercicioRepositorio.obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(), (Long) any()))
                .thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

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
        assertThrows(ResponseStatusException.class,
                () -> entrenamientoEjercicioServicioImpl.obtenerEsfuerzosPorUsuarioYEjercicio(1L, 1L));
        verify(ejercicioRepositorio).findById((Long) any());
        verify(entrenamientoEjercicioRepositorio).obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(),
                (Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerEsfuerzosPorUsuarioYEjercicio(Long, Long)}
     */
    @Test
    void testObtenerEsfuerzosPorUsuarioYEjercicio3() {
        when(ejercicioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        when(entrenamientoEjercicioRepositorio.obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(), (Long) any()))
                .thenReturn(new ArrayList<>());

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
        assertThrows(ResponseStatusException.class,
                () -> entrenamientoEjercicioServicioImpl.obtenerEsfuerzosPorUsuarioYEjercicio(1L, 1L));
        verify(ejercicioRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerEsfuerzosPorUsuarioYEjercicio(Long, Long)}
     */
    @Test
    void testObtenerEsfuerzosPorUsuarioYEjercicio4() {
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
        when(entrenamientoEjercicioRepositorio.obtenerEsfuerzoPercibidoPorUsuarioYEjercicio((Long) any(), (Long) any()))
                .thenReturn(new ArrayList<>());
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,
                () -> entrenamientoEjercicioServicioImpl.obtenerEsfuerzosPorUsuarioYEjercicio(1L, 1L));
        verify(ejercicioRepositorio).findById((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertEntrenamientoEjercicioToDto(EntrenamientoEjercicio)}
     */
    @Test
    void testConvertEntrenamientoEjercicioToDto() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");

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

        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setDiaRutina(diaRutina);
        entrenamiento.setDuracionMinutos(1);
        entrenamiento.setEjercicios_realizados(new ArrayList<>());
        entrenamiento.setFecha(mock(Timestamp.class));
        entrenamiento.setId(123L);
        entrenamiento.setUsuario(usuario);

        EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
        entrenamientoEjercicio.setEjercicio(ejercicio);
        entrenamientoEjercicio.setEntrenamiento(entrenamiento);
        entrenamientoEjercicio.setId(123L);
        entrenamientoEjercicio.setNivelEsfuerzoPercibido(1);
        entrenamientoEjercicio.setSeriesRealizadas(new ArrayList<>());
        EntrenamientoEjercicioDto actualConvertEntrenamientoEjercicioToDtoResult = entrenamientoEjercicioServicioImpl
                .convertEntrenamientoEjercicioToDto(entrenamientoEjercicio);
        assertEquals(123L, actualConvertEntrenamientoEjercicioToDtoResult.getEjercicioId().longValue());
        assertEquals(comentarioList, actualConvertEntrenamientoEjercicioToDtoResult.getSeriesRealizadas());
        assertEquals(1, actualConvertEntrenamientoEjercicioToDtoResult.getNivelEsfuerzoPercibido());
        assertEquals(123L, actualConvertEntrenamientoEjercicioToDtoResult.getId().longValue());
        assertEquals(123L, actualConvertEntrenamientoEjercicioToDtoResult.getEntrenamientoId().longValue());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertSerieEntrenamientoToDto(SerieEntrenamiento)}
     */
    @Test
    public void testConvertSerieEntrenamientoToDto() {
        // Crear una serie de entrenamiento simulada
        SerieEntrenamiento serieEntrenamiento = new SerieEntrenamiento();
        serieEntrenamiento.setId(1L);
        serieEntrenamiento.setNumeroSerie(2);
        serieEntrenamiento.setPesoUtilizado(15.0);
        serieEntrenamiento.setRepeticionesRealizadas(12);
        serieEntrenamiento.setObjetivoCumplido(true);

        // Simular el entrenamientoEjercicio asociado a la serie
        EntrenamientoEjercicio entrenamientoEjercicio = new EntrenamientoEjercicio();
        entrenamientoEjercicio.setId(3L);
        serieEntrenamiento.setEntrenamientoEjercicio(entrenamientoEjercicio);

        // Llamar al método a probar
        SerieEntrenamientoDto serieEntrenamientoDto = entrenamientoEjercicioServicioImpl.convertSerieEntrenamientoToDto(serieEntrenamiento);

        // Verificar los resultados
        assertEquals(1L, serieEntrenamientoDto.getId());
        assertEquals(3L, serieEntrenamientoDto.getEntrenamientoEjercicioId());
        assertEquals(2, serieEntrenamientoDto.getNumeroSerie());
        assertEquals(15.0, serieEntrenamientoDto.getPesoUtilizado());
        assertEquals(12, serieEntrenamientoDto.getRepeticionesRealizadas());
        assertTrue(serieEntrenamientoDto.isObjetivoCumplido());
    }
}

