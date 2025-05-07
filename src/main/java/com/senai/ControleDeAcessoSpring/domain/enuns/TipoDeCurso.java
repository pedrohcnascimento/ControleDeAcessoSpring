package com.senai.ControleDeAcessoSpring.domain.enuns;

public enum TipoDeCurso {
    CAI(20, 50),
    TECNICO(15, 45);

    private final int intervaloMinutos;
    private final int minutosPorAula;

    TipoDeCurso(int intervaloMinutos, int minutosPorAula) {
        this.minutosPorAula = minutosPorAula;
        this.intervaloMinutos = intervaloMinutos;
    }

    public int getIntervaloMinutos() {return intervaloMinutos;}
    public int getMinutosPorAula() {return minutosPorAula;}

}
