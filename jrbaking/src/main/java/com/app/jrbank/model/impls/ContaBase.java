package com.app.jrbank.model.impls;

import com.app.jrbank.model.Cliente;

public interface ContaBase {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
    int getNumero();
    Cliente getTitular();
}
