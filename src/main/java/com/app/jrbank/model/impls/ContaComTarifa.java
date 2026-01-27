package com.app.jrbank.model.impls;

import com.app.jrbank.model.Cliente;
import com.app.jrbank.service.TarifaService;

public class ContaComTarifa implements ContaBase {

    private final ContaBase contaBase;
    private final TarifaService tarifaService;


    public ContaComTarifa(ContaBase contaBase, double valorTarifa) {
        this.contaBase = contaBase;
        this.tarifaService = new TarifaService(valorTarifa);
    }

    @Override
    public void depositar(double valor) {
        contaBase.depositar(valor);
    }

    @Override
    public void sacar(double valor) {
        contaBase.sacar(valor);
    }

    public void aplicarTarifaMensal() {
        double saldoAtual = contaBase.getSaldo();
        double novoSaldo = tarifaService.aplicarTarifa(saldoAtual);
        System.out.println("Saldo após tarifa: " + novoSaldo);
    }

    @Override
    public double getSaldo() {
        return contaBase.getSaldo();
    }

    @Override
    public int getNumero() {
        return contaBase.getNumero();
    }

    @Override
    public Cliente getTitular() {
        return contaBase.getTitular();
    }
}
