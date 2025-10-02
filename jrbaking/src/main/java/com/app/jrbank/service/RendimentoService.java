package com.app.jrbank.service;

public class RendimentoService {
    private final double taxaRendimento;

    public RendimentoService(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public double calcular(double saldoAtual) {
        return saldoAtual * taxaRendimento;
    }
}
