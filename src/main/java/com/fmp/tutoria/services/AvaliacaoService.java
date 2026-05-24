package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Avaliacao;
import com.fmp.tutoria.repositories.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository repo;
    public AvaliacaoService(AvaliacaoRepository repo){ this.repo = repo; }

    public List<Avaliacao> listar(){ return repo.findAll(); }

    public Avaliacao salvar(Avaliacao o){ return repo.save(o); }

    public Avaliacao buscar(String id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Avaliacao nao encontrada com ID: " + id)); }

    public Avaliacao atualizar(String id, Avaliacao alteracoes) {
        Avaliacao existente = buscar(id);

        if (alteracoes.getNota() != null) {
            existente.setNota(alteracoes.getNota());
        }
        if (alteracoes.getComentario() != null) {
            existente.setComentario(alteracoes.getComentario());
        }
        if (alteracoes.getDataAvaliacao() != null) {
            existente.setDataAvaliacao(alteracoes.getDataAvaliacao());
        }
        if (alteracoes.getAgendamentoId() != null) {
            existente.setAgendamentoId(alteracoes.getAgendamentoId());
        }

        return repo.save(existente);
    }

    public void deletar(String id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Avaliacao nao encontrada com ID: " + id);
        }
        repo.deleteById(id);
    }
}
