package com.senai.ControleDeAcessoSpring.domain.enums;

public enum TipoDeCurso {
    CAI(20, 50),
    TECNICO(1, 1); // Originalmente 15 e 45 (alterado para testes)

    private final int intervaloMinutos;
    private final int minutosPorAula;

    TipoDeCurso(int intervaloMinutos, int minutosPorAula) {
        this.minutosPorAula = minutosPorAula;
        this.intervaloMinutos = intervaloMinutos;
    }

    public int getIntervaloMinutos() {return intervaloMinutos;}
    public int getMinutosPorAula() {return minutosPorAula;}

}
