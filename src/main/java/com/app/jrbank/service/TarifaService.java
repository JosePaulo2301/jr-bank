package com.app.jrbank.service;

public class TarifaService {
    private final double valorTarifa;

    public TarifaService(double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public double aplicarTarifa(double saldoAtual) {
        double novoSaldo = saldoAtual - valorTarifa;
        System.out.println("[Tafia aplicada] - " + valorTarifa + " | Novo saldo: " + novoSaldo);
        return novoSaldo;
    }
}
