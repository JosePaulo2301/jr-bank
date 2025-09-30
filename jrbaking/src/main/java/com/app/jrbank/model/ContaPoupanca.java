package com.app.jrbank.model;

public class ContaPoupanca extends Conta {
        private double rendimento = 0.02;

    public ContaPoupanca(int numero, Cliente titular) {
        super(numero, titular);
    }

    public void renderJuros() {
        double novoSaldo = getSaldo() * (1 + rendimento);
        depositar(novoSaldo - getSaldo());
        System.out.println("Juros aplicados. Novo saldo: R$" + getSaldo());
    }
}
