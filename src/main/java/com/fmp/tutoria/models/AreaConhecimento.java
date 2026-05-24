package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "area_conhecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaConhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e obrigatorio")
    private String nome;
    private String descricao;
}
