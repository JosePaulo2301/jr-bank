package com.app.jrbank.service;

import com.app.jrbank.dao.ContaRepositoryMemoria;
import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.Conta;
import com.app.jrbank.model.ContaCorrente;
import com.app.jrbank.model.ContaPoupanca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class ContaServiceTest {

    private ContaRepositoryMemoria repo;
    private ContaService service;
    private Conta contaOrigem;
    private Conta contaDestino;


    @BeforeEach
    void setup() {
        repo    = new ContaRepositoryMemoria();
        service = new ContaService(repo);

        Cliente maria = new Cliente("maria","111111111111");
        Cliente joao = new Cliente("joao","222222222222");

        //contaOrigem = new ContaCorrente(1001, maria);
       // contaDestino = new ContaPoupanca(1002, joao);

        contaOrigem.depositar(500);

        repo.salvar(contaOrigem);
        repo.salvar(contaDestino);
    }

    @Test
    void deveTransferirComSucesso() {
        service.transferir(1001,1002,200);

        assertEquals(300, contaOrigem.getSaldo());
        assertEquals(200, contaDestino.getSaldo());
    }

    @Test
    void deveLancerExecaoQuandoSaldoInsuficiente() {
        assertThrows(SaldoInsuficienteException.class, () -> service.transferir(1001,1002,1000));
    }

    @Test
    void deveLancarExcecaoQuandoContaNaoExiste() {
        assertThrows(IllegalArgumentException.class, () -> service.transferir(9999,1002,100));
    }
}
