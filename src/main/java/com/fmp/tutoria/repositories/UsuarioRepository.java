package com.fmp.tutoria.repositories;

import com.fmp.tutoria.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {}
