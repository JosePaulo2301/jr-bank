package com.app.jrbank.model;


public  class Conta {
    private int numero;
    private double saldo;
    private Cliente titular;

    public Conta(int numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
            saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0)     throw new IllegalArgumentException("Valor deve ser positivo");
        if (saldo < valor)  throw new RuntimeException("Saldo insuficiente");
            saldo -= valor;
    }

    public void transferir(double valor, Conta destino) {
        if (valor <= saldo) {
            this.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " para conta " + destino.getNumero() + " realizada.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public int getNumero() { return numero; }

    public double getSaldo() { return saldo; }

    public Cliente getTitular() { return titular; }

    @Override
    public String toString() {
        return "Conta nº " + this.numero + " | Titular: " + titular.getNome() + " | Saldo: R$" + this.saldo;
    }
}