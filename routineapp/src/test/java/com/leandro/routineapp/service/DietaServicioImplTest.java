package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.AlimentoDto;
import com.leandro.routineapp.dto.ComidaDto;
import com.leandro.routineapp.dto.DietaDto;
import com.leandro.routineapp.entity.Alimento;
import com.leandro.routineapp.entity.Comida;
import com.leandro.routineapp.entity.Dieta;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.AlimentoRepositorio;
import com.leandro.routineapp.repository.ComidaRepositorio;
import com.leandro.routineapp.repository.DietaRepositorio;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DietaServicioImpl.class})
@ExtendWith(SpringExtension.class)
class DietaServicioImplTest {
    @MockBean
    private AlimentoRepositorio alimentoRepositorio;

    @MockBean
    private ComidaRepositorio comidaRepositorio;

    @MockBean
    private DietaRepositorio dietaRepositorio;

    @Autowired
    private DietaServicioImpl dietaServicioImpl;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Method under test: {@link DietaServicioImpl#guardarDieta(DietaDto)}
     */
    @Test
    void testGuardarDieta() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta);

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
        Optional<Usuario> ofResult = Optional.of(usuario1);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);

        DietaDto dietaDto = new DietaDto();
        ArrayList<ComidaDto> comidaDtoList = new ArrayList<>();
        dietaDto.setComidas(comidaDtoList);
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        DietaDto actualGuardarDietaResult = dietaServicioImpl.guardarDieta(dietaDto);
        assertEquals(comidaDtoList, actualGuardarDietaResult.getComidas());
        assertEquals("janedoe", actualGuardarDietaResult.getUsernameCreador());
        assertEquals("Nombre", actualGuardarDietaResult.getNombre());
        assertEquals(123L, actualGuardarDietaResult.getId().longValue());
        assertEquals(123L, actualGuardarDietaResult.getCreadorId().longValue());
        verify(dietaRepositorio).save((Dieta) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#guardarDieta(DietaDto)}
     */
    @Test
    void testGuardarDieta2() {
        when(dietaRepositorio.save((Dieta) any()))
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

        DietaDto dietaDto = new DietaDto();
        dietaDto.setComidas(new ArrayList<>());
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.guardarDieta(dietaDto));
        verify(dietaRepositorio).save((Dieta) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#guardarDieta(DietaDto)}
     */
    @Test
    void testGuardarDieta3() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");
        Optional<Usuario> ofResult = Optional.of(usuario2);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(ofResult);

        DietaDto dietaDto = new DietaDto();
        ArrayList<ComidaDto> comidaDtoList = new ArrayList<>();
        dietaDto.setComidas(comidaDtoList);
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        DietaDto actualGuardarDietaResult = dietaServicioImpl.guardarDieta(dietaDto);
        List<ComidaDto> comidas = actualGuardarDietaResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualGuardarDietaResult.getUsernameCreador());
        assertEquals(123L, actualGuardarDietaResult.getCreadorId().longValue());
        assertEquals("Nombre", actualGuardarDietaResult.getNombre());
        assertEquals(123L, actualGuardarDietaResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(comidaDtoList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(dietaRepositorio).save((Dieta) any());
        verify(usuarioRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#guardarDieta(DietaDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGuardarDieta4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.leandro.routineapp.service.DietaServicioImpl.guardarDieta(DietaServicioImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta);
        when(usuarioRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        DietaDto dietaDto = new DietaDto();
        dietaDto.setComidas(new ArrayList<>());
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        dietaServicioImpl.guardarDieta(dietaDto);
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerDietaPorId(Long)}
     */
    @Test
    void testObtenerDietaPorId() {
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

        Dieta dieta = new Dieta();
        ArrayList<Comida> comidaList = new ArrayList<>();
        dieta.setComidas(comidaList);
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DietaDto actualObtenerDietaPorIdResult = dietaServicioImpl.obtenerDietaPorId(123L);
        assertEquals(comidaList, actualObtenerDietaPorIdResult.getComidas());
        assertEquals("janedoe", actualObtenerDietaPorIdResult.getUsernameCreador());
        assertEquals("Nombre", actualObtenerDietaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerDietaPorIdResult.getId().longValue());
        assertEquals(123L, actualObtenerDietaPorIdResult.getCreadorId().longValue());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerDietaPorId(Long)}
     */
    @Test
    void testObtenerDietaPorId2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida);

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
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        usuario1.setRutinasSeguidas(rutinaList);
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta1);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DietaDto actualObtenerDietaPorIdResult = dietaServicioImpl.obtenerDietaPorId(123L);
        List<ComidaDto> comidas = actualObtenerDietaPorIdResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualObtenerDietaPorIdResult.getUsernameCreador());
        assertEquals(123L, actualObtenerDietaPorIdResult.getCreadorId().longValue());
        assertEquals("Nombre", actualObtenerDietaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerDietaPorIdResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(rutinaList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerDietaPorId(Long)}
     */
    @Test
    void testObtenerDietaPorId3() {
        when(dietaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.obtenerDietaPorId(123L));
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerDietaPorId(Long)}
     */
    @Test
    void testObtenerDietaPorId4() {
        when(dietaRepositorio.findById((Long) any())).thenThrow(new ResourceNotFoundException("Dieta", "Dieta", "Dieta"));
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.obtenerDietaPorId(123L));
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerDietaPorId(Long)}
     */
    @Test
    void testObtenerDietaPorId5() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");

        ArrayList<Alimento> alimentoList = new ArrayList<>();
        alimentoList.add(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(alimentoList);
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta2);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        DietaDto actualObtenerDietaPorIdResult = dietaServicioImpl.obtenerDietaPorId(123L);
        List<ComidaDto> comidas = actualObtenerDietaPorIdResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualObtenerDietaPorIdResult.getUsernameCreador());
        assertEquals(123L, actualObtenerDietaPorIdResult.getCreadorId().longValue());
        assertEquals("Nombre", actualObtenerDietaPorIdResult.getNombre());
        assertEquals(123L, actualObtenerDietaPorIdResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        List<AlimentoDto> alimentos = getResult.getAlimentos();
        assertEquals(1, alimentos.size());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getDietaId().longValue());
        assertEquals(123L, getResult.getId().longValue());
        AlimentoDto getResult1 = alimentos.get(0);
        assertEquals(10.0d, getResult1.getCantidad());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult1.getComidaId().longValue());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarDieta(Long)}
     */
    @Test
    void testEliminarDieta() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);
        doNothing().when(dietaRepositorio).deleteById((Long) any());
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity actualEliminarDietaResult = dietaServicioImpl.eliminarDieta(123L);
        assertNull(actualEliminarDietaResult.getBody());
        assertEquals(HttpStatus.OK, actualEliminarDietaResult.getStatusCode());
        assertTrue(actualEliminarDietaResult.getHeaders().isEmpty());
        verify(dietaRepositorio).findById((Long) any());
        verify(dietaRepositorio).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarDieta(Long)}
     */
    @Test
    void testEliminarDieta2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);
        doThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo")).when(dietaRepositorio)
                .deleteById((Long) any());
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarDieta(123L));
        verify(dietaRepositorio).findById((Long) any());
        verify(dietaRepositorio).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarDieta(Long)}
     */
    @Test
    void testEliminarDieta3() {
        doNothing().when(dietaRepositorio).deleteById((Long) any());
        when(dietaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarDieta(123L));
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#editarDieta(Long, DietaDto)}
     */
    @Test
    void testEditarDieta() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta1);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        DietaDto dietaDto = new DietaDto();
        ArrayList<ComidaDto> comidaDtoList = new ArrayList<>();
        dietaDto.setComidas(comidaDtoList);
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        DietaDto actualEditarDietaResult = dietaServicioImpl.editarDieta(123L, dietaDto);
        assertEquals(comidaDtoList, actualEditarDietaResult.getComidas());
        assertEquals("janedoe", actualEditarDietaResult.getUsernameCreador());
        assertEquals("Nombre", actualEditarDietaResult.getNombre());
        assertEquals(123L, actualEditarDietaResult.getId().longValue());
        assertEquals(123L, actualEditarDietaResult.getCreadorId().longValue());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#editarDieta(Long, DietaDto)}
     */
    @Test
    void testEditarDieta2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);
        when(dietaRepositorio.save((Dieta) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        DietaDto dietaDto = new DietaDto();
        dietaDto.setComidas(new ArrayList<>());
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.editarDieta(123L, dietaDto));
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#editarDieta(Long, DietaDto)}
     */
    @Test
    void testEditarDieta3() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta1);
        comida.setId(123L);
        comida.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta2);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        DietaDto dietaDto = new DietaDto();
        ArrayList<ComidaDto> comidaDtoList = new ArrayList<>();
        dietaDto.setComidas(comidaDtoList);
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        DietaDto actualEditarDietaResult = dietaServicioImpl.editarDieta(123L, dietaDto);
        List<ComidaDto> comidas = actualEditarDietaResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualEditarDietaResult.getUsernameCreador());
        assertEquals(123L, actualEditarDietaResult.getCreadorId().longValue());
        assertEquals("Nombre", actualEditarDietaResult.getNombre());
        assertEquals(123L, actualEditarDietaResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(comidaDtoList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#editarDieta(Long, DietaDto)}
     */
    @Test
    void testEditarDieta4() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta);
        when(dietaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        DietaDto dietaDto = new DietaDto();
        dietaDto.setComidas(new ArrayList<>());
        dietaDto.setCreadorId(123L);
        dietaDto.setId(123L);
        dietaDto.setNombre("Nombre");
        dietaDto.setUsernameCreador("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.editarDieta(123L, dietaDto));
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarComida(Long, Long)}
     */
    @Test
    void testEliminarComida() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida);
        doNothing().when(comidaRepositorio).deleteById((Long) any());
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        ArrayList<Comida> comidaList = new ArrayList<>();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        Optional<Dieta> ofResult1 = Optional.of(dieta1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta2);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult1);
        DietaDto actualEliminarComidaResult = dietaServicioImpl.eliminarComida(123L, 123L);
        assertEquals(comidaList, actualEliminarComidaResult.getComidas());
        assertEquals("janedoe", actualEliminarComidaResult.getUsernameCreador());
        assertEquals("Nombre", actualEliminarComidaResult.getNombre());
        assertEquals(123L, actualEliminarComidaResult.getId().longValue());
        assertEquals(123L, actualEliminarComidaResult.getCreadorId().longValue());
        verify(comidaRepositorio).findById((Long) any());
        verify(comidaRepositorio).deleteById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarComida(Long, Long)}
     */
    @Test
    void testEliminarComida2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida);
        doNothing().when(comidaRepositorio).deleteById((Long) any());
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        Optional<Dieta> ofResult1 = Optional.of(dieta1);
        when(dietaRepositorio.save((Dieta) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarComida(123L, 123L));
        verify(comidaRepositorio).findById((Long) any());
        verify(comidaRepositorio).deleteById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarComida(Long, Long)}
     */
    @Test
    void testEliminarComida3() {
        doNothing().when(comidaRepositorio).deleteById((Long) any());
        when(comidaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta1);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarComida(123L, 123L));
        verify(comidaRepositorio).findById((Long) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarComida(Long, Long)}
     */
    @Test
    void testEliminarComida4() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida);
        doNothing().when(comidaRepositorio).deleteById((Long) any());
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        usuario2.setRutinasSeguidas(rutinaList);
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        Optional<Dieta> ofResult1 = Optional.of(dieta2);

        Usuario usuario3 = new Usuario();
        usuario3.setAltura(10.0d);
        usuario3.setApellidos("Apellidos");
        usuario3.setEdad(1);
        usuario3.setEmail("jane.doe@example.org");
        usuario3.setId(123L);
        usuario3.setImagen("Imagen");
        usuario3.setNombre("Nombre");
        usuario3.setPassword("iloveyou");
        usuario3.setPeso(10.0d);
        usuario3.setRoles(new HashSet<>());
        usuario3.setRutinasSeguidas(new ArrayList<>());
        usuario3.setTelefono("Telefono");
        usuario3.setUsername("janedoe");

        Dieta dieta3 = new Dieta();
        dieta3.setComidas(new ArrayList<>());
        dieta3.setCreador(usuario3);
        dieta3.setId(123L);
        dieta3.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta3);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult1);
        DietaDto actualEliminarComidaResult = dietaServicioImpl.eliminarComida(123L, 123L);
        List<ComidaDto> comidas = actualEliminarComidaResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualEliminarComidaResult.getUsernameCreador());
        assertEquals(123L, actualEliminarComidaResult.getCreadorId().longValue());
        assertEquals("Nombre", actualEliminarComidaResult.getNombre());
        assertEquals(123L, actualEliminarComidaResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(rutinaList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(comidaRepositorio).findById((Long) any());
        verify(comidaRepositorio).deleteById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarComida(Long, Long)}
     */
    @Test
    void testEliminarComida5() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida);
        doNothing().when(comidaRepositorio).deleteById((Long) any());
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta1);
        when(dietaRepositorio.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarComida(123L, 123L));
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarAlimento(Long, Long)}
     */
    @Test
    void testEliminarAlimento() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        Optional<Alimento> ofResult = Optional.of(alimento);
        doNothing().when(alimentoRepositorio).deleteById((Long) any());
        when(alimentoRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        ArrayList<Alimento> alimentoList = new ArrayList<>();
        comida1.setAlimentos(alimentoList);
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        Optional<Comida> ofResult1 = Optional.of(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        Comida comida2 = new Comida();
        comida2.setAlimentos(new ArrayList<>());
        comida2.setDieta(dieta2);
        comida2.setId(123L);
        comida2.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida2);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult1);

        Usuario usuario3 = new Usuario();
        usuario3.setAltura(10.0d);
        usuario3.setApellidos("Apellidos");
        usuario3.setEdad(1);
        usuario3.setEmail("jane.doe@example.org");
        usuario3.setId(123L);
        usuario3.setImagen("Imagen");
        usuario3.setNombre("Nombre");
        usuario3.setPassword("iloveyou");
        usuario3.setPeso(10.0d);
        usuario3.setRoles(new HashSet<>());
        usuario3.setRutinasSeguidas(new ArrayList<>());
        usuario3.setTelefono("Telefono");
        usuario3.setUsername("janedoe");

        Dieta dieta3 = new Dieta();
        dieta3.setComidas(new ArrayList<>());
        dieta3.setCreador(usuario3);
        dieta3.setId(123L);
        dieta3.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta3);
        DietaDto actualEliminarAlimentoResult = dietaServicioImpl.eliminarAlimento(123L, 123L);
        assertEquals(alimentoList, actualEliminarAlimentoResult.getComidas());
        assertEquals("janedoe", actualEliminarAlimentoResult.getUsernameCreador());
        assertEquals("Nombre", actualEliminarAlimentoResult.getNombre());
        assertEquals(123L, actualEliminarAlimentoResult.getId().longValue());
        assertEquals(123L, actualEliminarAlimentoResult.getCreadorId().longValue());
        verify(alimentoRepositorio).findById((Long) any());
        verify(alimentoRepositorio).deleteById((Long) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarAlimento(Long, Long)}
     */
    @Test
    void testEliminarAlimento2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        Optional<Alimento> ofResult = Optional.of(alimento);
        doNothing().when(alimentoRepositorio).deleteById((Long) any());
        when(alimentoRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        Optional<Comida> ofResult1 = Optional.of(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        Comida comida2 = new Comida();
        comida2.setAlimentos(new ArrayList<>());
        comida2.setDieta(dieta2);
        comida2.setId(123L);
        comida2.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida2);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult1);
        when(dietaRepositorio.save((Dieta) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarAlimento(123L, 123L));
        verify(alimentoRepositorio).findById((Long) any());
        verify(alimentoRepositorio).deleteById((Long) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarAlimento(Long, Long)}
     */
    @Test
    void testEliminarAlimento3() {
        doNothing().when(alimentoRepositorio).deleteById((Long) any());
        when(alimentoRepositorio.findById((Long) any())).thenReturn(Optional.empty());

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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida1);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta2);
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.eliminarAlimento(123L, 123L));
        verify(alimentoRepositorio).findById((Long) any());
        verify(comidaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#eliminarAlimento(Long, Long)}
     */
    @Test
    void testEliminarAlimento4() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        Optional<Alimento> ofResult = Optional.of(alimento);
        doNothing().when(alimentoRepositorio).deleteById((Long) any());
        when(alimentoRepositorio.findById((Long) any())).thenReturn(ofResult);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        Comida comida2 = new Comida();
        ArrayList<Alimento> alimentoList = new ArrayList<>();
        comida2.setAlimentos(alimentoList);
        comida2.setDieta(dieta2);
        comida2.setId(123L);
        comida2.setNombre("Nombre");
        Optional<Comida> ofResult1 = Optional.of(comida2);

        Usuario usuario3 = new Usuario();
        usuario3.setAltura(10.0d);
        usuario3.setApellidos("Apellidos");
        usuario3.setEdad(1);
        usuario3.setEmail("jane.doe@example.org");
        usuario3.setId(123L);
        usuario3.setImagen("Imagen");
        usuario3.setNombre("Nombre");
        usuario3.setPassword("iloveyou");
        usuario3.setPeso(10.0d);
        usuario3.setRoles(new HashSet<>());
        usuario3.setRutinasSeguidas(new ArrayList<>());
        usuario3.setTelefono("Telefono");
        usuario3.setUsername("janedoe");

        Dieta dieta3 = new Dieta();
        dieta3.setComidas(new ArrayList<>());
        dieta3.setCreador(usuario3);
        dieta3.setId(123L);
        dieta3.setNombre("Nombre");

        Comida comida3 = new Comida();
        comida3.setAlimentos(new ArrayList<>());
        comida3.setDieta(dieta3);
        comida3.setId(123L);
        comida3.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida3);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult1);

        Usuario usuario4 = new Usuario();
        usuario4.setAltura(10.0d);
        usuario4.setApellidos("Apellidos");
        usuario4.setEdad(1);
        usuario4.setEmail("jane.doe@example.org");
        usuario4.setId(123L);
        usuario4.setImagen("Imagen");
        usuario4.setNombre("Nombre");
        usuario4.setPassword("iloveyou");
        usuario4.setPeso(10.0d);
        usuario4.setRoles(new HashSet<>());
        usuario4.setRutinasSeguidas(new ArrayList<>());
        usuario4.setTelefono("Telefono");
        usuario4.setUsername("janedoe");

        Dieta dieta4 = new Dieta();
        dieta4.setComidas(new ArrayList<>());
        dieta4.setCreador(usuario4);
        dieta4.setId(123L);
        dieta4.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta4);
        DietaDto actualEliminarAlimentoResult = dietaServicioImpl.eliminarAlimento(123L, 123L);
        List<ComidaDto> comidas = actualEliminarAlimentoResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualEliminarAlimentoResult.getUsernameCreador());
        assertEquals(123L, actualEliminarAlimentoResult.getCreadorId().longValue());
        assertEquals("Nombre", actualEliminarAlimentoResult.getNombre());
        assertEquals(123L, actualEliminarAlimentoResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(alimentoList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(alimentoRepositorio).findById((Long) any());
        verify(alimentoRepositorio).deleteById((Long) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
        verify(dietaRepositorio).save((Dieta) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerTodasDietas()}
     */
    @Test
    void testObtenerTodasDietas() {
        when(dietaRepositorio.findAll()).thenReturn(new ArrayList<>());
        assertTrue(dietaServicioImpl.obtenerTodasDietas().isEmpty());
        verify(dietaRepositorio).findAll();
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerTodasDietas()}
     */
    @Test
    void testObtenerTodasDietas2() {
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

        Dieta dieta = new Dieta();
        ArrayList<Comida> comidaList = new ArrayList<>();
        dieta.setComidas(comidaList);
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta);
        when(dietaRepositorio.findAll()).thenReturn(dietaList);
        List<DietaDto> actualObtenerTodasDietasResult = dietaServicioImpl.obtenerTodasDietas();
        assertEquals(1, actualObtenerTodasDietasResult.size());
        DietaDto getResult = actualObtenerTodasDietasResult.get(0);
        assertEquals(comidaList, getResult.getComidas());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getCreadorId().longValue());
        verify(dietaRepositorio).findAll();
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerTodasDietas()}
     */
    @Test
    void testObtenerTodasDietas3() {
        when(dietaRepositorio.findAll())
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.obtenerTodasDietas());
        verify(dietaRepositorio).findAll();
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerTodasDietas()}
     */
    @Test
    void testObtenerTodasDietas4() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida);

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
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        usuario1.setRutinasSeguidas(rutinaList);
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta1);
        when(dietaRepositorio.findAll()).thenReturn(dietaList);
        List<DietaDto> actualObtenerTodasDietasResult = dietaServicioImpl.obtenerTodasDietas();
        assertEquals(1, actualObtenerTodasDietasResult.size());
        DietaDto getResult = actualObtenerTodasDietasResult.get(0);
        List<ComidaDto> comidas = getResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals(123L, getResult.getCreadorId().longValue());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        ComidaDto getResult1 = comidas.get(0);
        assertEquals(rutinaList, getResult1.getAlimentos());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult1.getDietaId().longValue());
        verify(dietaRepositorio).findAll();
    }

    /**
     * Method under test: {@link DietaServicioImpl#obtenerTodasDietas()}
     */
    @Test
    void testObtenerTodasDietas5() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");

        ArrayList<Alimento> alimentoList = new ArrayList<>();
        alimentoList.add(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(alimentoList);
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta2);
        when(dietaRepositorio.findAll()).thenReturn(dietaList);
        List<DietaDto> actualObtenerTodasDietasResult = dietaServicioImpl.obtenerTodasDietas();
        assertEquals(1, actualObtenerTodasDietasResult.size());
        DietaDto getResult = actualObtenerTodasDietasResult.get(0);
        List<ComidaDto> comidas = getResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals(123L, getResult.getCreadorId().longValue());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        ComidaDto getResult1 = comidas.get(0);
        List<AlimentoDto> alimentos = getResult1.getAlimentos();
        assertEquals(1, alimentos.size());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getDietaId().longValue());
        assertEquals(123L, getResult1.getId().longValue());
        AlimentoDto getResult2 = alimentos.get(0);
        assertEquals(10.0d, getResult2.getCantidad());
        assertEquals("Nombre", getResult2.getNombre());
        assertEquals(123L, getResult2.getId().longValue());
        assertEquals(123L, getResult2.getComidaId().longValue());
        verify(dietaRepositorio).findAll();
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirAlimento(Long, AlimentoDto)}
     */
    @Test
    void testAñadirAlimento() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        when(alimentoRepositorio.save((Alimento) any())).thenReturn(alimento);

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

        Dieta dieta1 = new Dieta();
        ArrayList<Comida> comidaList = new ArrayList<>();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        Comida comida2 = new Comida();
        comida2.setAlimentos(new ArrayList<>());
        comida2.setDieta(dieta2);
        comida2.setId(123L);
        comida2.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida2);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

        AlimentoDto alimentoDto = new AlimentoDto();
        alimentoDto.setCantidad(10.0d);
        alimentoDto.setComidaId(123L);
        alimentoDto.setId(123L);
        alimentoDto.setNombre("Nombre");
        DietaDto actualAñadirAlimentoResult = dietaServicioImpl.añadirAlimento(123L, alimentoDto);
        assertEquals(comidaList, actualAñadirAlimentoResult.getComidas());
        assertEquals("janedoe", actualAñadirAlimentoResult.getUsernameCreador());
        assertEquals("Nombre", actualAñadirAlimentoResult.getNombre());
        assertEquals(123L, actualAñadirAlimentoResult.getId().longValue());
        assertEquals(123L, actualAñadirAlimentoResult.getCreadorId().longValue());
        verify(alimentoRepositorio).save((Alimento) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#buscarPorNombre(String)}
     */
    @Test
    void testBuscarPorNombre() {
        when(dietaRepositorio.buscarPorNombreSinTildesIgnoreCase((String) any())).thenReturn(new ArrayList<>());
        assertTrue(dietaServicioImpl.buscarPorNombre("Nombre").isEmpty());
        verify(dietaRepositorio).buscarPorNombreSinTildesIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#buscarPorNombre(String)}
     */
    @Test
    void testBuscarPorNombre2() {
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

        Dieta dieta = new Dieta();
        ArrayList<Comida> comidaList = new ArrayList<>();
        dieta.setComidas(comidaList);
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta);
        when(dietaRepositorio.buscarPorNombreSinTildesIgnoreCase((String) any())).thenReturn(dietaList);
        List<DietaDto> actualBuscarPorNombreResult = dietaServicioImpl.buscarPorNombre("Nombre");
        assertEquals(1, actualBuscarPorNombreResult.size());
        DietaDto getResult = actualBuscarPorNombreResult.get(0);
        assertEquals(comidaList, getResult.getComidas());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getCreadorId().longValue());
        verify(dietaRepositorio).buscarPorNombreSinTildesIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#buscarPorNombre(String)}
     */
    @Test
    void testBuscarPorNombre3() {
        when(dietaRepositorio.buscarPorNombreSinTildesIgnoreCase((String) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.buscarPorNombre("Nombre"));
        verify(dietaRepositorio).buscarPorNombreSinTildesIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#buscarPorNombre(String)}
     */
    @Test
    void testBuscarPorNombre4() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida);

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
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        usuario1.setRutinasSeguidas(rutinaList);
        usuario1.setTelefono("Telefono");
        usuario1.setUsername("janedoe");

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(comidaList);
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta1);
        when(dietaRepositorio.buscarPorNombreSinTildesIgnoreCase((String) any())).thenReturn(dietaList);
        List<DietaDto> actualBuscarPorNombreResult = dietaServicioImpl.buscarPorNombre("Nombre");
        assertEquals(1, actualBuscarPorNombreResult.size());
        DietaDto getResult = actualBuscarPorNombreResult.get(0);
        List<ComidaDto> comidas = getResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals(123L, getResult.getCreadorId().longValue());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        ComidaDto getResult1 = comidas.get(0);
        assertEquals(rutinaList, getResult1.getAlimentos());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult1.getDietaId().longValue());
        verify(dietaRepositorio).buscarPorNombreSinTildesIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#buscarPorNombre(String)}
     */
    @Test
    void testBuscarPorNombre5() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");

        ArrayList<Alimento> alimentoList = new ArrayList<>();
        alimentoList.add(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(alimentoList);
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        ArrayList<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta2);
        when(dietaRepositorio.buscarPorNombreSinTildesIgnoreCase((String) any())).thenReturn(dietaList);
        List<DietaDto> actualBuscarPorNombreResult = dietaServicioImpl.buscarPorNombre("Nombre");
        assertEquals(1, actualBuscarPorNombreResult.size());
        DietaDto getResult = actualBuscarPorNombreResult.get(0);
        List<ComidaDto> comidas = getResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", getResult.getUsernameCreador());
        assertEquals(123L, getResult.getCreadorId().longValue());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        ComidaDto getResult1 = comidas.get(0);
        List<AlimentoDto> alimentos = getResult1.getAlimentos();
        assertEquals(1, alimentos.size());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getDietaId().longValue());
        assertEquals(123L, getResult1.getId().longValue());
        AlimentoDto getResult2 = alimentos.get(0);
        assertEquals(10.0d, getResult2.getCantidad());
        assertEquals("Nombre", getResult2.getNombre());
        assertEquals(123L, getResult2.getId().longValue());
        assertEquals(123L, getResult2.getComidaId().longValue());
        verify(dietaRepositorio).buscarPorNombreSinTildesIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirAlimento(Long, AlimentoDto)}
     */
    @Test
    void testAñadirAlimento2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        when(alimentoRepositorio.save((Alimento) any())).thenReturn(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida1);
        when(comidaRepositorio.save((Comida) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

        AlimentoDto alimentoDto = new AlimentoDto();
        alimentoDto.setCantidad(10.0d);
        alimentoDto.setComidaId(123L);
        alimentoDto.setId(123L);
        alimentoDto.setNombre("Nombre");
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.añadirAlimento(123L, alimentoDto));
        verify(alimentoRepositorio).save((Alimento) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirAlimento(Long, AlimentoDto)}
     */
    @Test
    void testAñadirAlimento3() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");
        when(alimentoRepositorio.save((Alimento) any())).thenReturn(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(new ArrayList<>());
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");

        ArrayList<Comida> comidaList = new ArrayList<>();
        comidaList.add(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        ArrayList<Rutina> rutinaList = new ArrayList<>();
        usuario2.setRutinasSeguidas(rutinaList);
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(comidaList);
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");

        Comida comida2 = new Comida();
        comida2.setAlimentos(new ArrayList<>());
        comida2.setDieta(dieta2);
        comida2.setId(123L);
        comida2.setNombre("Nombre");
        Optional<Comida> ofResult = Optional.of(comida2);

        Usuario usuario3 = new Usuario();
        usuario3.setAltura(10.0d);
        usuario3.setApellidos("Apellidos");
        usuario3.setEdad(1);
        usuario3.setEmail("jane.doe@example.org");
        usuario3.setId(123L);
        usuario3.setImagen("Imagen");
        usuario3.setNombre("Nombre");
        usuario3.setPassword("iloveyou");
        usuario3.setPeso(10.0d);
        usuario3.setRoles(new HashSet<>());
        usuario3.setRutinasSeguidas(new ArrayList<>());
        usuario3.setTelefono("Telefono");
        usuario3.setUsername("janedoe");

        Dieta dieta3 = new Dieta();
        dieta3.setComidas(new ArrayList<>());
        dieta3.setCreador(usuario3);
        dieta3.setId(123L);
        dieta3.setNombre("Nombre");

        Comida comida3 = new Comida();
        comida3.setAlimentos(new ArrayList<>());
        comida3.setDieta(dieta3);
        comida3.setId(123L);
        comida3.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida3);
        when(comidaRepositorio.findById((Long) any())).thenReturn(ofResult);

        AlimentoDto alimentoDto = new AlimentoDto();
        alimentoDto.setCantidad(10.0d);
        alimentoDto.setComidaId(123L);
        alimentoDto.setId(123L);
        alimentoDto.setNombre("Nombre");
        DietaDto actualAñadirAlimentoResult = dietaServicioImpl.añadirAlimento(123L, alimentoDto);
        List<ComidaDto> comidas = actualAñadirAlimentoResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualAñadirAlimentoResult.getUsernameCreador());
        assertEquals(123L, actualAñadirAlimentoResult.getCreadorId().longValue());
        assertEquals("Nombre", actualAñadirAlimentoResult.getNombre());
        assertEquals(123L, actualAñadirAlimentoResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(rutinaList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(alimentoRepositorio).save((Alimento) any());
        verify(comidaRepositorio).save((Comida) any());
        verify(comidaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirComida(Long, ComidaDto)}
     */
    @Test
    void testAñadirComida() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta2);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        ComidaDto comidaDto = new ComidaDto();
        ArrayList<AlimentoDto> alimentoDtoList = new ArrayList<>();
        comidaDto.setAlimentos(alimentoDtoList);
        comidaDto.setDietaId(123L);
        comidaDto.setId(123L);
        comidaDto.setNombre("Nombre");
        DietaDto actualAñadirComidaResult = dietaServicioImpl.añadirComida(123L, comidaDto);
        List<ComidaDto> comidas = actualAñadirComidaResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualAñadirComidaResult.getUsernameCreador());
        assertEquals(123L, actualAñadirComidaResult.getCreadorId().longValue());
        assertEquals("Nombre", actualAñadirComidaResult.getNombre());
        assertEquals(123L, actualAñadirComidaResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        assertEquals(alimentoDtoList, getResult.getAlimentos());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(123L, getResult.getDietaId().longValue());
        verify(comidaRepositorio).save((Comida) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirComida(Long, ComidaDto)}
     */
    @Test
    void testAñadirComida2() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta1);
        when(dietaRepositorio.save((Dieta) any()))
                .thenThrow(new ResourceNotFoundException("Nombre Recurso", "Nombre Campo", "Valor Campo"));
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        ComidaDto comidaDto = new ComidaDto();
        comidaDto.setAlimentos(new ArrayList<>());
        comidaDto.setDietaId(123L);
        comidaDto.setId(123L);
        comidaDto.setNombre("Nombre");
        assertThrows(ResourceNotFoundException.class, () -> dietaServicioImpl.añadirComida(123L, comidaDto));
        verify(comidaRepositorio).save((Comida) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirComida(Long, ComidaDto)}
     */
    @Test
    void testAñadirComida3() {
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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");

        Alimento alimento = new Alimento();
        alimento.setCantidad(10.0d);
        alimento.setComida(comida);
        alimento.setId(123L);
        alimento.setNombre("Nombre");

        ArrayList<Alimento> alimentoList = new ArrayList<>();
        alimentoList.add(alimento);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");

        Comida comida1 = new Comida();
        comida1.setAlimentos(alimentoList);
        comida1.setDieta(dieta1);
        comida1.setId(123L);
        comida1.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida1);

        Usuario usuario2 = new Usuario();
        usuario2.setAltura(10.0d);
        usuario2.setApellidos("Apellidos");
        usuario2.setEdad(1);
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setImagen("Imagen");
        usuario2.setNombre("Nombre");
        usuario2.setPassword("iloveyou");
        usuario2.setPeso(10.0d);
        usuario2.setRoles(new HashSet<>());
        usuario2.setRutinasSeguidas(new ArrayList<>());
        usuario2.setTelefono("Telefono");
        usuario2.setUsername("janedoe");

        Dieta dieta2 = new Dieta();
        dieta2.setComidas(new ArrayList<>());
        dieta2.setCreador(usuario2);
        dieta2.setId(123L);
        dieta2.setNombre("Nombre");
        Optional<Dieta> ofResult = Optional.of(dieta2);

        Usuario usuario3 = new Usuario();
        usuario3.setAltura(10.0d);
        usuario3.setApellidos("Apellidos");
        usuario3.setEdad(1);
        usuario3.setEmail("jane.doe@example.org");
        usuario3.setId(123L);
        usuario3.setImagen("Imagen");
        usuario3.setNombre("Nombre");
        usuario3.setPassword("iloveyou");
        usuario3.setPeso(10.0d);
        usuario3.setRoles(new HashSet<>());
        usuario3.setRutinasSeguidas(new ArrayList<>());
        usuario3.setTelefono("Telefono");
        usuario3.setUsername("janedoe");

        Dieta dieta3 = new Dieta();
        dieta3.setComidas(new ArrayList<>());
        dieta3.setCreador(usuario3);
        dieta3.setId(123L);
        dieta3.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta3);
        when(dietaRepositorio.findById((Long) any())).thenReturn(ofResult);

        ComidaDto comidaDto = new ComidaDto();
        comidaDto.setAlimentos(new ArrayList<>());
        comidaDto.setDietaId(123L);
        comidaDto.setId(123L);
        comidaDto.setNombre("Nombre");
        DietaDto actualAñadirComidaResult = dietaServicioImpl.añadirComida(123L, comidaDto);
        List<ComidaDto> comidas = actualAñadirComidaResult.getComidas();
        assertEquals(1, comidas.size());
        assertEquals("janedoe", actualAñadirComidaResult.getUsernameCreador());
        assertEquals(123L, actualAñadirComidaResult.getCreadorId().longValue());
        assertEquals("Nombre", actualAñadirComidaResult.getNombre());
        assertEquals(123L, actualAñadirComidaResult.getId().longValue());
        ComidaDto getResult = comidas.get(0);
        List<AlimentoDto> alimentos = getResult.getAlimentos();
        assertEquals(1, alimentos.size());
        assertEquals("Nombre", getResult.getNombre());
        assertEquals(123L, getResult.getDietaId().longValue());
        assertEquals(123L, getResult.getId().longValue());
        AlimentoDto getResult1 = alimentos.get(0);
        assertEquals(10.0d, getResult1.getCantidad());
        assertEquals("Nombre", getResult1.getNombre());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult1.getComidaId().longValue());
        verify(comidaRepositorio).save((Comida) any());
        verify(dietaRepositorio).save((Dieta) any());
        verify(dietaRepositorio).findById((Long) any());
    }

    /**
     * Method under test: {@link DietaServicioImpl#añadirComida(Long, ComidaDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAñadirComida4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.leandro.routineapp.service.DietaServicioImpl.añadirComida(DietaServicioImpl.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

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

        Dieta dieta = new Dieta();
        dieta.setComidas(new ArrayList<>());
        dieta.setCreador(usuario);
        dieta.setId(123L);
        dieta.setNombre("Nombre");

        Comida comida = new Comida();
        comida.setAlimentos(new ArrayList<>());
        comida.setDieta(dieta);
        comida.setId(123L);
        comida.setNombre("Nombre");
        when(comidaRepositorio.save((Comida) any())).thenReturn(comida);

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

        Dieta dieta1 = new Dieta();
        dieta1.setComidas(new ArrayList<>());
        dieta1.setCreador(usuario1);
        dieta1.setId(123L);
        dieta1.setNombre("Nombre");
        when(dietaRepositorio.save((Dieta) any())).thenReturn(dieta1);
        when(dietaRepositorio.findById((Long) any())).thenReturn(Optional.empty());

        ComidaDto comidaDto = new ComidaDto();
        comidaDto.setAlimentos(new ArrayList<>());
        comidaDto.setDietaId(123L);
        comidaDto.setId(123L);
        comidaDto.setNombre("Nombre");
        dietaServicioImpl.añadirComida(123L, comidaDto);
    }
}

