package com.fmp.tutoria.enums;

import java.util.Arrays;

public enum ProfileEnum {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final Integer codigo;
    private final String descricao;

    ProfileEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ProfileEnum toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        return Arrays.stream(ProfileEnum.values())
                .filter(profile -> profile.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Codigo de perfil invalido: " + codigo));
    }

    public static ProfileEnum fromDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            return null;
        }

        String normalizada = descricao.trim().toUpperCase();
        if (normalizada.startsWith("ROLE_")) {
            normalizada = normalizada.substring(5);
        }

        return ProfileEnum.valueOf(normalizada);
    }
}