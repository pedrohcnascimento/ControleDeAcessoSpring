package com.senai.ControleDeAcessoSpring.domain.enums;

public enum TipoDeCurso {
    CAI(20,50), TECNICO(15,45);

    private final int intervaloMinutos;
    private final int minutosPorAula;

    TipoDeCurso(int intervaloMinutos, int minutosPorAula ) {

        this.intervaloMinutos = intervaloMinutos;
        this.minutosPorAula = minutosPorAula;
    }

    public int getIntervaloMinutos() {
        return intervaloMinutos;
    }

    public int getMinutosPorAula() {
        return minutosPorAula;
    }
}
