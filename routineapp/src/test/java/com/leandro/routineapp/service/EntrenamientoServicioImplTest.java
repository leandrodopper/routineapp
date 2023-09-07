package com.leandro.routineapp.service;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.leandro.routineapp.dto.*;
import com.leandro.routineapp.dto.EntrenamientoDto;
import com.leandro.routineapp.dto.EntrenamientoEjercicioDto;
import com.leandro.routineapp.dto.SerieEntrenamientoDto;
import com.leandro.routineapp.entity.*;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.DiaRutina;
import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.entity.Entrenamiento;
import com.leandro.routineapp.entity.EntrenamientoEjercicio;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.SerieEntrenamiento;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.*;
import com.leandro.routineapp.repository.DiaRutinaRepositorio;
import com.leandro.routineapp.repository.EjercicioRepositorio;
import com.leandro.routineapp.repository.EntrenamientoEjercicioRepositorio;
import com.leandro.routineapp.repository.EntrenamientoRepositorio;
import com.leandro.routineapp.repository.SerieEntrenamientoRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;

import java.sql.Timestamp;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {EntrenamientoServicioImpl.class})
@ExtendWith(SpringExtension.class)
class EntrenamientoServicioImplTest {
    @MockBean
    private DiaRutinaRepositorio diaRutinaRepositorio;

    @MockBean
    private EjercicioRepositorio ejercicioRepositorio;

    @MockBean
    private RutinaRepositorio rutinaRepositorio;

    @MockBean
    private EntrenamientoEjercicioRepositorio entrenamientoEjercicioRepositorio;

    @MockBean
    private EntrenamientoRepositorio entrenamientoRepositorio;

    @Autowired
    private EntrenamientoServicioImpl entrenamientoServicioImpl;

