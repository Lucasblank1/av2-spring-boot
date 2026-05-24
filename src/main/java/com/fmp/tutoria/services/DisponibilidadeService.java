package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.Disponibilidade;
import com.fmp.tutoria.repositories.AgendamentoRepository;
import com.fmp.tutoria.repositories.DisponibilidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {
    private final DisponibilidadeRepository repo;
    private final AgendamentoRepository agendamentoRepository;

    public DisponibilidadeService(DisponibilidadeRepository repo, AgendamentoRepository agendamentoRepository){
        this.repo = repo;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Disponibilidade> listar(){ return repo.findAll(); }

    public Disponibilidade salvar(Disponibilidade o){ return repo.save(o); }

    public Disponibilidade buscar(Long id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Disponibilidade nao encontrada com ID: " + id)); }

    public Disponibilidade atualizar(Long id, Disponibilidade alteracoes) {
        Disponibilidade existente = buscar(id);

        if (alteracoes.getTutorId() != null) {
            existente.setTutorId(alteracoes.getTutorId());
        }
        if (alteracoes.getDiaSemana() != null) {
            existente.setDiaSemana(alteracoes.getDiaSemana());
        }
        if (alteracoes.getHorario() != null) {
            existente.setHorario(alteracoes.getHorario());
        }
        if (alteracoes.getAgenda() != null) {
            existente.setAgenda(alteracoes.getAgenda());
        }

        return repo.save(existente);
    }

    public void deletar(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Disponibilidade nao encontrada com ID: " + id);
        }
        repo.deleteById(id);
    }

    public Agendamento buscarAgendamento(Long id) {
        return agendamentoRepository.findFirstByDisponibilidadeId(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado com ID: " + id));
    }
}
