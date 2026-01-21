package com.app.jrbank.service.impls;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.impls.ContaBase;

public class ContaBaseTestImpl implements ContaBase {
    private double saldo;
    private int numero;
    private Cliente titular;


    public ContaBaseTestImpl(Cliente titular, int numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0.0;
    }


    @Override
    public void depositar(double valor) {
        this.saldo+= valor;
    }

    @Override
    public void sacar(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("não ha saldo suficiente para sacar..");
        } else if (valor >= 0) {
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
