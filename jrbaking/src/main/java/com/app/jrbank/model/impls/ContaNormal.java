package com.app.jrbank.model.impls;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;

public class ContaNormal implements ContaBase {

    private int numero;
    private double saldo;
    private Cliente titular;

    public ContaNormal(int numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }


    @Override
    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public Cliente getTitular() {
        return titular;
    }
}
