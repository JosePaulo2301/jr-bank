package com.app.jrbank.model;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.impls.ContaBase;
import com.app.jrbank.model.impls.ContaNormal;
import com.app.jrbank.repository.ContaRepository;
import com.app.jrbank.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ContaNormalTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDepositarCorretamente() {
        Cliente maria = new Cliente("maira", "11");
        ContaBase conta = new ContaNormal(1001, maria);

        conta.depositar(200);

        assertEquals(200, conta.getSaldo(), 0.001);
    }

    @Test
    void deveValidarSaldo() {
        Cliente maria = new Cliente("maria", "11");
        ContaBase conta = new ContaNormal(1001, maria);

        conta.depositar(200);

        assertEquals(200, conta.getSaldo());
    }

    @Test
    void deveGerarExceptionSaldoInsuficiente() {

        // Arrange
        int origem = 1002;
        int destino = 1003;
        double valor = 0.15;

        Conta contaOrigem = new Conta(1002, 0.50);
        Conta contaDestino = new Conta(1003, 0.50);

        Mockito.when(contaRepository.buscarPorNumero(origem)).thenReturn(contaOrigem);
        Mockito.when(contaRepository.buscarPorNumero(destino)).thenReturn(contaDestino);

        assertThrows(SaldoInsuficienteException.class,
                () -> contaService.transferir(
                        contaOrigem.getNumero(),
                        contaDestino.getNumero(),
                        valor));
    }

}
