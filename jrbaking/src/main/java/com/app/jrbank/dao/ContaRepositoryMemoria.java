package com.app.jrbank.dao;

import com.app.jrbank.model.Conta;
import com.app.jrbank.repository.ContaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaRepositoryMemoria implements ContaRepository {
    Map<Integer, Conta> contas = new HashMap<>();

    @Override
    public void salvar(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    @Override
    public Conta buscarPorNumero(int numero) {
        return contas.get(numero);
    }

    @Override
    public List<Conta> listarTodas() {
        return new ArrayList<>(contas.values());
    }
}
