package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Avaliacao;
import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.services.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/agendamentos", "/agendamento", "/reservas"})
public class AgendamentoController {
    private final AgendamentoService service;
    public AgendamentoController(AgendamentoService service){ this.service = service; }

    @GetMapping
    public List<Agendamento> listar(){ return service.listar(); }

    @GetMapping("/hospede/{hospedeId}")
    public List<Agendamento> listarPorHospede(@PathVariable Long hospedeId){ return service.listarPorHospede(hospedeId); }
    
    @PostMapping
    public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Agendamento buscar(@PathVariable String id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Agendamento o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/avaliacao")
    public Avaliacao avaliacao(@PathVariable String id){ return service.buscarAvaliacao(id); }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
