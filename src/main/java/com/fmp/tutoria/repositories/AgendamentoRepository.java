package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {
	List<Agendamento> findByTutorId(Long tutorId);

	List<Agendamento> findByEstudanteId(Long estudanteId);

	Optional<Agendamento> findFirstByDisponibilidadeId(Long disponibilidadeId);
}
