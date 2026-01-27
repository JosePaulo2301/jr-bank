package com.app.jrbank.service;

public class RendimentoService {
    private double taxaRendimento;

    public RendimentoService() {
    }

    public RendimentoService(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public double calcular(double saldoAtual) {
        return saldoAtual * taxaRendimento;
    }

    public double calcular(Double saldoAtual) {
        if (saldoAtual == null) {
            throw new IllegalArgumentException("O saldo não pode ser é nulo");
        } else {
            return saldoAtual * taxaRendimento;
        }

    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

}
