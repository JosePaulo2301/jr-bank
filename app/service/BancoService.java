package app.service;

import java.util.HashMap;
import java.util.Map;

import app.model.Conta;

public class BancoService {
        private Map<Integer, Conta> contas = new HashMap<>();

    public void criarConta(Conta conta) {
        contas.put(conta.getNumero(), conta);
        System.out.println("Conta criada: " + conta);
    }

    public Conta buscarConta(int numero) {
        return contas.get(numero);
    }

    public void listarContas() {
        contas.values().forEach(System.out::println);
    }

}
