package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.Disponibilidade;
import com.fmp.tutoria.services.DisponibilidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/disponibilidades", "/disponibilidade"})
public class DisponibilidadeController {
    private final DisponibilidadeService service;
    public DisponibilidadeController(DisponibilidadeService service){ this.service = service; }

    @GetMapping
    public List<Disponibilidade> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<Disponibilidade> salvar(@Valid @RequestBody Disponibilidade o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Disponibilidade buscar(@PathVariable Long id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Disponibilidade o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/agendamento")
    public Agendamento agendamento(@PathVariable Long id){ return service.buscarAgendamento(id); }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
