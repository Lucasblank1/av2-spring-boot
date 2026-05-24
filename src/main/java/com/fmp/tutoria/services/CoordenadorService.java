package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Coordenador;
import com.fmp.tutoria.repositories.CoordenadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoordenadorService {
    private final CoordenadorRepository repo;
    public CoordenadorService(CoordenadorRepository repo){ this.repo = repo; }

    public List<Coordenador> listar(){ return repo.findAll(); }
    public Coordenador salvar(Coordenador o){ return repo.save(o); }
    public Coordenador buscar(Long id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Coordenador nao encontrado com ID: " + id)); }
    public void deletar(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Coordenador nao encontrado com ID: " + id);
        }
        repo.deleteById(id);
    }
}
