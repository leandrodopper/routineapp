package com.leandro.routineapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leandro.routineapp.dto.ComentarioDto;
import com.leandro.routineapp.dto.ComentarioRespuesta;
import com.leandro.routineapp.entity.Comentario;
import com.leandro.routineapp.entity.Rutina;
import com.leandro.routineapp.exceptions.ForbiddenException;
import com.leandro.routineapp.repository.ComentarioRepositorio;
import com.leandro.routineapp.repository.RutinaRepositorio;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ComentarioServicioImpl.class})
@ExtendWith(SpringExtension.class)
class ComentarioServicioImplTest {
    @MockBean
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private ComentarioServicioImpl comentarioServicioImpl;

    @MockBean
    private RutinaRepositorio rutinaRepositorio;

    /**
     * Method under test: {@link ComentarioServicioImpl#publicarComentario(ComentarioDto, Long, Long)}
     */
    @Test
    void testPublicarComentario() {
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

        Comentario comentario4 = new Comentario();
        comentario4.setComentarioPadre(comentario3);
        comentario4.setContenido("Contenido");
        comentario4.setFecha(mock(Timestamp.class));
        comentario4.setId(123L);
        comentario4.setRespuestas(new ArrayList<>());
        comentario4.setRutina(rutina3);
        comentario4.setUsuario("Usuario");

        Comentario comentario5 = new Comentario();
        comentario5.setComentarioPadre(new Comentario());
        comentario5.setContenido("Contenido");
        comentario5.setFecha(mock(Timestamp.class));
        comentario5.setId(123L);
        comentario5.setRespuestas(new ArrayList<>());
        comentario5.setRutina(new Rutina());
        comentario5.setUsuario("Usuario");

        Rutina rutina4 = new Rutina();
        rutina4.setComentarios(new ArrayList<>());
        rutina4.setCreador("Creador");
        rutina4.setDescripcion("Descripcion");
        rutina4.setDias_rutina(new ArrayList<>());
        rutina4.setId(123L);
        rutina4.setNombre("Nombre");
        rutina4.setNumPuntuaciones(1L);
        rutina4.setPuntuacion(10.0d);
        rutina4.setSeguidores(new ArrayList<>());

        Comentario comentario6 = new Comentario();
        comentario6.setComentarioPadre(comentario5);
        comentario6.setContenido("Contenido");
        comentario6.setFecha(mock(Timestamp.class));
        comentario6.setId(123L);
        comentario6.setRespuestas(new ArrayList<>());
        comentario6.setRutina(rutina4);
        comentario6.setUsuario("Usuario");

        Rutina rutina5 = new Rutina();
        rutina5.setComentarios(new ArrayList<>());
        rutina5.setCreador("Creador");
        rutina5.setDescripcion("Descripcion");
        rutina5.setDias_rutina(new ArrayList<>());
        rutina5.setId(123L);
        rutina5.setNombre("Nombre");
        rutina5.setNumPuntuaciones(1L);
        rutina5.setPuntuacion(10.0d);
        rutina5.setSeguidores(new ArrayList<>());

        Comentario comentario7 = new Comentario();
        comentario7.setComentarioPadre(comentario6);
        comentario7.setContenido("Contenido");
        comentario7.setFecha(mock(Timestamp.class));
        comentario7.setId(123L);
        comentario7.setRespuestas(new ArrayList<>());
        comentario7.setRutina(rutina5);
        comentario7.setUsuario("Usuario");

        Rutina rutina6 = new Rutina();
        rutina6.setComentarios(new ArrayList<>());
        rutina6.setCreador("Creador");
        rutina6.setDescripcion("Descripcion");
        rutina6.setDias_rutina(new ArrayList<>());
        rutina6.setId(123L);
        rutina6.setNombre("Nombre");
        rutina6.setNumPuntuaciones(1L);
        rutina6.setPuntuacion(10.0d);
        rutina6.setSeguidores(new ArrayList<>());

        Comentario comentario8 = new Comentario();
        comentario8.setComentarioPadre(comentario7);
        comentario8.setContenido("Contenido");
        comentario8.setFecha(mock(Timestamp.class));
        comentario8.setId(123L);
        comentario8.setRespuestas(new ArrayList<>());
        comentario8.setRutina(rutina6);
        comentario8.setUsuario("Usuario");

        Rutina rutina7 = new Rutina();
        rutina7.setComentarios(new ArrayList<>());
        rutina7.setCreador("Creador");
        rutina7.setDescripcion("Descripcion");
        rutina7.setDias_rutina(new ArrayList<>());
        rutina7.setId(123L);
        rutina7.setNombre("Nombre");
        rutina7.setNumPuntuaciones(1L);
        rutina7.setPuntuacion(10.0d);
        rutina7.setSeguidores(new ArrayList<>());

        Comentario comentario9 = new Comentario();
        comentario9.setComentarioPadre(comentario8);
        comentario9.setContenido("Contenido");
        comentario9.setFecha(mock(Timestamp.class));
        comentario9.setId(123L);
        comentario9.setRespuestas(new ArrayList<>());
        comentario9.setRutina(rutina7);
        comentario9.setUsuario("Usuario");
        when(comentarioRepositorio.getById((Long) any())).thenReturn(comentario4);
        when(comentarioRepositorio.save((Comentario) any())).thenReturn(comentario9);

        Rutina rutina8 = new Rutina();
        rutina8.setComentarios(new ArrayList<>());
        rutina8.setCreador("Creador");
        rutina8.setDescripcion("Descripcion");
        rutina8.setDias_rutina(new ArrayList<>());
        rutina8.setId(123L);
        rutina8.setNombre("Nombre");
        rutina8.setNumPuntuaciones(1L);
        rutina8.setPuntuacion(10.0d);
        rutina8.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina8);

        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setComentarioPadre_id(1L);
        comentarioDto.setContenido("Contenido");
        comentarioDto.setFecha("Fecha");
        comentarioDto.setId(123L);
        ArrayList<ComentarioDto> comentarioDtoList = new ArrayList<>();
        comentarioDto.setRespuestas(comentarioDtoList);
        comentarioDto.setRutina_id(1L);
        comentarioDto.setUsuario("Usuario");
        ComentarioDto actualPublicarComentarioResult = comentarioServicioImpl.publicarComentario(comentarioDto, 1L, 1L);
        assertEquals(123L, actualPublicarComentarioResult.getComentarioPadre_id().longValue());
        assertEquals("Usuario", actualPublicarComentarioResult.getUsuario());
        assertEquals(123L, actualPublicarComentarioResult.getRutina_id().longValue());
        assertEquals(comentarioDtoList, actualPublicarComentarioResult.getRespuestas());
        assertEquals(123L, actualPublicarComentarioResult.getId().longValue());
        assertEquals("Contenido", actualPublicarComentarioResult.getContenido());
        verify(comentarioRepositorio).getById((Long) any());
        verify(comentarioRepositorio).save((Comentario) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#publicarComentario(ComentarioDto, Long, Long)}
     */
    @Test
    void testPublicarComentario2() {
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

        Comentario comentario4 = new Comentario();
        comentario4.setComentarioPadre(comentario3);
        comentario4.setContenido("Contenido");
        comentario4.setFecha(mock(Timestamp.class));
        comentario4.setId(123L);
        comentario4.setRespuestas(new ArrayList<>());
        comentario4.setRutina(rutina3);
        comentario4.setUsuario("Usuario");

        Comentario comentario5 = new Comentario();
        comentario5.setComentarioPadre(new Comentario());
        comentario5.setContenido("Contenido");
        comentario5.setFecha(mock(Timestamp.class));
        comentario5.setId(123L);
        comentario5.setRespuestas(new ArrayList<>());
        comentario5.setRutina(new Rutina());
        comentario5.setUsuario("Usuario");

        Rutina rutina4 = new Rutina();
        rutina4.setComentarios(new ArrayList<>());
        rutina4.setCreador("Creador");
        rutina4.setDescripcion("Descripcion");
        rutina4.setDias_rutina(new ArrayList<>());
        rutina4.setId(123L);
        rutina4.setNombre("Nombre");
        rutina4.setNumPuntuaciones(1L);
        rutina4.setPuntuacion(10.0d);
        rutina4.setSeguidores(new ArrayList<>());

        Comentario comentario6 = new Comentario();
        comentario6.setComentarioPadre(comentario5);
        comentario6.setContenido("Contenido");
        comentario6.setFecha(mock(Timestamp.class));
        comentario6.setId(123L);
        comentario6.setRespuestas(new ArrayList<>());
        comentario6.setRutina(rutina4);
        comentario6.setUsuario("Usuario");

        Rutina rutina5 = new Rutina();
        rutina5.setComentarios(new ArrayList<>());
        rutina5.setCreador("Creador");
        rutina5.setDescripcion("Descripcion");
        rutina5.setDias_rutina(new ArrayList<>());
        rutina5.setId(123L);
        rutina5.setNombre("Nombre");
        rutina5.setNumPuntuaciones(1L);
        rutina5.setPuntuacion(10.0d);
        rutina5.setSeguidores(new ArrayList<>());

        Comentario comentario7 = new Comentario();
        comentario7.setComentarioPadre(comentario6);
        comentario7.setContenido("Contenido");
        comentario7.setFecha(mock(Timestamp.class));
        comentario7.setId(123L);
        comentario7.setRespuestas(new ArrayList<>());
        comentario7.setRutina(rutina5);
        comentario7.setUsuario("Usuario");

        Rutina rutina6 = new Rutina();
        rutina6.setComentarios(new ArrayList<>());
        rutina6.setCreador("Creador");
        rutina6.setDescripcion("Descripcion");
        rutina6.setDias_rutina(new ArrayList<>());
        rutina6.setId(123L);
        rutina6.setNombre("Nombre");
        rutina6.setNumPuntuaciones(1L);
        rutina6.setPuntuacion(10.0d);
        rutina6.setSeguidores(new ArrayList<>());

        Comentario comentario8 = new Comentario();
        comentario8.setComentarioPadre(comentario7);
        comentario8.setContenido("Contenido");
        comentario8.setFecha(mock(Timestamp.class));
        comentario8.setId(123L);
        comentario8.setRespuestas(new ArrayList<>());
        comentario8.setRutina(rutina6);
        comentario8.setUsuario("Usuario");

        Rutina rutina7 = new Rutina();
        rutina7.setComentarios(new ArrayList<>());
        rutina7.setCreador("Creador");
        rutina7.setDescripcion("Descripcion");
        rutina7.setDias_rutina(new ArrayList<>());
        rutina7.setId(123L);
        rutina7.setNombre("Nombre");
        rutina7.setNumPuntuaciones(1L);
        rutina7.setPuntuacion(10.0d);
        rutina7.setSeguidores(new ArrayList<>());

        Comentario comentario9 = new Comentario();
        comentario9.setComentarioPadre(comentario8);
        comentario9.setContenido("Contenido");
        comentario9.setFecha(mock(Timestamp.class));
        comentario9.setId(123L);
        comentario9.setRespuestas(new ArrayList<>());
        comentario9.setRutina(rutina7);
        comentario9.setUsuario("Usuario");
        when(comentarioRepositorio.getById((Long) any())).thenReturn(comentario4);
        when(comentarioRepositorio.save((Comentario) any())).thenReturn(comentario9);
        when(rutinaRepositorio.getById((Long) any())).thenThrow(new ForbiddenException("Mensaje"));

        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setComentarioPadre_id(1L);
        comentarioDto.setContenido("Contenido");
        comentarioDto.setFecha("Fecha");
        comentarioDto.setId(123L);
        comentarioDto.setRespuestas(new ArrayList<>());
        comentarioDto.setRutina_id(1L);
        comentarioDto.setUsuario("Usuario");
        assertThrows(ForbiddenException.class, () -> comentarioServicioImpl.publicarComentario(comentarioDto, 1L, 1L));
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    void testGetComentariosRutina() {
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(comentarioList));

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
        ComentarioRespuesta actualComentariosRutina = comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por",
                "Sort Dir", 1L);
        assertEquals(comentarioList, actualComentariosRutina.getContenido());
        assertTrue(actualComentariosRutina.isUltima());
        assertEquals(1L, actualComentariosRutina.getTotalPaginas());
        assertEquals(0L, actualComentariosRutina.getTotalElementos());
        assertEquals(0, actualComentariosRutina.getTamPagina());
        assertEquals(0, actualComentariosRutina.getNumPagina());
        verify(comentarioRepositorio).findByRutina((Rutina) any(), (Pageable) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    void testGetComentariosRutina2() {
        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(rutinaRepositorio.getById((Long) any())).thenThrow(new ForbiddenException("Mensaje"));
        assertThrows(ForbiddenException.class,
                () -> comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por", "Sort Dir", 1L));
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    void testGetComentariosRutina3() {
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
        PageImpl<Comentario> pageImpl = new PageImpl<>(comentarioList);
        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any())).thenReturn(pageImpl);

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
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina3);
        ComentarioRespuesta actualComentariosRutina = comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por",
                "Sort Dir", 1L);
        assertEquals(1, actualComentariosRutina.getContenido().size());
        assertTrue(actualComentariosRutina.isUltima());
        assertEquals(1L, actualComentariosRutina.getTotalPaginas());
        assertEquals(1L, actualComentariosRutina.getTotalElementos());
        assertEquals(1, actualComentariosRutina.getTamPagina());
        assertEquals(0, actualComentariosRutina.getNumPagina());
        verify(comentarioRepositorio).findByRutina((Rutina) any(), (Pageable) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    void testGetComentariosRutina4() {
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

        Comentario comentario4 = new Comentario();
        comentario4.setComentarioPadre(new Comentario());
        comentario4.setContenido("Contenido");
        comentario4.setFecha(mock(Timestamp.class));
        comentario4.setId(123L);
        comentario4.setRespuestas(new ArrayList<>());
        comentario4.setRutina(new Rutina());
        comentario4.setUsuario("Usuario");

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

        Comentario comentario5 = new Comentario();
        comentario5.setComentarioPadre(comentario4);
        comentario5.setContenido("Contenido");
        comentario5.setFecha(mock(Timestamp.class));
        comentario5.setId(123L);
        comentario5.setRespuestas(new ArrayList<>());
        comentario5.setRutina(rutina3);
        comentario5.setUsuario("Usuario");

        Rutina rutina4 = new Rutina();
        rutina4.setComentarios(new ArrayList<>());
        rutina4.setCreador("Creador");
        rutina4.setDescripcion("Descripcion");
        rutina4.setDias_rutina(new ArrayList<>());
        rutina4.setId(123L);
        rutina4.setNombre("Nombre");
        rutina4.setNumPuntuaciones(1L);
        rutina4.setPuntuacion(10.0d);
        rutina4.setSeguidores(new ArrayList<>());

        Comentario comentario6 = new Comentario();
        comentario6.setComentarioPadre(comentario5);
        comentario6.setContenido("Contenido");
        comentario6.setFecha(mock(Timestamp.class));
        comentario6.setId(123L);
        comentario6.setRespuestas(new ArrayList<>());
        comentario6.setRutina(rutina4);
        comentario6.setUsuario("Usuario");

        Rutina rutina5 = new Rutina();
        rutina5.setComentarios(new ArrayList<>());
        rutina5.setCreador("Creador");
        rutina5.setDescripcion("Descripcion");
        rutina5.setDias_rutina(new ArrayList<>());
        rutina5.setId(123L);
        rutina5.setNombre("Nombre");
        rutina5.setNumPuntuaciones(1L);
        rutina5.setPuntuacion(10.0d);
        rutina5.setSeguidores(new ArrayList<>());

        Comentario comentario7 = new Comentario();
        comentario7.setComentarioPadre(comentario6);
        comentario7.setContenido("Contenido");
        comentario7.setFecha(mock(Timestamp.class));
        comentario7.setId(123L);
        comentario7.setRespuestas(new ArrayList<>());
        comentario7.setRutina(rutina5);
        comentario7.setUsuario("Usuario");

        ArrayList<Comentario> comentarioList = new ArrayList<>();
        comentarioList.add(comentario7);
        comentarioList.add(comentario3);
        PageImpl<Comentario> pageImpl = new PageImpl<>(comentarioList);
        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any())).thenReturn(pageImpl);

        Rutina rutina6 = new Rutina();
        rutina6.setComentarios(new ArrayList<>());
        rutina6.setCreador("Creador");
        rutina6.setDescripcion("Descripcion");
        rutina6.setDias_rutina(new ArrayList<>());
        rutina6.setId(123L);
        rutina6.setNombre("Nombre");
        rutina6.setNumPuntuaciones(1L);
        rutina6.setPuntuacion(10.0d);
        rutina6.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina6);
        ComentarioRespuesta actualComentariosRutina = comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por",
                "Sort Dir", 1L);
        assertEquals(2, actualComentariosRutina.getContenido().size());
        assertTrue(actualComentariosRutina.isUltima());
        assertEquals(1L, actualComentariosRutina.getTotalPaginas());
        assertEquals(2L, actualComentariosRutina.getTotalElementos());
        assertEquals(2, actualComentariosRutina.getTamPagina());
        assertEquals(0, actualComentariosRutina.getNumPagina());
        verify(comentarioRepositorio).findByRutina((Rutina) any(), (Pageable) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetComentariosRutina5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "comentariosPage" is null
        //       at com.leandro.routineapp.service.ComentarioServicioImpl.getComentariosRutina(ComentarioServicioImpl.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any())).thenReturn(null);

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
        comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por", "Sort Dir", 1L);
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetComentariosRutina6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at com.leandro.routineapp.service.ComentarioServicioImpl.getComentariosRutina(ComentarioServicioImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

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
        comentarioServicioImpl.getComentariosRutina(-1, 1, "Ordenar Por", "Sort Dir", 1L);
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetComentariosRutina7() {
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
        //       at com.leandro.routineapp.service.ComentarioServicioImpl.getComentariosRutina(ComentarioServicioImpl.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

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
        comentarioServicioImpl.getComentariosRutina(10, 1, "", "Sort Dir", 1L);
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#getComentariosRutina(int, int, String, String, Long)}
     */
    @Test
    void testGetComentariosRutina8() {
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

        Comentario comentario3 = new Comentario();
        comentario3.setComentarioPadre(new Comentario());
        comentario3.setContenido("Contenido");
        comentario3.setFecha(mock(Timestamp.class));
        comentario3.setId(123L);
        comentario3.setRespuestas(new ArrayList<>());
        comentario3.setRutina(new Rutina());
        comentario3.setUsuario("Usuario");

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

        Comentario comentario4 = new Comentario();
        comentario4.setComentarioPadre(comentario3);
        comentario4.setContenido("Contenido");
        comentario4.setFecha(mock(Timestamp.class));
        comentario4.setId(123L);
        comentario4.setRespuestas(new ArrayList<>());
        comentario4.setRutina(rutina2);
        comentario4.setUsuario("Usuario");

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

        Comentario comentario5 = new Comentario();
        comentario5.setComentarioPadre(comentario4);
        comentario5.setContenido("Contenido");
        comentario5.setFecha(mock(Timestamp.class));
        comentario5.setId(123L);
        comentario5.setRespuestas(new ArrayList<>());
        comentario5.setRutina(rutina3);
        comentario5.setUsuario("Usuario");

        Rutina rutina4 = new Rutina();
        rutina4.setComentarios(new ArrayList<>());
        rutina4.setCreador("Creador");
        rutina4.setDescripcion("Descripcion");
        rutina4.setDias_rutina(new ArrayList<>());
        rutina4.setId(123L);
        rutina4.setNombre("Nombre");
        rutina4.setNumPuntuaciones(1L);
        rutina4.setPuntuacion(10.0d);
        rutina4.setSeguidores(new ArrayList<>());

        Comentario comentario6 = new Comentario();
        comentario6.setComentarioPadre(comentario5);
        comentario6.setContenido("Contenido");
        comentario6.setFecha(mock(Timestamp.class));
        comentario6.setId(123L);
        comentario6.setRespuestas(new ArrayList<>());
        comentario6.setRutina(rutina4);
        comentario6.setUsuario("Usuario");

        ArrayList<Comentario> comentarioList = new ArrayList<>();
        comentarioList.add(comentario6);

        Rutina rutina5 = new Rutina();
        rutina5.setComentarios(new ArrayList<>());
        rutina5.setCreador("Creador");
        rutina5.setDescripcion("Descripcion");
        rutina5.setDias_rutina(new ArrayList<>());
        rutina5.setId(123L);
        rutina5.setNombre("Nombre");
        rutina5.setNumPuntuaciones(1L);
        rutina5.setPuntuacion(10.0d);
        rutina5.setSeguidores(new ArrayList<>());

        Comentario comentario7 = new Comentario();
        comentario7.setComentarioPadre(comentario2);
        comentario7.setContenido("Contenido");
        comentario7.setFecha(mock(Timestamp.class));
        comentario7.setId(123L);
        comentario7.setRespuestas(comentarioList);
        comentario7.setRutina(rutina5);
        comentario7.setUsuario("Usuario");

        ArrayList<Comentario> comentarioList1 = new ArrayList<>();
        comentarioList1.add(comentario7);
        PageImpl<Comentario> pageImpl = new PageImpl<>(comentarioList1);
        when(comentarioRepositorio.findByRutina((Rutina) any(), (Pageable) any())).thenReturn(pageImpl);

        Rutina rutina6 = new Rutina();
        rutina6.setComentarios(new ArrayList<>());
        rutina6.setCreador("Creador");
        rutina6.setDescripcion("Descripcion");
        rutina6.setDias_rutina(new ArrayList<>());
        rutina6.setId(123L);
        rutina6.setNombre("Nombre");
        rutina6.setNumPuntuaciones(1L);
        rutina6.setPuntuacion(10.0d);
        rutina6.setSeguidores(new ArrayList<>());
        when(rutinaRepositorio.getById((Long) any())).thenReturn(rutina6);
        ComentarioRespuesta actualComentariosRutina = comentarioServicioImpl.getComentariosRutina(10, 1, "Ordenar Por",
                "Sort Dir", 1L);
        assertEquals(1, actualComentariosRutina.getContenido().size());
        assertTrue(actualComentariosRutina.isUltima());
        assertEquals(1L, actualComentariosRutina.getTotalPaginas());
        assertEquals(1L, actualComentariosRutina.getTotalElementos());
        assertEquals(1, actualComentariosRutina.getTamPagina());
        assertEquals(0, actualComentariosRutina.getNumPagina());
        verify(comentarioRepositorio).findByRutina((Rutina) any(), (Pageable) any());
        verify(rutinaRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#eliminarComentario(Long, String)}
     */
    @Test
    void testEliminarComentario() {
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

        Comentario comentario4 = new Comentario();
        comentario4.setComentarioPadre(comentario3);
        comentario4.setContenido("Contenido");
        comentario4.setFecha(mock(Timestamp.class));
        comentario4.setId(123L);
        comentario4.setRespuestas(new ArrayList<>());
        comentario4.setRutina(rutina3);
        comentario4.setUsuario("Usuario");
        when(comentarioRepositorio.getById((Long) any())).thenReturn(comentario4);
        assertThrows(ForbiddenException.class, () -> comentarioServicioImpl.eliminarComentario(1L, "janedoe"));
        verify(comentarioRepositorio).getById((Long) any());
    }

    /**
     * Method under test: {@link ComentarioServicioImpl#mapearDto(Comentario)}
     */
    @Test
    void testMapearDto() {
        Comentario comentario = new Comentario();
        comentario.setComentarioPadre(new Comentario());
        comentario.setContenido("Contenido");
        comentario.setFecha(mock(Timestamp.class));
        comentario.setId(123L);
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        comentario.setRespuestas(comentarioList);
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
        ComentarioDto actualMapearDtoResult = comentarioServicioImpl.mapearDto(comentario3);
        assertEquals(123L, actualMapearDtoResult.getComentarioPadre_id().longValue());
        assertEquals("Usuario", actualMapearDtoResult.getUsuario());
        assertEquals(123L, actualMapearDtoResult.getRutina_id().longValue());
        assertEquals(comentarioList, actualMapearDtoResult.getRespuestas());
        assertEquals(123L, actualMapearDtoResult.getId().longValue());
        assertEquals("Contenido", actualMapearDtoResult.getContenido());
    }
}

