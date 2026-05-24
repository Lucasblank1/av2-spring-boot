package com.fmp.tutoria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String curriculo;
    private String especialidade;
    @Column(name = "usuario_id")
    private String usuarioId;
    private Boolean aprovado;

    @ManyToMany
    @JoinTable(
            name = "tutor_area",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<AreaConhecimento> areas = new ArrayList<>();
}
