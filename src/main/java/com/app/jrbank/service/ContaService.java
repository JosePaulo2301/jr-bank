package com.app.jrbank.service;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Conta;
import com.app.jrbank.repository.ContaRepository;

public class ContaService {

    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaService() {
    }

    public void transferir(int origem, int destino, double valor) throws SaldoInsuficienteException {
        Conta contaOrigem = contaRepository.buscarPorNumero(origem);
        Conta contaDestino = contaRepository.buscarPorNumero(destino);

        if (contaOrigem == null || contaDestino == null) {
            throw new IllegalArgumentException("Conta inexistente");
        }
        if (contaOrigem.getSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transferencia");
        }

        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);

        contaRepository.salvar(contaOrigem);
        contaRepository.salvar(contaDestino);
    }
}
