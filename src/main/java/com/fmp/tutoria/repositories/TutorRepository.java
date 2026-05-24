package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
	Optional<Tutor> findByUsuarioId(String usuarioId);
}
