package com.app.jrbank.service.impls;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.impls.ContaBase;

public class ContaBaseTestImpl implements ContaBase {
    private double saldo;
    private int numero;
    private Cliente titular;
    private double limiteChequeEspecial;


    public ContaBaseTestImpl(Cliente titular, int numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0.0;
    }
    public ContaBaseTestImpl(double saldo, double limiteChequeEspecial) {
        this.saldo = saldo;
        this.limiteChequeEspecial = limiteChequeEspecial;
    }


    @Override
    public void depositar(double valor) {
        this.saldo+= valor;
    }

    @Override
    public void sacar(double valor) {

        if(valor <= 0) {
            saldo -= valor;
            throw new IllegalArgumentException("O valor deve ser positivo..");
        }

        double disponivel = saldo + limiteChequeEspecial;

        if (valor > disponivel) {
            throw new SaldoInsuficienteException("Valor é superior ao saldo atual");
        }

        saldo -= valor;

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
