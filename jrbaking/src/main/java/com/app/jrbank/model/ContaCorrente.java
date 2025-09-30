package com.app.jrbank.model;

public class ContaCorrente extends Conta {
    private double taxa = 1.50;

    public ContaCorrente(int numero, Cliente titular) {
        super(numero, titular);
    }

    @Override
    public void sacar(double valor) {
        super.sacar(valor + taxa);
    }
}