package com.fmp.tutoria.services;

import com.fmp.tutoria.models.Agendamento;
import com.fmp.tutoria.models.AreaConhecimento;
import com.fmp.tutoria.models.Disponibilidade;
import com.fmp.tutoria.models.Tutor;
import com.fmp.tutoria.repositories.AgendamentoRepository;
import com.fmp.tutoria.repositories.AreaConhecimentoRepository;
import com.fmp.tutoria.repositories.DisponibilidadeRepository;
import com.fmp.tutoria.repositories.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {
    private final TutorRepository repo;
    private final AreaConhecimentoRepository areaRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final AgendamentoRepository agendamentoRepository;

    public TutorService(TutorRepository repo, AreaConhecimentoRepository areaRepository, DisponibilidadeRepository disponibilidadeRepository, AgendamentoRepository agendamentoRepository){
        this.repo = repo;
        this.areaRepository = areaRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Tutor> listar(){ return repo.findAll(); }

    public Tutor salvar(Tutor o){
        if (o.getAprovado() == null) {
            o.setAprovado(Boolean.FALSE);
        }
        if (o.getAreas() == null) {
            o.setAreas(new ArrayList<>());
        }
        return repo.save(o);
    }

    public Tutor buscar(Long id){ return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Tutor nao encontrado com ID: " + id)); }

    public Tutor atualizar(Long id, Tutor alteracoes) {
        Tutor existente = buscar(id);

        if (alteracoes.getCurriculo() != null) {
            existente.setCurriculo(alteracoes.getCurriculo());
        }
        if (alteracoes.getEspecialidade() != null) {
            existente.setEspecialidade(alteracoes.getEspecialidade());
        }
        if (alteracoes.getUsuarioId() != null) {
            existente.setUsuarioId(alteracoes.getUsuarioId());
        }
        if (alteracoes.getAprovado() != null) {
            existente.setAprovado(alteracoes.getAprovado());
        }

        return repo.save(existente);
    }

    public void deletar(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Tutor nao encontrado com ID: " + id);
        }
        repo.deleteById(id);
    }

    public Tutor vincularArea(Long tutorId, Long areaId) {
        Tutor tutor = buscar(tutorId);
        AreaConhecimento area = areaRepository.findById(areaId)
                .orElseThrow(() -> new EntityNotFoundException("Area nao encontrada com ID: " + areaId));

        if (tutor.getAreas() == null) {
            tutor.setAreas(new ArrayList<>());
        }

        boolean jaVinculada = tutor.getAreas().stream().anyMatch(a -> a.getId().equals(areaId));
        if (!jaVinculada) {
            tutor.getAreas().add(area);
        }

        return repo.save(tutor);
    }

    public List<Disponibilidade> listarDisponibilidades(Long tutorId) {
        return disponibilidadeRepository.findByTutorId(tutorId);
    }

    public List<Agendamento> listarAgendamentos(Long tutorId) {
        return agendamentoRepository.findByTutorId(tutorId);
    }
}
