package com.app.jrbank.model;

import com.app.jrbank.exception.SaldoInsuficienteException;

public class ContaPremium implements ContaBase {
    private final ContaBase contaBase;
    private final double limiteEspecial;


    public ContaPremium(ContaBase contaBase, double limiteEspecial) {
        this.contaBase = contaBase;
        this.limiteEspecial = limiteEspecial;
    }

    @Override
    public void depositar(double valor) {
        contaBase.depositar(valor);
    }

    @Override
    public void sacar(double valor) {
        if(contaBase.getSaldo() + limiteEspecial < valor) {
            throw  new SaldoInsuficienteException("Saldo insuficiente, mesmo com limite especial");
        }
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
}
