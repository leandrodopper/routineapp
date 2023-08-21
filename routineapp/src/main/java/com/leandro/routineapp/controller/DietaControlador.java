package com.leandro.routineapp.controller;

import com.leandro.routineapp.dto.AlimentoDto;
import com.leandro.routineapp.dto.ComidaDto;
import com.leandro.routineapp.dto.DietaDto;
import com.leandro.routineapp.entity.Usuario;
import com.leandro.routineapp.jwt.JwtTokenProvider;
import com.leandro.routineapp.repository.UsuarioRepositorio;
import com.leandro.routineapp.service.DietaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietas")
public class DietaControlador {

    @Autowired
    private DietaServicio dietaServicio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("guardar")
    public DietaDto guardarDieta(@RequestBody DietaDto dietaDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Optional<Usuario> usuario = usuarioRepositorio.findByUsernameOrEmail(username,username);
        dietaDto.setCreadorId(usuario.get().getId());
        return dietaServicio.guardarDieta(dietaDto);
    }

    @PostMapping("/{dietaId}/comidas")
    public DietaDto añadirComida(@PathVariable Long dietaId, @RequestBody ComidaDto comidaDto) {
        return dietaServicio.añadirComida(dietaId, comidaDto);
    }

    @PostMapping("/{comidaId}/alimentos")
    public DietaDto añadirAlimento(@PathVariable Long comidaId, @RequestBody AlimentoDto alimentoDto) {
        return dietaServicio.añadirAlimento(comidaId, alimentoDto);
    }

    @GetMapping("/getById/{dietaId}")
    public DietaDto obtenerDietaPorId(@PathVariable(name = "dietaId") long dietaId){
        return dietaServicio.obtenerDietaPorId(dietaId);
    }

    @DeleteMapping("/deleteById/{dietaId}")
    public ResponseEntity eliminarDietaPorId(@PathVariable(name = "dietaId") long dietaId){
        return dietaServicio.eliminarDieta(dietaId);
    }

    @PutMapping("/actualizar/{dietaId}")
    public DietaDto actualizarDieta(@PathVariable Long dietaId, @RequestBody DietaDto dietaDto){
        return dietaServicio.editarDieta(dietaId,dietaDto);
    }

    @DeleteMapping("/dieta/{dietaId}/comida/{comidaId}")
    public ResponseEntity eliminarComidaDeDieta(@PathVariable Long dietaId, @PathVariable Long comidaId){
        dietaServicio.eliminarComida(dietaId,comidaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comida/{comidaId}/alimento/{alimentoId}")
    public ResponseEntity eliminarAlimentoDeComida(@PathVariable Long comidaId, @PathVariable Long alimentoId){
        dietaServicio.eliminarAlimento(comidaId,alimentoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<DietaDto> obtenerTodas(){
        return dietaServicio.obtenerTodasDietas();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DietaDto>> buscarDietasPorNombre(@RequestParam String searchTerm) {
        List<DietaDto> dietasEncontradas = dietaServicio.buscarPorNombre(searchTerm);
        return ResponseEntity.ok(dietasEncontradas);
    }
}
