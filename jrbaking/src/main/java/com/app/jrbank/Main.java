package com.app.jrbank;

import com.app.jrbank.model.*;
import com.app.jrbank.model.impls.*;

public class Main {

    public static void main(String[] args) {
        Cliente maria = new Cliente("Maria", "11111111111");

        ContaBase contaNormal = new ContaNormal(1001, maria);

        ContaBase contaPremium = new ContaPremium(new ContaNormal(1002, maria), 500);
        ContaPoupanca contaPoupanca = new ContaPoupanca(new ContaNormal(1003, maria), 0.02);

        contaNormal.depositar(500);
        contaNormal.sacar(200);
        System.out.println("Conta normal saldo: " + contaNormal.getSaldo());

        contaPremium.depositar(100);
        contaPremium.sacar(550);
        System.out.println("Conta premium saldo: " + contaPremium.getSaldo());

        contaPoupanca.depositar(1000);
        contaPoupanca.aplicarRendimento();
        System.out.println("Conta poupança saldo: " + contaPoupanca.getSaldo());

        System.out.println("----------------------------------");
        ContaComTarifa contaComTarifa = new ContaComTarifa(contaNormal, 15.0);
        contaComTarifa.aplicarTarifaMensal();


    }
}
