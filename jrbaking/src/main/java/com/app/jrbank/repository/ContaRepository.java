package com.app.jrbank.repository;

import com.app.jrbank.model.Conta;

import java.util.List;

public interface ContaRepository {
    void salvar(Conta conta);
    Conta buscarPorNumero(int numero);
    List<Conta> listarTodas();
}
