package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Avaliacao;
import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.repositories.AvaliacaoRepository;
import com.fmp.tutoria.repositories.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    private final AgendamentoRepository repo;
    private final AvaliacaoRepository avaliacaoRepository;

    public AgendamentoService(AgendamentoRepository repo, AvaliacaoRepository avaliacaoRepository){
        this.repo = repo;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<Agendamento> listar(){ return repo.findAll(); }

    public List<Agendamento> listarPorHospede(Long hospedeId){ return repo.findByEstudanteId(hospedeId); }

    public Agendamento salvar(Agendamento o){ return repo.save(o); }

    public Agendamento buscar(String id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado com ID: " + id)); }

    public Agendamento atualizar(String id, Agendamento alteracoes) {
        Agendamento existente = buscar(id);

        if (alteracoes.getStatus() != null) {
            existente.setStatus(alteracoes.getStatus());
        }
        if (alteracoes.getDataHora() != null) {
            existente.setDataHora(alteracoes.getDataHora());
        }
        if (alteracoes.getDisponibilidadeId() != null) {
            existente.setDisponibilidadeId(alteracoes.getDisponibilidadeId());
        }
        if (alteracoes.getEstudanteId() != null) {
            existente.setEstudanteId(alteracoes.getEstudanteId());
        }
        if (alteracoes.getTutorId() != null) {
            existente.setTutorId(alteracoes.getTutorId());
        }

        return repo.save(existente);
    }

    public void deletar(String id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Agendamento nao encontrado com ID: " + id);
        }
        repo.deleteById(id);
    }

    public Avaliacao buscarAvaliacao(String id) {
        return avaliacaoRepository.findByAgendamentoId(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao nao encontrada com ID: " + id));
    }
}