    @MockBean
    private SerieEntrenamientoRepositorio serieEntrenamientoRepositorio;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Method under test: {@link EntrenamientoServicioImpl#guardarEntrenamiento(GuardarEntrenoDto, Long)}
     */
    @Test
    void testGuardarEntrenamiento() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

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
        entrenamiento.setDiaRutina(diaRutina1);
        entrenamiento.setDuracionMinutos(1);
        entrenamiento.setEjercicios_realizados(new ArrayList<>());
        entrenamiento.setFecha(mock(Timestamp.class));
        entrenamiento.setId(123L);
        entrenamiento.setUsuario(usuario);
        when(entrenamientoRepositorio.save((Entrenamiento) any())).thenReturn(entrenamiento);

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
        when(usuarioRepositorio.getById((Long) any())).thenReturn(usuario1);

        GuardarEntrenoDto guardarEntrenoDto = new GuardarEntrenoDto();
        guardarEntrenoDto.setDiaRutinaId(123L);
        guardarEntrenoDto.setDuracionMinutos(1);
        ArrayList<NuevoEjercicioEntrenamientoDto> nuevoEjercicioEntrenamientoDtoList = new ArrayList<>();
        guardarEntrenoDto.setEjerciciosRealizados(nuevoEjercicioEntrenamientoDtoList);
        EntrenamientoDto actualGuardarEntrenamientoResult = entrenamientoServicioImpl
                .guardarEntrenamiento(guardarEntrenoDto, 1L);
        assertEquals(123L, actualGuardarEntrenamientoResult.getDiaRutinaId().longValue());
        assertEquals(123L, actualGuardarEntrenamientoResult.getUsuarioId().longValue());
        assertNull(actualGuardarEntrenamientoResult.getId());
        assertEquals(nuevoEjercicioEntrenamientoDtoList, actualGuardarEntrenamientoResult.getEjerciciosRealizados());
        assertEquals(1, actualGuardarEntrenamientoResult.getDuracionMinutos());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(entrenamientoRepositorio).save((Entrenamiento) any());
        verify(usuarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#guardarEntrenamiento(GuardarEntrenoDto, Long)}
     */
    @Test
    void testGuardarEntrenamiento2() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

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
        entrenamiento.setDiaRutina(diaRutina1);
        entrenamiento.setDuracionMinutos(1);
        entrenamiento.setEjercicios_realizados(new ArrayList<>());
        entrenamiento.setFecha(mock(Timestamp.class));
        entrenamiento.setId(123L);
        entrenamiento.setUsuario(usuario);
        when(entrenamientoRepositorio.save((Entrenamiento) any())).thenReturn(entrenamiento);
        when(usuarioRepositorio.getById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        GuardarEntrenoDto guardarEntrenoDto = new GuardarEntrenoDto();
        guardarEntrenoDto.setDiaRutinaId(123L);
        guardarEntrenoDto.setDuracionMinutos(1);
        guardarEntrenoDto.setEjerciciosRealizados(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class,
                () -> entrenamientoServicioImpl.guardarEntrenamiento(guardarEntrenoDto, 1L));
        verify(usuarioRepositorio).getById((Long) any());
    }

    @Test
    public void testGuardarEntrenamientoConDatosValidos() {
        // Simular datos necesarios
        Usuario usuario = new Usuario();
        DiaRutina diaRutina = new DiaRutina();
        Ejercicio ejercicio = new Ejercicio();

        // Simular llamadas a repositorios
        when(usuarioRepositorio.getById(anyLong())).thenReturn(usuario);
        when(diaRutinaRepositorio.getById(anyLong())).thenReturn(diaRutina);
        when(ejercicioRepositorio.findById(anyLong())).thenReturn(Optional.of(ejercicio));

        // Simular el objeto DTO que se pasa como argumento al método
        GuardarEntrenoDto guardarEntrenoDto = new GuardarEntrenoDto();
        guardarEntrenoDto.setDiaRutinaId(1L); // ID de DiaRutina válido
        guardarEntrenoDto.setDuracionMinutos(30);
        guardarEntrenoDto.setEjerciciosRealizados(new ArrayList<>());

        // Crear ejercicios y series DTO válidos
        NuevoEjercicioEntrenamientoDto ejercicioDto = new NuevoEjercicioEntrenamientoDto();
        ejercicioDto.setEjercicioId(1L); // ID de Ejercicio válido
        ejercicioDto.setNivelEsfuerzoPercibido(8);
        ejercicioDto.setSeriesRealizadas(new ArrayList<>());

        NuevaSerieEntrenamientoDto serieDto = new NuevaSerieEntrenamientoDto();
        serieDto.setNumeroSerie(1);
        serieDto.setRepeticionesRealizadas(10);
        serieDto.setObjetivoCumplido(true);
        serieDto.setPesoUtilizado(12.5);

        ejercicioDto.getSeriesRealizadas().add(serieDto);
        guardarEntrenoDto.getEjerciciosRealizados().add(ejercicioDto);

        // Llamar al método a probar
        EntrenamientoDto resultado = entrenamientoServicioImpl.guardarEntrenamiento(guardarEntrenoDto, 1L); // 1L es un ejemplo de id_usuario

        // Verificar que los métodos de repositorio fueron llamados
        verify(usuarioRepositorio).getById(anyLong());
        verify(diaRutinaRepositorio).getById(anyLong());
        verify(ejercicioRepositorio, atLeastOnce()).findById(anyLong());

        // Verificar que los métodos de repositorio de guardado fueron llamados
        verify(entrenamientoRepositorio).save(any(Entrenamiento.class));
        verify(entrenamientoEjercicioRepositorio, atLeastOnce()).save(any(EntrenamientoEjercicio.class));
        verify(serieEntrenamientoRepositorio, atLeastOnce()).save(any(SerieEntrenamiento.class));

        // Puedes agregar más aserciones según las necesidades de tu caso
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerTiemposUsuario(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testObtenerTiemposUsuario() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //       at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //       at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //       at java.util.Objects.checkIndex(Objects.java:359)
        //       at java.util.ArrayList.get(ArrayList.java:427)
        //       at com.leandro.routineapp.service.EntrenamientoServicioImpl.obtenerTiemposUsuario(EntrenamientoServicioImpl.java:106)
        //   See https://diff.blue/R013 to resolve this issue.

        when(entrenamientoRepositorio.obtenerTiemposDeEntrenamientoPorUsuario((Long) any()))
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
        entrenamientoServicioImpl.obtenerTiemposUsuario(1L);
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerTiemposUsuario(Long)}
     */
    @Test
    void testObtenerTiemposUsuario2() {
        when(entrenamientoRepositorio.obtenerTiemposDeEntrenamientoPorUsuario((Long) any()))
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
        assertThrows(ResourceNotFoundException.class, () -> entrenamientoServicioImpl.obtenerTiemposUsuario(1L));
        verify(entrenamientoRepositorio).obtenerTiemposDeEntrenamientoPorUsuario((Long) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#obtenerTiemposUsuario(Long)}
     */
    @Test
    void testObtenerTiemposUsuario3() {
        when(entrenamientoRepositorio.obtenerTiemposDeEntrenamientoPorUsuario((Long) any()))
                .thenReturn(new ArrayList<>());
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> entrenamientoServicioImpl.obtenerTiemposUsuario(1L));
        verify(usuarioRepositorio).findById((Long) any());
    }

    @Test
    public void testObtenerTiemposUsuarioConResultados() {
        Long usuarioId = 1L;

        // Simulación de un usuario existente en la base de datos
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        when(usuarioRepositorio.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // Simulación de resultados válidos
        List<Object[]> resultados = new ArrayList<>();
        Object[] resultado = {10, 5, 7.5}; // Máximo, mínimo y promedio
        resultados.add(resultado);
        when(entrenamientoRepositorio.obtenerTiemposDeEntrenamientoPorUsuario(usuarioId)).thenReturn(resultados);

        // Llamar al método a probar
        GetTiemposDto tiempos = entrenamientoServicioImpl.obtenerTiemposUsuario(usuarioId);

        // Verificar los resultados
        assertEquals(10, tiempos.getMaximo());
        assertEquals(5, tiempos.getMinimo());
        assertEquals(7.5, tiempos.getPromedio(), 0.001); // Se utiliza un delta para comparar valores double
    }


    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamiento(EntrenamientoDto)}
     */
    @Test
    void testConvertirDtoAEntrenamiento() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

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
        when(usuarioRepositorio.getById((Long) any())).thenReturn(usuario);

        EntrenamientoDto entrenamientoDto = new EntrenamientoDto();
        entrenamientoDto.setDiaRutinaId(123L);
        entrenamientoDto.setDuracionMinutos(1);
        ArrayList<EntrenamientoEjercicioDto> entrenamientoEjercicioDtoList = new ArrayList<>();
        entrenamientoDto.setEjerciciosRealizados(entrenamientoEjercicioDtoList);
        entrenamientoDto.setFecha("Fecha");
        entrenamientoDto.setId(123L);
        entrenamientoDto.setUsuarioId(123L);
        Entrenamiento actualConvertirDtoAEntrenamientoResult = entrenamientoServicioImpl
                .convertirDtoAEntrenamiento(entrenamientoDto);
        assertSame(diaRutina, actualConvertirDtoAEntrenamientoResult.getDiaRutina());
        assertSame(usuario, actualConvertirDtoAEntrenamientoResult.getUsuario());
        assertEquals(1, actualConvertirDtoAEntrenamientoResult.getDuracionMinutos());
        assertEquals(123L, actualConvertirDtoAEntrenamientoResult.getId().longValue());
        assertEquals(entrenamientoEjercicioDtoList, actualConvertirDtoAEntrenamientoResult.getEjercicios_realizados());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(usuarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamiento(EntrenamientoDto)}
     */
    @Test
    void testConvertirDtoAEntrenamiento2() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);
        when(usuarioRepositorio.getById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        EntrenamientoDto entrenamientoDto = new EntrenamientoDto();
        entrenamientoDto.setDiaRutinaId(123L);
        entrenamientoDto.setDuracionMinutos(1);
        entrenamientoDto.setEjerciciosRealizados(new ArrayList<>());
        entrenamientoDto.setFecha("Fecha");
        entrenamientoDto.setId(123L);
        entrenamientoDto.setUsuarioId(123L);
        assertThrows(ResourceNotFoundException.class,
                () -> entrenamientoServicioImpl.convertirDtoAEntrenamiento(entrenamientoDto));
        verify(usuarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamiento(EntrenamientoDto)}
     */
    @Test
    void testConvertirDtoAEntrenamiento3() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.getById((Long) any())).thenReturn(ejercicio);

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
        entrenamiento.setDiaRutina(diaRutina1);
        entrenamiento.setDuracionMinutos(1);
        entrenamiento.setEjercicios_realizados(new ArrayList<>());
        entrenamiento.setFecha(mock(Timestamp.class));
        entrenamiento.setId(123L);
        entrenamiento.setUsuario(usuario);
        when(entrenamientoRepositorio.getById((Long) any())).thenReturn(entrenamiento);

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
        when(usuarioRepositorio.getById((Long) any())).thenReturn(usuario1);

        EntrenamientoEjercicioDto entrenamientoEjercicioDto = new EntrenamientoEjercicioDto();
        entrenamientoEjercicioDto.setEjercicioId(123L);
        entrenamientoEjercicioDto.setId(123L);
        entrenamientoEjercicioDto.setNivelEsfuerzoPercibido(1);
        entrenamientoEjercicioDto.setSeriesRealizadas(new ArrayList<>());

        ArrayList<EntrenamientoEjercicioDto> entrenamientoEjercicioDtoList = new ArrayList<>();
        entrenamientoEjercicioDtoList.add(entrenamientoEjercicioDto);

        EntrenamientoDto entrenamientoDto = new EntrenamientoDto();
        entrenamientoDto.setDiaRutinaId(123L);
        entrenamientoDto.setDuracionMinutos(1);
        entrenamientoDto.setEjerciciosRealizados(entrenamientoEjercicioDtoList);
        entrenamientoDto.setFecha("Fecha");
        entrenamientoDto.setId(123L);
        entrenamientoDto.setUsuarioId(123L);
        Entrenamiento actualConvertirDtoAEntrenamientoResult = entrenamientoServicioImpl
                .convertirDtoAEntrenamiento(entrenamientoDto);
        assertSame(diaRutina, actualConvertirDtoAEntrenamientoResult.getDiaRutina());
        assertSame(usuario1, actualConvertirDtoAEntrenamientoResult.getUsuario());
        assertEquals(1, actualConvertirDtoAEntrenamientoResult.getDuracionMinutos());
        assertEquals(123L, actualConvertirDtoAEntrenamientoResult.getId().longValue());
        List<EntrenamientoEjercicio> ejercicios_realizados = actualConvertirDtoAEntrenamientoResult
                .getEjercicios_realizados();
        assertEquals(1, ejercicios_realizados.size());
        EntrenamientoEjercicio getResult = ejercicios_realizados.get(0);
        assertSame(ejercicio, getResult.getEjercicio());
        assertSame(entrenamiento, getResult.getEntrenamiento());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(comentarioList, getResult.getSeriesRealizadas());
        assertEquals(1, getResult.getNivelEsfuerzoPercibido());
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(ejercicioRepositorio).getById((Long) any());
        verify(entrenamientoRepositorio).getById((Long) any());
        verify(usuarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamiento(EntrenamientoDto)}
     */
    @Test
    void testConvertirDtoAEntrenamiento4() {
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
        when(diaRutinaRepositorio.getById((Long) any())).thenReturn(diaRutina);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.getById((Long) any())).thenReturn(ejercicio);
        when(entrenamientoRepositorio.getById((Long) any()))
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
        when(usuarioRepositorio.getById((Long) any())).thenReturn(usuario);

        EntrenamientoEjercicioDto entrenamientoEjercicioDto = new EntrenamientoEjercicioDto();
        entrenamientoEjercicioDto.setEjercicioId(123L);
        entrenamientoEjercicioDto.setId(123L);
        entrenamientoEjercicioDto.setNivelEsfuerzoPercibido(1);
        entrenamientoEjercicioDto.setSeriesRealizadas(new ArrayList<>());

        ArrayList<EntrenamientoEjercicioDto> entrenamientoEjercicioDtoList = new ArrayList<>();
        entrenamientoEjercicioDtoList.add(entrenamientoEjercicioDto);

        EntrenamientoDto entrenamientoDto = new EntrenamientoDto();
        entrenamientoDto.setDiaRutinaId(123L);
        entrenamientoDto.setDuracionMinutos(1);
        entrenamientoDto.setEjerciciosRealizados(entrenamientoEjercicioDtoList);
        entrenamientoDto.setFecha("Fecha");
        entrenamientoDto.setId(123L);
        entrenamientoDto.setUsuarioId(123L);
        assertThrows(ResourceNotFoundException.class,
                () -> entrenamientoServicioImpl.convertirDtoAEntrenamiento(entrenamientoDto));
        verify(diaRutinaRepositorio).getById((Long) any());
        verify(entrenamientoRepositorio).getById((Long) any());
        verify(usuarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamientoEjercicio(EntrenamientoEjercicioDto)}
     */
    @Test
    void testConvertirDtoAEntrenamientoEjercicio() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.getById((Long) any())).thenReturn(ejercicio);

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
        when(entrenamientoRepositorio.getById((Long) any())).thenReturn(entrenamiento);

        EntrenamientoEjercicioDto entrenamientoEjercicioDto = new EntrenamientoEjercicioDto();
        entrenamientoEjercicioDto.setEjercicioId(123L);
        entrenamientoEjercicioDto.setId(123L);
        entrenamientoEjercicioDto.setNivelEsfuerzoPercibido(1);
        ArrayList<SerieEntrenamientoDto> serieEntrenamientoDtoList = new ArrayList<>();
        entrenamientoEjercicioDto.setSeriesRealizadas(serieEntrenamientoDtoList);
        EntrenamientoEjercicio actualConvertirDtoAEntrenamientoEjercicioResult = entrenamientoServicioImpl
                .convertirDtoAEntrenamientoEjercicio(entrenamientoEjercicioDto);
        assertSame(ejercicio, actualConvertirDtoAEntrenamientoEjercicioResult.getEjercicio());
        assertEquals(serieEntrenamientoDtoList, actualConvertirDtoAEntrenamientoEjercicioResult.getSeriesRealizadas());
        assertSame(entrenamiento, actualConvertirDtoAEntrenamientoEjercicioResult.getEntrenamiento());
        assertEquals(1, actualConvertirDtoAEntrenamientoEjercicioResult.getNivelEsfuerzoPercibido());
        assertEquals(123L, actualConvertirDtoAEntrenamientoEjercicioResult.getId().longValue());
        verify(ejercicioRepositorio).getById((Long) any());
        verify(entrenamientoRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamientoEjercicio(EntrenamientoEjercicioDto)}
     */
    @Test
    void testConvertirDtoAEntrenamientoEjercicio2() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.getById((Long) any())).thenReturn(ejercicio);
        when(entrenamientoRepositorio.getById((Long) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));

        EntrenamientoEjercicioDto entrenamientoEjercicioDto = new EntrenamientoEjercicioDto();
        entrenamientoEjercicioDto.setEjercicioId(123L);
        entrenamientoEjercicioDto.setId(123L);
        entrenamientoEjercicioDto.setNivelEsfuerzoPercibido(1);
        entrenamientoEjercicioDto.setSeriesRealizadas(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class,
                () -> entrenamientoServicioImpl.convertirDtoAEntrenamientoEjercicio(entrenamientoEjercicioDto));
        verify(entrenamientoRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoAEntrenamientoEjercicio(EntrenamientoEjercicioDto)}
     */
    @Test
    void testConvertirDtoAEntrenamientoEjercicio3() {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setDificultad("Dificultad");
        ejercicio.setEjerciciosDiaRutina(new HashSet<>());
        ejercicio.setGrupo_muscular("Grupo muscular");
        ejercicio.setId(123L);
        ejercicio.setImagen("Imagen");
        ejercicio.setNombre("Nombre");
        ejercicio.setUsernameCreador("janedoe");
        when(ejercicioRepositorio.getById((Long) any())).thenReturn(ejercicio);

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
        when(entrenamientoRepositorio.getById((Long) any())).thenReturn(entrenamiento);

        SerieEntrenamientoDto serieEntrenamientoDto = new SerieEntrenamientoDto();
        serieEntrenamientoDto.setId(123L);
        serieEntrenamientoDto.setNumeroSerie(10);
        serieEntrenamientoDto.setObjetivoCumplido(true);
        serieEntrenamientoDto.setPesoUtilizado(10.0d);
        serieEntrenamientoDto.setRepeticionesRealizadas(1);

        ArrayList<SerieEntrenamientoDto> serieEntrenamientoDtoList = new ArrayList<>();
        serieEntrenamientoDtoList.add(serieEntrenamientoDto);

        EntrenamientoEjercicioDto entrenamientoEjercicioDto = new EntrenamientoEjercicioDto();
        entrenamientoEjercicioDto.setEjercicioId(123L);
        entrenamientoEjercicioDto.setId(123L);
        entrenamientoEjercicioDto.setNivelEsfuerzoPercibido(1);
        entrenamientoEjercicioDto.setSeriesRealizadas(serieEntrenamientoDtoList);
        EntrenamientoEjercicio actualConvertirDtoAEntrenamientoEjercicioResult = entrenamientoServicioImpl
                .convertirDtoAEntrenamientoEjercicio(entrenamientoEjercicioDto);
        assertSame(ejercicio, actualConvertirDtoAEntrenamientoEjercicioResult.getEjercicio());
        assertEquals(1, actualConvertirDtoAEntrenamientoEjercicioResult.getSeriesRealizadas().size());
        assertSame(entrenamiento, actualConvertirDtoAEntrenamientoEjercicioResult.getEntrenamiento());
        assertEquals(1, actualConvertirDtoAEntrenamientoEjercicioResult.getNivelEsfuerzoPercibido());
        assertEquals(123L, actualConvertirDtoAEntrenamientoEjercicioResult.getId().longValue());
        verify(ejercicioRepositorio).getById((Long) any());
        verify(entrenamientoRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoASerieEntrenamiento(SerieEntrenamientoDto)}
     */
    @Test
    void testConvertirDtoASerieEntrenamiento() {
        SerieEntrenamientoDto serieEntrenamientoDto = new SerieEntrenamientoDto();
        serieEntrenamientoDto.setId(123L);
        serieEntrenamientoDto.setNumeroSerie(10);
        serieEntrenamientoDto.setObjetivoCumplido(true);
        serieEntrenamientoDto.setPesoUtilizado(10.0d);
        serieEntrenamientoDto.setRepeticionesRealizadas(1);
        SerieEntrenamiento actualConvertirDtoASerieEntrenamientoResult = entrenamientoServicioImpl
                .convertirDtoASerieEntrenamiento(serieEntrenamientoDto);
        assertTrue(actualConvertirDtoASerieEntrenamientoResult.isObjetivoCumplido());
        assertEquals(1, actualConvertirDtoASerieEntrenamientoResult.getRepeticionesRealizadas());
        assertEquals(10, actualConvertirDtoASerieEntrenamientoResult.getNumeroSerie());
        assertEquals(123L, actualConvertirDtoASerieEntrenamientoResult.getId().longValue());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirDtoASerieEntrenamiento(SerieEntrenamientoDto)}
     */
    @Test
    void testConvertirDtoASerieEntrenamiento2() {
        SerieEntrenamientoDto serieEntrenamientoDto = new SerieEntrenamientoDto();
        serieEntrenamientoDto.setId(123L);
        serieEntrenamientoDto.setNumeroSerie(10);
        serieEntrenamientoDto.setObjetivoCumplido(false);
        serieEntrenamientoDto.setPesoUtilizado(10.0d);
        serieEntrenamientoDto.setRepeticionesRealizadas(1);
        SerieEntrenamiento actualConvertirDtoASerieEntrenamientoResult = entrenamientoServicioImpl
                .convertirDtoASerieEntrenamiento(serieEntrenamientoDto);
        assertFalse(actualConvertirDtoASerieEntrenamientoResult.isObjetivoCumplido());
        assertEquals(1, actualConvertirDtoASerieEntrenamientoResult.getRepeticionesRealizadas());
        assertEquals(10, actualConvertirDtoASerieEntrenamientoResult.getNumeroSerie());
        assertEquals(123L, actualConvertirDtoASerieEntrenamientoResult.getId().longValue());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirEntrenamientoADto(Entrenamiento)}
     */
    @Test
    void testConvertirEntrenamientoADto() {
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
        EntrenamientoDto actualConvertirEntrenamientoADtoResult = entrenamientoServicioImpl
                .convertirEntrenamientoADto(entrenamiento);
        assertEquals(123L, actualConvertirEntrenamientoADtoResult.getDiaRutinaId().longValue());
        assertEquals(123L, actualConvertirEntrenamientoADtoResult.getUsuarioId().longValue());
        assertEquals(123L, actualConvertirEntrenamientoADtoResult.getId().longValue());
        assertEquals(comentarioList, actualConvertirEntrenamientoADtoResult.getEjerciciosRealizados());
        assertEquals(1, actualConvertirEntrenamientoADtoResult.getDuracionMinutos());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirEntrenamientoEjercicioADto(EntrenamientoEjercicio)}
     */
    @Test
    void testConvertirEntrenamientoEjercicioADto() {
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
        EntrenamientoEjercicioDto actualConvertirEntrenamientoEjercicioADtoResult = entrenamientoServicioImpl
                .convertirEntrenamientoEjercicioADto(entrenamientoEjercicio);
        assertEquals(123L, actualConvertirEntrenamientoEjercicioADtoResult.getEjercicioId().longValue());
        assertEquals(comentarioList, actualConvertirEntrenamientoEjercicioADtoResult.getSeriesRealizadas());
        assertEquals(1, actualConvertirEntrenamientoEjercicioADtoResult.getNivelEsfuerzoPercibido());
        assertEquals(123L, actualConvertirEntrenamientoEjercicioADtoResult.getId().longValue());
        assertEquals(123L, actualConvertirEntrenamientoEjercicioADtoResult.getEntrenamientoId().longValue());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirSerieEntrenamientoADto(SerieEntrenamiento)}
     */
    @Test
    void testConvertirSerieEntrenamientoADto() {
        SerieEntrenamiento serieEntrenamiento = new SerieEntrenamiento();
        serieEntrenamiento.setId(123L);
        serieEntrenamiento.setNumeroSerie(10);
        serieEntrenamiento.setObjetivoCumplido(true);
        serieEntrenamiento.setPesoUtilizado(10.0d);
        serieEntrenamiento.setRepeticionesRealizadas(1);
        SerieEntrenamientoDto actualConvertirSerieEntrenamientoADtoResult = entrenamientoServicioImpl
                .convertirSerieEntrenamientoADto(serieEntrenamiento);
        assertTrue(actualConvertirSerieEntrenamientoADtoResult.isObjetivoCumplido());
        assertEquals(1, actualConvertirSerieEntrenamientoADtoResult.getRepeticionesRealizadas());
        assertEquals(10.0d, actualConvertirSerieEntrenamientoADtoResult.getPesoUtilizado());
        assertEquals(10, actualConvertirSerieEntrenamientoADtoResult.getNumeroSerie());
        assertEquals(123L, actualConvertirSerieEntrenamientoADtoResult.getId().longValue());
    }

    /**
     * Method under test: {@link EntrenamientoServicioImpl#convertirSerieEntrenamientoADto(SerieEntrenamiento)}
     */
    @Test
    void testConvertirSerieEntrenamientoADto2() {
        SerieEntrenamiento serieEntrenamiento = new SerieEntrenamiento();
        serieEntrenamiento.setId(123L);
        serieEntrenamiento.setNumeroSerie(10);
        serieEntrenamiento.setObjetivoCumplido(false);
        serieEntrenamiento.setPesoUtilizado(10.0d);
        serieEntrenamiento.setRepeticionesRealizadas(1);
        SerieEntrenamientoDto actualConvertirSerieEntrenamientoADtoResult = entrenamientoServicioImpl
                .convertirSerieEntrenamientoADto(serieEntrenamiento);
        assertFalse(actualConvertirSerieEntrenamientoADtoResult.isObjetivoCumplido());
        assertEquals(1, actualConvertirSerieEntrenamientoADtoResult.getRepeticionesRealizadas());
        assertEquals(10.0d, actualConvertirSerieEntrenamientoADtoResult.getPesoUtilizado());
        assertEquals(10, actualConvertirSerieEntrenamientoADtoResult.getNumeroSerie());
        assertEquals(123L, actualConvertirSerieEntrenamientoADtoResult.getId().longValue());
    }
}

