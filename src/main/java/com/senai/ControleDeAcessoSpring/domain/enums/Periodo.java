package com.senai.ControleDeAcessoSpring.domain.enums;

public enum Periodo {
    MANHA("M"),
    TARDE("T"),
    NOITE("N"),
    INTEGRAL("I");

    Periodo(String sigla) {
        this.sigla = sigla;
    }

    private final String sigla;

    public String getSigla() {
        return sigla;
    }
}
