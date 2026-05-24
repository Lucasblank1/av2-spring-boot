package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coordenador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "matriculaProfessor")
    private Integer matriculaProfessor;

    @Column(name = "usuario_id")
    private String usuarioId;
}
