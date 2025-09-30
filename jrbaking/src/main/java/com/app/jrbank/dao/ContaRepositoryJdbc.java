package com.app.jrbank.dao;

import com.app.jrbank.model.Conta;
import com.app.jrbank.repository.ContaRepository;

import java.util.List;

public class ContaRepositoryJdbc implements ContaRepository {
    @Override
    public void salvar(Conta conta) {

    }

    @Override
    public Conta buscarPorNumero(int numero) {
        return null;
    }

    @Override
    public List<Conta> listarTodas() {
        return List.of();
    }
}
