package com.app.jrbank.model;

import com.app.jrbank.service.TaxaService;

public class ContaCorrente {

    private Conta contaBase;
    private TaxaService taxaService;

    public ContaCorrente(int numero, Cliente titular, double taxa) {
        this.contaBase = new Conta(numero, titular);
        this.taxaService = new TaxaService(taxa);
    }
    public void depositar(double valor){
        contaBase.depositar(valor);
    }

    public void sacar(double valor) {
        double valorComTaxa = taxaService.aplicar(valor);
        contaBase.sacar(valorComTaxa);
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

    @Override
    public String toString() {
        return "ContaPoupanca{" + "numero=" + getNumero() + ", saldo" + getSaldo() + ", titular" + getTitular() +"}";
    }
}