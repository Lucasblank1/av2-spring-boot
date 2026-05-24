package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.AreaConhecimento;
import com.fmp.tutoria.services.AreaConhecimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/areas-conhecimento", "/areaconhecimento"})
public class AreaConhecimentoController {
    private final AreaConhecimentoService service;
    public AreaConhecimentoController(AreaConhecimentoService service){ this.service = service; }

    @GetMapping
    public List<AreaConhecimento> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<AreaConhecimento> salvar(@Valid @RequestBody AreaConhecimento o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public AreaConhecimento buscar(@PathVariable Long id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AreaConhecimento o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
