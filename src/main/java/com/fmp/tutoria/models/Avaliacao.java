package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    @Id
    private String id;
    private Integer nota;
    private String comentario;
    @Column(name = "dataAvaliacao")
    private LocalDateTime dataAvaliacao;

    @Column(name = "agendamento_id")
    private String agendamentoId;

    @PrePersist
    public void gerarIdSeNecessario() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }
}
