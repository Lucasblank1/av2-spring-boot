package com.fmp.tutoria.controllers;

import com.fmp.tutoria.models.Usuario;
import com.fmp.tutoria.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/usuarios", "/usuario"})
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service){ this.service = service; }

    @GetMapping
    public List<Usuario> listar(){ return service.listar(); }
    
    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario o){ return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(o)); }
    
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable String id){ return service.buscar(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Usuario o){
        service.atualizar(id, o);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
