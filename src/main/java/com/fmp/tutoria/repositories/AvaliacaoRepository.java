package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, String> {
	Optional<Avaliacao> findByAgendamentoId(String agendamentoId);
}
