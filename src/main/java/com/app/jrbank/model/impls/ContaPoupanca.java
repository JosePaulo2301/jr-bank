package com.app.jrbank.model.impls;

import com.app.jrbank.model.Cliente;
import com.app.jrbank.service.RendimentoService;

public class ContaPoupanca implements ContaBase {
        private final ContaBase contaBase;
        private final RendimentoService rendimentoService;

    public ContaPoupanca(ContaBase contaBase, double taxaRendimento) {
        this.contaBase = contaBase;
        this.rendimentoService = new RendimentoService(taxaRendimento);
    }

    @Override
    public void depositar(double valor) {
        contaBase.depositar(valor);
    }

    @Override
    public void sacar(double valor) {
        contaBase.sacar(valor);
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

    public void aplicarRendimento() {
        double rendimento = rendimentoService.calcular(getSaldo());
        contaBase.depositar(rendimento);
    }
}
