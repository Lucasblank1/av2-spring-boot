package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.AreaConhecimento;
import com.fmp.tutoria.models.Disponibilidade;
import com.fmp.tutoria.models.Tutor;
import com.fmp.tutoria.services.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/tutores", "/tutor"})
public class TutorController {
    private final TutorService service;
    public TutorController(TutorService service){ this.service = service; }

    @GetMapping
    public List<Tutor> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<Tutor> salvar(@Valid @RequestBody Tutor o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Tutor buscar(@PathVariable Long id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Tutor o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/areas")
    public ResponseEntity<Void> vincularArea(@PathVariable Long id, @RequestBody VinculoAreaRequest request){
        service.vincularArea(id, request.areaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/disponibilidades")
    public List<Disponibilidade> disponibilidades(@PathVariable Long id){ return service.listarDisponibilidades(id); }

    @GetMapping("/{id}/agendamentos")
    public List<Agendamento> agendamentos(@PathVariable Long id){ return service.listarAgendamentos(id); }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    public static class VinculoAreaRequest {
        public Long areaId;
    }
}
