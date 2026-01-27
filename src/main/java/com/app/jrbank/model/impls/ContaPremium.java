package com.app.jrbank.model.impls;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;

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
        if(valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        
        if(contaBase.getSaldo() + limiteEspecial < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente, mesmo com limite especial");
        }
        
        double saldoAtual = contaBase.getSaldo();
        if(saldoAtual >= valor) {
            contaBase.sacar(valor);
        } else {
            if(saldoAtual > 0) {
                contaBase.sacar(saldoAtual);
            }
        }
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
