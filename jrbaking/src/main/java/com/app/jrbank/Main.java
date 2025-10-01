package com.app.jrbank;

import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.ContaCorrente;
import com.app.jrbank.model.ContaPoupanca;

public class Main {

    public static void main(String[] args) {
        Cliente maria = new Cliente("Maria", "11111111111");
        Cliente joao = new Cliente("João", "22222222222");

        ContaCorrente cc = new ContaCorrente(1001, maria, 1.50);
        ContaPoupanca cp = new ContaPoupanca(1002, joao, 0.02);


        cc.depositar(500);
        cc.sacar(200);

        cp.depositar(300);

        System.out.println(cc);
        System.out.println(cp);

    }
}
