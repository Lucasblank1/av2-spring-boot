package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Coordenador;
import com.fmp.tutoria.services.CoordenadorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {
    private final CoordenadorService service;
    public CoordenadorController(CoordenadorService service){ this.service = service; }

    @GetMapping
    public List<Coordenador> listar(){ return service.listar(); }
    
    @PostMapping
    public Coordenador salvar(@Valid @RequestBody Coordenador o){ return service.salvar(o); }
    
    @GetMapping("/{id}")
    public Coordenador buscar(@PathVariable Long id){ return service.buscar(id); }
    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){ service.deletar(id); }
}
