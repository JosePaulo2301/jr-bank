package com.app.jrbank.model;

import com.app.jrbank.service.RendimentoService;

public class ContaPoupanca {
    private double rendimento = 0.02;
    private Conta contaBase;
    private RendimentoService rendimentoService;

    public ContaPoupanca(int numero, Cliente titular, double taxaRendimento) {
        this.contaBase = new Conta(numero, titular);
        this.rendimentoService = new RendimentoService(taxaRendimento);
    }

    public void depositar(double valor){
        contaBase.depositar(valor);
    }
    public void sacar(double valor){
        contaBase.depositar(valor);
    }

    public void aplicarRendimento() {
        double rendimento = rendimentoService.calcular(getSaldo());
        contaBase.depositar(rendimento);
    }
    public int getNumero() {
        return contaBase.getNumero();
    }
    public double getSaldo() {
        return contaBase.getSaldo();
    }
    public Cliente getTitular() {
        return contaBase.getTitular();
    }


    public String toString() {
        return "ContaPoupanca{" + "numero=" + getNumero() + ", saldo" + getSaldo() + ", titular" + getTitular() + "}";
    }
/*
    public ContaPoupanca(int numero, Cliente titular) {
        super(numero, titular);
    }
*/
    public void renderJuros() {
        double novoSaldo = getSaldo() * (1 + rendimento);
        depositar(novoSaldo - getSaldo());
        System.out.println("Juros aplicados. Novo saldo: R$" + getSaldo());
    }
}
