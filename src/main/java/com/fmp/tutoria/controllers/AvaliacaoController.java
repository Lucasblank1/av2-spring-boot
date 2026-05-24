package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Avaliacao;
import com.fmp.tutoria.services.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/avaliacoes", "/avaliacao"})
public class AvaliacaoController {
    private final AvaliacaoService service;
    public AvaliacaoController(AvaliacaoService service){ this.service = service; }

    @GetMapping
    public List<Avaliacao> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<Avaliacao> salvar(@Valid @RequestBody Avaliacao o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Avaliacao buscar(@PathVariable String id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Avaliacao o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
