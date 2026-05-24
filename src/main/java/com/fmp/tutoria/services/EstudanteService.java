package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.Estudante;
import com.fmp.tutoria.repositories.AgendamentoRepository;
import com.fmp.tutoria.repositories.EstudanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteService {
    private final EstudanteRepository repo;
    private final AgendamentoRepository agendamentoRepository;

    public EstudanteService(EstudanteRepository repo, AgendamentoRepository agendamentoRepository){
        this.repo = repo;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Estudante> listar(){ return repo.findAll(); }

    public Estudante salvar(Estudante o){ return repo.save(o); }

    public Estudante buscar(Long id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudante nao encontrado com ID: " + id)); }

    public Estudante atualizar(Long id, Estudante alteracoes) {
        Estudante existente = buscar(id);

        if (alteracoes.getMatricula() != null) {
            existente.setMatricula(alteracoes.getMatricula());
        }
        if (alteracoes.getCurso() != null) {
            existente.setCurso(alteracoes.getCurso());
        }
        if (alteracoes.getUsuarioId() != null) {
            existente.setUsuarioId(alteracoes.getUsuarioId());
        }

        return repo.save(existente);
    }

    public void deletar(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Estudante nao encontrado com ID: " + id);
        }
        repo.deleteById(id);
    }

    public List<Agendamento> listarAgendamentos(Long estudanteId) {
        return agendamentoRepository.findByEstudanteId(estudanteId);
    }
}
