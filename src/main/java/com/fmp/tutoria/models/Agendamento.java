package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    private String id;
    private String status;
    @Column(name = "dataHora")
    private LocalDateTime dataHora;
    @Column(name = "disponibilidade_id")
    private Long disponibilidadeId;
    @Column(name = "estudante_id")
    private Long estudanteId;
    @Column(name = "tutor_id")
    private Long tutorId;

    @PrePersist
    public void gerarIdSeNecessario() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }
}
