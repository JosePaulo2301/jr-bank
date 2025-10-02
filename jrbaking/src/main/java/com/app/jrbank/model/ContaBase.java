package com.app.jrbank.model;

public interface ContaBase {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
    int getNumero();
    Cliente getTitular();
}
