package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disponibilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tutor_id")
    private Long tutorId;
    @Column(name = "diaSemana")
    private String diaSemana;
    private String horario;
    private Boolean agenda;
}
