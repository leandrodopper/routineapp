package com.leandro.routineapp.service;

import com.leandro.routineapp.dto.AlimentoDto;
import com.leandro.routineapp.dto.ComidaDto;
import com.leandro.routineapp.dto.DietaDto;
import com.leandro.routineapp.entity.Alimento;
import com.leandro.routineapp.entity.Comida;
import com.leandro.routineapp.entity.Dieta;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.exceptions.ResourceNotFoundException;
import com.leandro.routineapp.repository.AlimentoRepositorio;
import com.leandro.routineapp.repository.ComidaRepositorio;
import com.leandro.routineapp.repository.DietaRepositorio;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DietaServicioImpl implements DietaServicio{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DietaRepositorio dietaRepositorio;
    @Autowired
    private ComidaRepositorio comidaRepositorio;

    @Autowired
    private AlimentoRepositorio alimentoRepositorio;

    @Override
    public DietaDto guardarDieta(DietaDto dietaDto) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(dietaDto.getCreadorId());
        List<Comida> comidas=new ArrayList<>();
        Dieta dieta=new Dieta();
        dieta.setNombre(dietaDto.getNombre());
        dieta.setCreador(usuario.get());
        dieta.setComidas(comidas);
        Dieta dieta_added = dietaRepositorio.save(dieta);
        return toDto(dieta_added);
    }

    @Override
    public DietaDto añadirComida(Long dietaId, ComidaDto comidaDto) {
        Dieta dieta = dietaRepositorio.findById(dietaId).get();
        List<Alimento> alimentos=new ArrayList<>();
        Comida nuevaComida = new Comida();
        nuevaComida.setNombre(comidaDto.getNombre());
        nuevaComida.setDieta(dieta);
        nuevaComida.setAlimentos(alimentos);

        Comida comidaAñadida = comidaRepositorio.save(nuevaComida);

        dieta.getComidas().add(comidaAñadida);
        dietaRepositorio.save(dieta);

        return toDto(dieta);
    }


    @Override
    public DietaDto añadirAlimento(Long comidaId, AlimentoDto alimentoDto) {
        Comida comida = comidaRepositorio.findById(comidaId).get();

        Alimento nuevoAlimento = new Alimento();
        nuevoAlimento.setNombre(alimentoDto.getNombre());
        nuevoAlimento.setCantidad(alimentoDto.getCantidad());
        nuevoAlimento.setComida(comida);

        Alimento alimentoAñadido = alimentoRepositorio.save(nuevoAlimento);

        comida.getAlimentos().add(alimentoAñadido);
        comidaRepositorio.save(comida);

        return toDto(comida.getDieta());
    }

    @Override
    public DietaDto obtenerDietaPorId(Long dietaId) {
        Optional<Dieta> dieta = dietaRepositorio.findById(dietaId);

        if (!dieta.isPresent()) {
            throw new ResourceNotFoundException("Dieta", "id", dietaId.toString());
        }

        return toDto(dieta.get());
    }

    @Override
    public ResponseEntity eliminarDieta(Long dietaId) {
        Optional<Dieta> dieta = dietaRepositorio.findById(dietaId);

        if (!dieta.isPresent()) {
            throw new ResourceNotFoundException("Dieta", "id", dietaId.toString());
        }

        dietaRepositorio.deleteById(dietaId);

        return ResponseEntity.ok().build();
    }

    @Override
    public DietaDto editarDieta(Long dietaId, DietaDto dietaDto) {
        Optional<Dieta> dieta = dietaRepositorio.findById(dietaId);

        if (!dieta.isPresent()) {
            throw new ResourceNotFoundException("Dieta", "id", dietaId.toString());
        }

        dieta.get().setNombre(dietaDto.getNombre());
        Dieta dieta_actualizada= dietaRepositorio.save(dieta.get());
        return toDto(dieta_actualizada);
    }

    @Override
    public DietaDto eliminarComida(Long dietaId, Long comidaId) {
        Dieta dieta = dietaRepositorio.findById(dietaId).orElseThrow(() -> new ResourceNotFoundException("Dieta", "id", dietaId.toString()));

        Comida comida = comidaRepositorio.findById(comidaId).orElseThrow(() -> new ResourceNotFoundException("Comida", "id", comidaId.toString()));

        dieta.getComidas().remove(comida);
        comidaRepositorio.deleteById(comidaId);

        dietaRepositorio.save(dieta);

        return toDto(dieta);    }

    @Override
    public DietaDto eliminarAlimento(Long comidaId, Long alimentoId) {
        Comida comida = comidaRepositorio.findById(comidaId).orElseThrow(() -> new ResourceNotFoundException("Comida", "id", comidaId.toString()));

        Alimento alimento = alimentoRepositorio.findById(alimentoId).orElseThrow(() -> new ResourceNotFoundException("Alimento", "id", alimentoId.toString()));

        comida.getAlimentos().remove(alimento);
        alimentoRepositorio.deleteById(alimentoId);

        comidaRepositorio.save(comida);

        Dieta dieta = comida.getDieta();
        dietaRepositorio.save(dieta);

        return toDto(dieta);
    }

    @Override
    public List<DietaDto> obtenerTodasDietas() {
        List<Dieta> dietas_bd = dietaRepositorio.findAll();
        List<DietaDto> respuesta = new ArrayList<>();
        for(Dieta dieta:dietas_bd){
            respuesta.add(toDto(dieta));
        }
        return respuesta;
    }

    @Override
    public List<DietaDto> buscarPorNombre(String nombre) {
        List<Dieta> dietas = dietaRepositorio.buscarPorNombreSinTildesIgnoreCase(nombre);
        List<DietaDto> respuesta = new ArrayList<>();
        for(Dieta dieta:dietas){
            respuesta.add(toDto(dieta));
        }
        return respuesta;
    }

    private DietaDto toDto(Dieta dieta) {
        DietaDto dietaDto = new DietaDto();
        dietaDto.setId(dieta.getId());
        dietaDto.setNombre(dieta.getNombre());
        dietaDto.setCreadorId(dieta.getCreador().getId());
        dietaDto.setUsernameCreador(dieta.getCreador().getUsername());

        List<ComidaDto> comidasDto = new ArrayList<>();
        for (Comida comida : dieta.getComidas()) {
            ComidaDto comidaDto = new ComidaDto();
            comidaDto.setId(comida.getId());
            comidaDto.setNombre(comida.getNombre());
            comidaDto.setDietaId(comida.getDieta().getId());

            List<AlimentoDto> alimentosDto = new ArrayList<>();
            for (Alimento alimento : comida.getAlimentos()) {
                AlimentoDto alimentoDto = new AlimentoDto();
                alimentoDto.setId(alimento.getId());
                alimentoDto.setNombre(alimento.getNombre());
                alimentoDto.setCantidad(alimento.getCantidad());
                alimentoDto.setComidaId(alimento.getComida().getId());
                alimentosDto.add(alimentoDto);
            }
            comidaDto.setAlimentos(alimentosDto);

            comidasDto.add(comidaDto);
        }
        dietaDto.setComidas(comidasDto);

        return dietaDto;
    }
}
