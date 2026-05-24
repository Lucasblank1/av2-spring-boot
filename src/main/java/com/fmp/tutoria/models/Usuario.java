package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    private String id;

    @NotBlank(message = "Nome e obrigatorio")
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    private String senha;
    @Column(name = "dataCadastro")
    private LocalDateTime dataCadastro;

    @NotBlank(message = "Tipo de perfil e obrigatorio")
    @Column(name = "tipoPerfil")
    private String tipoPerfil;
    private String telefone;

    @PrePersist
    public void gerarIdSeNecessario() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }
}
