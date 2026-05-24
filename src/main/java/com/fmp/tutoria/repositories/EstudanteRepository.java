package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
	Optional<Estudante> findByUsuarioId(String usuarioId);
}
