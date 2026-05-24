package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
	List<Disponibilidade> findByTutorId(Long tutorId);
}
