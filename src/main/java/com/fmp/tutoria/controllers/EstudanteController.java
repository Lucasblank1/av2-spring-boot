package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.Estudante;
import com.fmp.tutoria.services.EstudanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/estudantes", "/estudante"})
public class EstudanteController {
    private final EstudanteService service;
    public EstudanteController(EstudanteService service){ this.service = service; }

    @GetMapping
    public List<Estudante> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<Estudante> salvar(@Valid @RequestBody Estudante o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Estudante buscar(@PathVariable Long id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Estudante o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/agendamentos")
    public List<Agendamento> agendamentos(@PathVariable Long id){ return service.listarAgendamentos(id); }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
