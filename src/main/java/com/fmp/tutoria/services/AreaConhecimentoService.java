package com.fmp.tutoria.services;

import com.fmp.tutoria.models.AreaConhecimento;
import com.fmp.tutoria.repositories.AreaConhecimentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaConhecimentoService {
    private final AreaConhecimentoRepository repo;
    public AreaConhecimentoService(AreaConhecimentoRepository repo){ this.repo = repo; }

    public List<AreaConhecimento> listar(){ return repo.findAll(); }
    public AreaConhecimento salvar(AreaConhecimento o){ return repo.save(o); }
    public AreaConhecimento buscar(Long id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Area nao encontrada com ID: " + id)); }

    public AreaConhecimento atualizar(Long id, AreaConhecimento alteracoes) {
        AreaConhecimento existente = buscar(id);

        if (alteracoes.getNome() != null) {
            existente.setNome(alteracoes.getNome());
        }
        if (alteracoes.getDescricao() != null) {
            existente.setDescricao(alteracoes.getDescricao());
        }

        return repo.save(existente);
    }

    public void deletar(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Area nao encontrada com ID: " + id);
        }
        repo.deleteById(id);
    }
}
